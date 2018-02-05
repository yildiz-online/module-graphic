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

import be.yildiz.module.coordinate.BaseCoordinate;

import java.util.Optional;

/**
 * A tab container contains a list of tab panel, only the selected is visible
 *
 * @author Grégory Van den Borre
 */
public final class TabContainer extends ContainerChild {

    /**
     * The list of tab in this container.
     */
    private final TabElement[] elements;

    /**
     * Image to use as background.
     */
    private final Image background;

    /**
     * The currently selected tab.
     */
    private TabElement selected;

    /**
     * Full container.
     *
     * @param name         Tab container name, must be unique.
     * @param coordinates  Tab container size and position.
     * @param tabSelectors List of buttons used as tabs.
     * @param container    Container holding this tab container.
     */
    TabContainer(final String name, final BaseCoordinate coordinates, final View[] containers, final GuiButton[] tabSelectors, final Image background, final GuiContainer container) {
        super(name, coordinates, Optional.of(container));
        this.elements = new TabElement[tabSelectors.length];
        this.background = background;

        for (int i = 0; i < tabSelectors.length; i++) {

            this.elements[i] = new TabElement(i, tabSelectors[i], containers[i]);
            final TabElement element = this.elements[i];
            tabSelectors[i].addMouseLeftClickListener(() -> {
                for (int j = 0; j < TabContainer.this.elements.length; j++) {
                    TabContainer.this.elements[j].unPush();
                }
                element.push();
                TabContainer.this.selected = element;
            });
            this.selected = this.elements[0];
        }
    }

    @Override
    protected void delete() {
        for (TabElement element : this.elements) {
            element.delete();
        }
    }

    @Override
    protected void highlightImpl(final boolean highlightState) {
    }

    @Override
    protected void showImpl() {
        this.background.show();
        for (int i = 0; i < this.elements.length; i++) {
            this.elements[i].setTitleVisible(true);
        }
        this.selected.push();
    }

    @Override
    protected void hideImpl() {
        this.background.hide();
        for (int i = 0; i < this.elements.length; i++) {
            this.elements[i].setTitleVisible(false);
            this.elements[i].setPanelVisible(false);
        }
    }

    @Override
    protected void setSizeImpl(final int newWidth, final int newHeight) {
        // FIXME implements
    }

    @Override
    protected Element setPositionImpl(final int newLeft, final int newTop) {
        // FIXME implements
        return this;
    }

    public GuiContainer getContainer(final int tab) {
        return this.elements[tab].getContainer();
    }
}
