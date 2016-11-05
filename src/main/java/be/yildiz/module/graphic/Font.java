//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.module.graphic;

import be.yildiz.common.Color;
import be.yildiz.common.resource.Resource;
import be.yildiz.common.util.Registerer;

import java.util.Arrays;

/**
 * Object containing data for a font.
 * Immutable class.
 *
 * @author Grégory Van den Borre
 */
public abstract class Font extends Resource {

    /**
     * Number of char to use.
     */
    protected static final int CHARS = 256;

    /**
     * List of all registered fonts, key is their name.
     */
    private static final Registerer<Font> REGISTERER = Registerer.newRegisterer();

    /**
     * Font height.
     */
    public final int size;

    /**
     * Font color.
     */
    public final Color color;

    /**
     * Width for each char, position in the tab is the ascii value. (i.e. charWidth[65] is the size of the char 'A').
     */
    private float[] charWidth;

    /**
     * Full constructor.
     *
     * @param fontName Font name, must be unique.
     * @param fontSize Font height.
     * @param color    Font color.
     */
    protected Font(final String fontName, final int fontSize, final Color color) {
        super(fontName);
        //FIXME LOW no assert!
        assert fontSize >= 0 : "fontSize parameter is smaller than 0";
        this.size = fontSize;
        this.color = color;
        Font.REGISTERER.register(this);
    }

    /**
     * @return The default font.
     */
    public static Font getDefault() {
        return get("default");
    }

    /**
     * Retrieve a Font from its name.
     *
     * @param name Font to retrieve.
     * @return The matching font.
     */
    public static Font get(final String name) {
        if (name.isEmpty()) {
            return Font.getDefault();
        }
        return Font.REGISTERER.get(name);
    }

    /**
     * Crop a text if it is longer than a given width.
     * The outside part is replaced by "...", excepted if the width is not sufficient to contains it, then it returns an empty String.
     * @param originalText Text to crop.
     * @param width Maximum text width allowed.
     * @return originalText if it is smaller than width, a cropped text with "..." if the text cannot fit in the given width, or "" if "..." cannot fit in the given width.
     */
    public String crop(final String originalText, final int width) {
        float size = 0;
        for(int i = 0; i < originalText.length(); i++) {
            size += charWidth[originalText.charAt(i)];
            if(size > width) {
                if(i < 3) {
                    return "";
                }
                return originalText.substring(0, i - 3) + "...";
            }
        }
        return originalText;
    }

    /**
     * Compute the width of a text with this font.
     *
     * @param text Text to use.
     * @return The text width.
     */
    public final int computeTextWidth(final String text) {
        assert text != null;
        float computedSize = 0;
        for (final char c : text.toCharArray()) {
            computedSize += this.charWidth[c];
        }
        return Math.round(computedSize);
    }

    /**
     * Return the width of a particular char.
     *
     * @param key Char to get the width.
     * @return The char width.
     */
    public final float getCharSize(final int key) {
        return this.charWidth[key];
    }

    /**
     * Set the size for every char for this font.
     *
     * @param widthArray Array containing the size for every char.
     */
    protected final void setCharWidth(final float[] widthArray) {
        assert widthArray != null;
        this.charWidth = Arrays.copyOf(widthArray, widthArray.length);
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getName().hashCode();
        result = (prime * result + this.size);
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Font other = (Font) obj;
        if (this.getName() == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!this.getName().equals(other.getName())) {
            return false;
        }
        return this.size == other.size;
    }
}
