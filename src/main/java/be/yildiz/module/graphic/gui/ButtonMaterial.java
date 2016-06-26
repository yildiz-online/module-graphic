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

package be.yildiz.module.graphic.gui;

import be.yildiz.common.util.StringUtil;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;
import lombok.NonNull;

import java.util.Optional;

/**
 * Wrapper class providing different material data for a button.
 *
 * @author Van den Borre Grégory
 */
public class ButtonMaterial {

    /**
     * Background material.
     */
    public final Material material;

    /**
     * Highlight material.
     */
    public final Material highlight;

    /**
     * Inactive material.
     */
    public final Material inactive;

    /**
     * Caption font.
     */
    public final Optional<Font> font;

    /**
     * Caption font.
     */
    public final Optional<Font> inactiveFont;

    /**
     * Full constructor.
     *
     * @param material Background material.
     * @param hlight   Highlight material.
     * @param inactive Inactive material.
     */
    public ButtonMaterial(final Material material, final Material hlight, final Material inactive) {
        super();
        this.material = material;
        this.highlight = hlight;
        this.inactive = inactive;
        this.font = Optional.empty();
        this.inactiveFont = Optional.empty();
    }

    /**
     * Full constructor.
     *
     * @param material Background material.
     * @param hlight   Highlight material.
     */
    public ButtonMaterial(final Material material, final Material hlight) {
        super();
        this.material = material;
        this.highlight = hlight;
        this.inactive = material;
        this.font = Optional.empty();
        this.inactiveFont = Optional.empty();
    }

    public ButtonMaterial(final Material material, final Material hlight, @NonNull final Font font) {
        super();
        this.material = material;
        this.highlight = hlight;
        this.inactive = material;
        this.font = Optional.of(font);
        this.inactiveFont = Optional.empty();
    }

    public ButtonMaterial(final Material material, final Material hlight, final Material inactive, @NonNull final Font font, final Font inactiveFont) {
        super();
        this.material = material;
        this.highlight = hlight;
        this.inactive = inactive;
        this.font = Optional.of(font);
        this.inactiveFont = Optional.of(inactiveFont);
    }

    public ButtonMaterial(final Material material, final Material hlight, final Material inactive, @NonNull final Optional<Font> font) {
        super();
        this.material = material;
        this.highlight = hlight;
        this.inactive = inactive;
        this.font = font;
        this.inactiveFont = Optional.empty();
    }

    public ButtonMaterial(final Material material, final Material hlight, @NonNull final Optional<Font> font) {
        super();
        this.material = material;
        this.highlight = hlight;
        this.inactive = material;
        this.font = font;
        this.inactiveFont = Optional.empty();
    }

    /**
     * @return A copy of those materials.
     */
    public ButtonMaterial copy() {
        Material m = this.material.copy(StringUtil.buildRandomString(this.material.getName()));
        Material hl = this.highlight.copy(StringUtil.buildRandomString(this.highlight.getName()));
        return new be.yildiz.module.graphic.gui.ButtonMaterial(m, hl);
    }
}
