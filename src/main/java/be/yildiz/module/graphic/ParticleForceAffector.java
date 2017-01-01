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

package be.yildiz.module.graphic;

import be.yildiz.common.vector.Point3D;

/**
 * Force to apply on a particle emitter.
 *
 * @author Grégory Van Den Borre
 */

public abstract class ParticleForceAffector {

    /**
     * Force direction and strength.
     */
    private Point3D force = Point3D.ZERO;

    /**
     * Simple constructor.
     */
    protected ParticleForceAffector() {
        super();
    }

    /**
     * Set the force direction and strength.
     *
     * @param forceX Force to apply on X direction.
     * @param forceY Force to apply on Y direction.
     * @param forceZ Force to apply on Z direction.
     */
    public final void setForce(final float forceX, final float forceY, final float forceZ) {
        this.setForce(Point3D.xyz(forceX, forceY, forceZ));
    }

    /**
     * Set the force direction and strength.
     *
     * @param force Force to apply.
     */
    public final void setForce(final Point3D force) {
        this.force = force;
        this.setForce(this.force.x, this.force.y, this.force.z);
    }

    /**
     * Set the force in implementation.
     *
     * @param forceX Force to apply on X direction.
     * @param forceY Force to apply on Y direction.
     * @param forceZ Force to apply on Z direction.
     */
    protected abstract void setForceImpl(final float forceX, final float forceY, final float forceZ);
}
