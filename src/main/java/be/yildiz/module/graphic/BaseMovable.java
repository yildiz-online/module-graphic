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

package be.yildiz.module.graphic;

import be.yildiz.common.gameobject.Movable;
import be.yildiz.common.vector.Point3D;
import lombok.Getter;

/**
 * @author Grégory Van den Borre
 */
public abstract class BaseMovable implements Movable, GraphicMovable {

    /**
     * Wrapped node.
     */
    @Getter
    private final Node node;

    /**
     * <code>true</code> if text is currently visible.
     */
    private boolean visible;

    /**
     * Full constructor.
     *
     * @param node Associated node.
     */
    protected BaseMovable(final Node node) {
        super();
        this.node = node;
        this.visible = true;
    }

    @Override
    public final void attachTo(final Movable other) {
        this.node.attachTo(((GraphicMovable) other).getNode());
    }

    @Override
    public final void attachToOptional(final Movable other) {
        this.node.attachToOptional(((GraphicMovable) other).getNode());
    }

    @Override
    public final Point3D getPosition() {
        return this.node.getPosition();
    }

    @Override
    public final void setPosition(final Point3D position) {
        this.node.setPosition(position);
    }

    @Override
    public final Point3D getAbsolutePosition() {
        return this.node.getAbsolutePosition();
    }

    @Override
    public final Point3D getDirection() {
        return this.node.getDirection();
    }

    @Override
    public final void setDirection(final Point3D direction) {
        this.node.setDirection(direction);
    }

    @Override
    public final Point3D getAbsoluteDirection() {
        return this.node.getWorldDirection();
    }

    @Override
    public final void delete() {
        this.node.delete();
    }

    /**
     * Set the object visible.
     */
    public final void show() {
        if (!this.visible) {
            this.showImpl();
            this.visible = true;
        }
    }

    /**
     * Set the object invisible.
     */
    public final void hide() {
        if (this.visible) {
            this.hideImpl();
            this.visible = false;
        }
    }

    /**
     * @return <code>true</code> if object is currently visible.
     */
    public final boolean isVisible() {
        return this.visible;
    }

    /**
     * Implementation specific show.
     */
    protected abstract void showImpl();

    /**
     * Implementation specific hide.
     */
    protected abstract void hideImpl();
}
