/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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

package be.yildizgames.module.graphic.gui;

import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.common.frame.FrameListener;
import be.yildizgames.module.graphic.gui.button.ButtonBuilder;
import be.yildizgames.module.graphic.gui.checkbox.CheckBoxBuilder;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.container.ContainerBuilder;
import be.yildizgames.module.graphic.gui.image.ImageBuilder;
import be.yildizgames.module.graphic.gui.inputbox.InputBoxBuilder;
import be.yildizgames.module.graphic.gui.progressbar.ProgressBarBuilder;
import be.yildizgames.module.graphic.gui.textarea.TextAreaBuilder;
import be.yildizgames.module.graphic.gui.textline.TextLine;
import be.yildizgames.module.graphic.gui.textline.TextLineBuilder;
import be.yildizgames.module.graphic.gui.textline.TextLineTemplate;
import be.yildizgames.module.window.ScreenSize;

/**
 * @author Grégory Van den Borre
 */
public interface GuiFactory {

    ContainerBuilder container();

    ButtonBuilder button();

    TextLineBuilder textLine();

    TextLine textLine(Container parent, TextLineTemplate template);

    TextLine textLine(Container parent, TextLineTemplate template, TranslationKey text);

    TextAreaBuilder textArea();

    CheckBoxBuilder checkbox();

    ImageBuilder image();

    FrameListener getAnimationManager();

    InputBoxBuilder inputBox();

    ProgressBarBuilder progressBar();

    ScreenSize getScreenSize();
}
