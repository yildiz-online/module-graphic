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

/**
 * Base class for graphic objects.
 *
 * @author Grégory Van den Borre
 */
public abstract class BaseGraphicObject implements ClientGameEntity {

    /**
     * <true> If the object cast shadows.
     */
    private boolean castingShadow;

    /**
     * <true> Will render the object, <false> the object will never be visible.
     */
    private boolean visible;

    /**
     * Material used for this graphic object.
     */
    private Material material;

    /**
     * Simple constructor.
     */
    protected BaseGraphicObject() {
        super();
        this.visible = true;
    }

    /**
     * Set the object visible on screen.
     */
    @Override
    public final void show() {
        if (!this.visible) {
            this.visible = true;
            this.showImpl();
        }
    }

    /**
     * Set the object not visible on screen.
     */
    @Override
    public final void hide() {
        if (this.visible) {
            this.visible = false;
            this.hideImpl();
        }
    }

    @Override
    public final BaseGraphicObject setCastShadow(final boolean cast) {
        this.castingShadow = cast;
        this.castShadowImpl(cast);
        return this;
    }

    @Override
    public final boolean isCastingShadow() {
        return this.castingShadow;
    }

    @Override
    public final boolean isVisible() {
        return this.visible;
    }

    @Override
    public final ClientGameEntity setMaterial(final Material newMaterial) {
        if (!newMaterial.equals(this.material)) {
            this.material = newMaterial;
            this.setMaterialImpl(newMaterial);
        }
        return this;
    }

    /**
     * Set the material in implementation.
     *
     * @param newMaterial New material to use.
     */
    protected abstract void setMaterialImpl(Material newMaterial);

    /**
     * Set the object casting shadow in implementation.
     *
     * @param cast <code>true</code> to cast shadow, <code>false</code> to stop
     *             casting.
     */
    protected abstract void castShadowImpl(boolean cast);

    /**
     * Show the object in implementation.
     */
    protected abstract void showImpl();

    /**
     * Hide the object in implementation.
     */
    protected abstract void hideImpl();
}
