/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildiz.module.graphic.gui;

import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;
import be.yildizgames.common.util.StringUtil;

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
    public final Font font;

    /**
     * Caption font.
     */
    public final Font inactiveFont;

    public ButtonMaterial(final Material material, final Material hlight, final Font font) {
        super();
        assert material != null;
        assert hlight != null;
        assert font != null;
        this.material = material;
        this.highlight = hlight;
        this.inactive = material;
        this.font = font;
        this.inactiveFont = font;
    }

    public ButtonMaterial(final Material material, final Material hlight, final Material inactive, final Font font, final Font inactiveFont) {
        super();
        assert material != null;
        assert hlight != null;
        assert font != null;
        assert inactive != null;
        assert inactiveFont != null;
        this.material = material;
        this.highlight = hlight;
        this.inactive = inactive;
        this.font = font;
        this.inactiveFont = inactiveFont;
    }

    /**
     * @return A copy of those materials.
     */
    public ButtonMaterial copy() {
        Material m = this.material.copy(StringUtil.buildRandomString(this.material.getName()));
        Material hl = this.highlight.copy(StringUtil.buildRandomString(this.highlight.getName()));
        Material i = this.inactive.copy(StringUtil.buildRandomString(this.inactive.getName()));
        return new ButtonMaterial(m, hl, i, font, inactiveFont);
    }
}
