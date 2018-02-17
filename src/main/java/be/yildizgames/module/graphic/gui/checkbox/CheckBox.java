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

package be.yildizgames.module.graphic.gui.checkbox;

import be.yildizgames.common.client.translation.Key;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.graphic.gui.ContainerChild;
import be.yildizgames.module.graphic.gui.Widget;

/**
 * @author Grégory Van den Borre
 */
public interface CheckBox extends Widget, ContainerChild {

    /**
     * Update the text associated with the check box.
     *
     * @param captionText New text to set.
     * @return This object for chaining.
     */
    CheckBox setCaptionText(String captionText);

    CheckBox setCaptionText(Key captionText);

    CheckBox setCaptionColor(Color color);

    /**
     * Set the check box state.
     *
     * @param checked <code>true</code> will set checked state while <code>false</code> is unchecked.
     */
    CheckBox check(boolean checked);

    boolean isChecked();

}
