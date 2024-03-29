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

import be.yildizgames.module.coordinates.Coordinates;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.Element;

/**
 * @author Grégory Van den Borre
 */
public final class WidgetMock extends BaseContainerChild {

    public WidgetMock(final Coordinates coordinates, final Container parent) {
        this("random_" + System.nanoTime(), coordinates, parent);
    }

    public WidgetMock(final String name, final Coordinates coordinates, final Container parent) {
        super("mock " + name, coordinates, parent);
        this.show();
    }

    @Override
    public void delete() {
    }

    @Override
    protected void highlightImpl(final boolean highlighted) {
    }

    @Override
    protected void setSizeImpl(final int w, final int h) {
    }

    @Override
    protected void showImpl() {
    }

    @Override
    protected void hideImpl() {
    }

    @Override
    protected Element setPositionImpl(final int x, final int y) {
        return this;
    }

}
