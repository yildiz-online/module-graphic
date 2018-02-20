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

package be.yildizgames.module.graphic.gui;

import be.yildizgames.common.geometry.Rectangle;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.button.ButtonMaterial;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleButton;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleContainer;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleGuiFactory;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleTextLine;
import be.yildizgames.module.graphic.material.Material;

import java.util.Arrays;
import java.util.List;

/**
 * A Window is a basic View with a title bar and a possible scroll bar if the main container content is bigger than the container.
 *
 * @author Grégory Van den Borre
 */
public abstract class Window extends View {

    /**
     * Window 's height when in minimized state.
     */
    private static final int MINIMIZED_SIZE = 30;

    /**
     * Full height, to use to resize after a minimize.
     */
    private final int initialHeight;
    /**
     * Associated title bar.
     */
    private final TitleBar titleBar;

    private final Font font;
    /**
     * Current state of the panel.
     */
    private ContainerState state;

    /**
     * Full constructor. Build the wrapped container with the given name.
     *
     * @param name         Container name.
     * @param builder      GUI builder.
     * @param font         Font to use.
     * @param z            View Z position.
     * @param eventManager Associated event manager.
     * @param params       Parameter to build the window.
     */
    public Window(final String name, final SimpleGuiFactory builder, Font font, final Zorder z, GuiEventManager eventManager, final Parameter... params) {
        this(builder.buildOverlayContainer(name, Material.empty(), BaseCoordinate.ZERO), builder, font, z, eventManager, params);
    }

    /**
     * Full constructor, build the Window.
     *
     * @param container    Container to use for the window.
     * @param builder      GUI builder.
     * @param font         Font to use.
     * @param z            View Z position.
     * @param eventManager Associated event manager.
     */
    public Window(final SimpleContainer container, final SimpleGuiFactory builder, Font font, final Zorder z, GuiEventManager eventManager) {
        this(container, builder, font, z, eventManager, Parameter.NOTHING);
    }

    /**
     * Full constructor, Build the Window from a given container.
     *
     * @param container    Container to use for the window.
     * @param builder      GUI builder.
     * @param font         Font to use.
     * @param z            View Z position.
     * @param eventManager Associated event manager.
     * @param params       Parameter to build the window.
     */
    public Window(final SimpleContainer container, final SimpleGuiFactory builder, Font font, final Zorder z, GuiEventManager eventManager, final Parameter... params) {
        super(container, z, eventManager);
        this.font = font;
        final List<Parameter> paramList = Arrays.asList(params);
        if (!paramList.contains(Parameter.NOT_MOVABLE)) {
            container.addMouseDragListener(new ContainerElementDragListener(container, new Rectangle(0, 0, builder.getScreenSize().width, builder.getScreenSize().height)));
        }
        this.state = ContainerState.OPEN;
        if (!paramList.contains(Parameter.NO_TITLE_BAR)) {
            this.titleBar = new BasicTitleBar(builder, font);
        } else {
            this.titleBar = new NoTitleBar();
        }
        this.initialHeight = container.getHeight();
    }

    /**
     * Switch between available ContainerState.
     */
    public final void switchState() {
        if (this.state == ContainerState.OPEN) {
            this.state = ContainerState.MINIMIZED;
            this.getContainer().setHeight(Window.MINIMIZED_SIZE);
            this.getContainer().hideContent();
            this.titleBar.showContent();
        } else if (this.state == ContainerState.MINIMIZED) {
            this.state = ContainerState.OPEN;
            this.getContainer().setHeight(this.initialHeight);
            this.getContainer().showContent();
        }
    }

    /**
     * Different possible parameters to build the window.
     *
     * @author Van den Borre Grégory
     */
    public enum Parameter {

        /**
         * No parameter.
         */
        NOTHING,

        /**
         * The window will be created without title bar.
         */
        NO_TITLE_BAR,

        /**
         * No minimize button.
         */
        NO_MINIMIZE,

        /**
         * The window will not be movable.
         */
        NOT_MOVABLE
    }

    @FunctionalInterface
    private interface TitleBar {
        void showContent();
    }

    /**
     * A basic title bar for a Window, it contains a minimize and a close button, and a title.
     *
     * @author Van den Borre Grégory
     */
    private final class BasicTitleBar implements TitleBar {

        /**
         * Title bar height.
         */
        private final int height;

        /**
         * Text to print as title for the window.
         */
        private final SimpleTextLine title;

        /**
         * GuiButton to close the Window.
         */
        private final SimpleButton close;

        /**
         * Full constructor.
         *
         * @param builder GUI builder.
         */
        public BasicTitleBar(final SimpleGuiFactory builder, Font font) {
            super();
            this.height = Window.MINIMIZED_SIZE;
            final SimpleContainer container = builder.getContainer(Window.this.getContainer().getName());
            this.title = builder.buildTextLine("title_" + Window.this.getName(), new Coordinates(Window.this.getContainer().getWidth(), 20, BaseCoordinate.ZERO.left, BaseCoordinate.ZERO.top), font, container);
            final Coordinates closeCoordinates = new Coordinates(this.height, this.height, Window.this.getContainer().getWidth() - this.height, 0);
            this.close = builder.buildButton("close_" + Window.this.getName(), closeCoordinates, new ButtonMaterial(Material.empty(), Material.empty(), font), container);
            this.close.addMouseLeftClickListener(Window.this::hide);
        }

        /**
         * Show the title bar elements.
         */
        @Override
        public void showContent() {
            this.close.show();
            this.title.show();
        }
    }

    /**
     * An empty title bar for a Window, it contains a minimize and a close button, and a title.
     *
     * @author Van den Borre Grégory
     */
    private final class NoTitleBar implements TitleBar {

        /**
         * Full constructor.
         */
        public NoTitleBar() {
            super();
        }

        /**
         * Show the title bar elements.
         */
        @Override
        public void showContent() {
        }
    }

}
