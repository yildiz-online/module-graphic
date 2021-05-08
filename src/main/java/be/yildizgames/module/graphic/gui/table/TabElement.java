/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE  SOFTWARE.
 */

package be.yildizgames.module.graphic.gui.table;

import be.yildizgames.module.graphic.gui.View;
import be.yildizgames.module.graphic.gui.button.Button;
import be.yildizgames.module.graphic.gui.container.Container;

import java.util.Objects;

/**
 * A tab element is part of a tab container. To add Widget, just retrieve the
 * container from getContainer() method and build them as usually.
 *
 * @author Grégory Van den Borre
 */
public final class TabElement {

    /**
     * Tab position in the list(first is 0).
     */
    private final int position;

    /**
     * View holding the Widgets contained in this tab.
     */
    private final View view;

    /**
     * GuiButton representing the tab.
     */
    private final Button titleTab;

    /**
     * Full constructor.
     *
     * @param position  Position of this tab in its TabContainer.
     * @param tab       GuiButton to set the tab visible and containing the tab title.
     * @param container View associated to this tab element, used to contain all
     *                  Widget.
     */
    TabElement(final int position, final Button tab, final View container) {
        super();
        this.position = position;
        this.titleTab = tab;
        this.view = container;
        this.view.show();
        this.view.hide();
    }

    /**
     * Set this tab being inactive.
     */
    void unPush() {
        this.titleTab.setUnpushed();
        this.setPanelVisible(false);
    }

    /**
     * Set this tab being the active one.
     */
    void push() {
        this.titleTab.setPushed();
        this.setPanelVisible(true);
    }

    /**
     * Set the tab button visible or not.
     *
     * @param visible <code>true</code> to show it, <code>false</code> to hide it.
     */
    void setTitleVisible(final boolean visible) {
        this.titleTab.setVisible(visible);
    }

    /**
     * Set the tab content visible or not.
     *
     * @param visible <code>true</code> to show the tab content, <code>false</code>
     *                to hide it.
     */
    void setPanelVisible(final boolean visible) {
        this.view.setVisible(visible);
    }

    /**
     * delete the tab button and all widgets contained in this element.
     */
    void delete() {
        //FIXME delete properly
        //this.titleTab.delete();
        this.view.delete();
    }

    /**
     * @return The tab position in the TabContainer(first one is 0).
     */
    int getPosition() {
        return this.position;
    }

    /**
     * @return The associated container to add widgets.
     */
    Container getContainer() {
        return this.view.getContainer();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.position);
    }

    @Override
    public boolean equals(final Object obj) {
        return Objects.equals(this, obj);
    }
}

