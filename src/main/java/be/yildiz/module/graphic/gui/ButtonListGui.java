/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.module.graphic.gui;

import be.yildiz.common.client.gui.listener.MouseLeftClickListener;
import be.yildiz.common.client.gui.listener.MouseMoveListener;
import be.yildiz.common.collections.Lists;
import be.yildiz.common.vector.Point2D;

import java.util.List;
import java.util.Optional;

/**
 * @author Grégory Van den Borre
 */
public final class ButtonListGui extends ContainerChild implements ButtonList, OnMouseOverListener, MouseMoveListener, MouseLeftClickListener {

    /**
     * GuiButton to hover to display the list.
     */
    private final Button button;

    /**
     * Buffer of elements not yet registered, never displayed.
     */
    private final List<Button> elements = Lists.newList();

    /**
     * Registered elements, displayed if the list is opened.
     */
    private final List<Button> registeredElement = Lists.newList();

    /**
     * Current list state.
     */
    private boolean open = false;

    private Optional<Button> selection = Optional.empty();

    public ButtonListGui(final String name, final Button button, final List<Button> elements) {
        super(name, button.getCoordinates(), button.getParent());
        this.button = button;
        this.elements.addAll(elements);
        this.elements.forEach(Button::show);
        this.elements.forEach(Button::hide);
        this.button.addOnMouseOverListener(this);
        this.button.addMouseMoveListener(this);
        this.button.addMouseLeftClickListener(this);
    }

    @Override
    public void addElement(String caption, MouseLeftClickListener listener) {
        if (!this.elements.isEmpty()) {
            Button b = this.elements.remove(0);
            b.setCaptionText(caption);
            b.addMouseLeftClickListener(listener);
            b.setVisible(this.open);
            this.registeredElement.add(b);
        }
        this.elements.forEach(Button::hide);
    }

    @Override
    public void notify(boolean over, Point2D position) {
        if (over) {
            this.open = true;
            this.registeredElement.forEach(Button::show);
            this.setHeight((this.registeredElement.size() + 1) * this.button.getHeight());
            // FIXME only work if parent is the view, otherwise the view size is not updated, -> check contains return false -> make the virtual height bubble till the view with
            // getParent().getParent...
            this.button.setVirtualHeight(this.getHeight());
            //FIXME virtual heigh no longer needed as container.contains is no longer necessary to check a widget
            this.getParent().get().setVirtualHeight(this.getHeight());
            // FIXME dirty fix
            this.getParent().get().disableEmptyZone();
        } else {
            this.open = false;
            this.registeredElement.forEach(Button::hide);
            this.setHeight(this.button.getHeight());
            this.button.resetVirtualHeight();
            this.getParent().get().resetVirtualHeight();
            // FIXME dirty fix
            this.getParent().ifPresent(GuiContainer::enableEmptyZone);
        }

    }

    @Override
    protected void highlightImpl(boolean highlightState) {
    }

    @Override
    protected void delete() {
        this.button.delete();
        this.elements.forEach(Button::delete);
        this.registeredElement.forEach(Button::delete);
    }

    @Override
    protected void showImpl() {
        this.button.show();
        this.highlight(this.isMouseOver());
        if (this.open) {
            this.registeredElement.forEach(Button::show);
        } else {
            this.registeredElement.forEach(Button::hide);
        }
        this.elements.forEach(Button::hide);
    }

    @Override
    protected void hideImpl() {
        this.button.hide();
        this.registeredElement.forEach(Button::hide);
        this.selection = Optional.empty();
    }

    @Override
    protected void setSizeImpl(int newWidth, int newHeight) {
    }

    @Override
    protected Element setPositionImpl(int newLeft, int newTop) {
        this.button.setPosition(newLeft, newTop);
        for (int i = 0; i < this.elements.size(); i++) {
            this.elements.get(i).setPosition(newLeft, (i + 1) * this.button.getHeight() + newTop);
        }
        return this;
    }

    @Override
    public void move(Point2D position) {
        for (Button b : this.registeredElement) {
            boolean contains = b.contains(position);
            b.highlight(contains);
            if (contains) {
                this.selection = Optional.of(b);
            }
        }
    }

    @Override
    public void click() {
        this.selection.ifPresent(b -> b.mouseLeftClick(0, 0));
        this.button.setMouseOver(false, Point2D.ZERO);
    }

    @Override
    public boolean isEmpty() {
        return this.registeredElement.isEmpty();
    }

}
