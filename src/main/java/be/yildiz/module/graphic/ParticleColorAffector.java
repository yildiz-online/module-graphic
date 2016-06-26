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
 * Affect a particle system to change the particles color.
 *
 * @author Grégory Van Den Borre
 */
public abstract class ParticleColorAffector {

    /**
     * Modify the alpha value.
     */
    private int alphaVariation;

    /**
     * Simple constructor, only to be called from child.
     */
    protected ParticleColorAffector() {
        super();
    }

    /**
     * @return The alpha modifier factor.
     */
    public final int getAlphaVariation() {
        return this.alphaVariation;
    }

    /**
     * Create an alpha variation.
     *
     * @param variation Variation value per second, in percent.
     */
    public final void setAlphaVariation(final int variation) {
        this.alphaVariation = variation;
        this.setAlphaVariationImpl(variation);
    }

    /**
     * Create an alpha variation in implementation.
     *
     * @param variation Variation strength.
     */
    protected abstract void setAlphaVariationImpl(int variation);

}
