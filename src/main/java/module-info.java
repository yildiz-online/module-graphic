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

module be.yildizgames.module.graphic {
    uses be.yildizgames.module.graphic.GraphicEngineProvider;
    requires be.yildizgames.common.geometry;
    requires be.yildizgames.common.gameobject;
    requires be.yildizgames.common.util;
    requires be.yildizgames.common.model;
    requires be.yildizgames.common.file;
    requires be.yildizgames.common.shape;
    requires be.yildizgames.common.client;
    requires be.yildizgames.common.frame;
    requires be.yildizgames.common.time;
    requires be.yildizgames.module.window;

    exports be.yildizgames.module.graphic;
    exports be.yildizgames.module.graphic.query;
    exports be.yildizgames.module.graphic.animation;
    exports be.yildizgames.module.graphic.billboard;
    exports be.yildizgames.module.graphic.camera;
    exports be.yildizgames.module.graphic.dummy;
    exports be.yildizgames.module.graphic.light;
    exports be.yildizgames.module.graphic.material;
    exports be.yildizgames.module.graphic.misc;
    exports be.yildizgames.module.graphic.particle;
    exports be.yildizgames.module.graphic.shader;
    exports be.yildizgames.module.graphic.gui;
    exports be.yildizgames.module.graphic.gui.button;
    exports be.yildizgames.module.graphic.gui.checkbox;
    exports be.yildizgames.module.graphic.gui.container;
    exports be.yildizgames.module.graphic.gui.container.animation;
    exports be.yildizgames.module.graphic.gui.element;
    exports be.yildizgames.module.graphic.gui.image;
    exports be.yildizgames.module.graphic.gui.image.animation;
    exports be.yildizgames.module.graphic.gui.inputbox;
    exports be.yildizgames.module.graphic.gui.progressbar;
    exports be.yildizgames.module.graphic.gui.table;
    exports be.yildizgames.module.graphic.gui.textarea;
    exports be.yildizgames.module.graphic.gui.textline;

    exports be.yildizgames.module.graphic.gui.internal to be.yildizgames.module.graphic.ogre;
    exports be.yildizgames.module.graphic.gui.internal.impl to be.yildizgames.module.graphic.ogre;
}
