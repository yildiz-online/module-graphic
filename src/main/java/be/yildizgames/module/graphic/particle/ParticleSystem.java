/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2018 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
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

package be.yildizgames.module.graphic.particle;

import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.particle.ParticleEmitter.EmitterType;

import java.util.ArrayList;
import java.util.List;

/**
 * Particle system, contains the material use by the particles, their size, the quota and a list of emitters.
 *
 * @author Grégory Van den Borre
 */
public abstract class ParticleSystem implements Movable {

    /**
     * List of particle emitter.
     */
    private final List<ParticleEmitter> emitterList = new ArrayList<>();
    /**
     * Material used for the particles.
     */
    private Material material;
    /**
     * Maximum number of particle at one time.
     */
    private int quota;

    /**
     * Current orientation, facing camera is default.
     */
    private Orientation orientation = ParticleSystem.Orientation.FACING_CAMERA;

    /**
     * Particle origin when generated.
     */
    private Origin origin = Origin.CENTER;

    /**
     * Protected constructor, only called from children.
     */
    protected ParticleSystem() {
        super();
    }

    /**
     * Create a new particle emitter and add it to the list.
     *
     * @param type Type of the emitter.
     * @return the newly built particle emitter.
     */
    public final ParticleEmitter addEmitter(final EmitterType type) {
        final ParticleEmitter emitter = this.createEmitter(type);
        this.emitterList.add(emitter);
        return emitter;
    }

    /**
     * Create a color affector.
     *
     * @return The newly created color affector.
     */
    public final ParticleColorAffector addColorAffector() {
        return this.createColorAffector();
    }

    /**
     * Create a force affector.
     *
     * @return The newly created force affector.
     */
    public final ParticleForceAffector addForceAffector() {
        return this.createForceAffector();
    }

    /**
     * Create a scale affector.
     *
     * @return The newly created scale affector.
     */
    public final ParticleScaleAffector addScaleAffector() {
        return this.createScaleAffector();
    }

    /**
     * Stops all emitters.
     */
    public final void stop() {
        this.emitterList.forEach(ParticleEmitter::stop);
    }

    /**
     * Starts all emitters.
     */
    public final void start() {
        this.emitterList.forEach(ParticleEmitter::start);
    }

    public final Material getMaterial() {
        return material;
    }

    public final int getQuota() {
        return quota;
    }

    public final Orientation getOrientation() {
        return orientation;
    }

    public final Origin getOrigin() {
        return origin;
    }

    /**
     * Create a new emitter in implementation.
     *
     * @param type Emitter type.
     * @return The built emitter.
     */
    protected abstract ParticleEmitter createEmitter(final ParticleEmitter.EmitterType type);

    /**
     * Create a color affector in implementation.
     *
     * @return The newly created color affector.
     */
    protected abstract ParticleColorAffector createColorAffector();

    /**
     * Create a force affector in implementation.
     *
     * @return The newly created force affector.
     */
    protected abstract ParticleForceAffector createForceAffector();

    /**
     * Create a scale affector in implementation.
     *
     * @return The newly created scale affector.
     */
    protected abstract ParticleScaleAffector createScaleAffector();

    /**
     * @param keep <code>true</code> To keep particles in local space.
     */
    public abstract void keepInLocalSpace(boolean keep);

    /**
     * Set the particle origin.
     *
     * @param origin new particle origin.
     * @return This.
     */
    public final ParticleSystem setOrigin(final Origin origin) {
        if (!this.origin.equals(origin)) {
            this.origin = origin;
            this.setOriginImpl(origin);
        }
        return this;
    }

    /**
     * Set a material for the particles.
     *
     * @param material New material.
     * @return This.
     */
    public final ParticleSystem setMaterial(final Material material) {
        this.material = material;
        this.setMaterialImpl(material);
        return this;
    }

    /**
     * Set the maximum number of particle at one time for this system.
     *
     * @param newQuota New quota value.
     * @return This.
     */
    public final ParticleSystem setQuota(final int newQuota) {
        this.quota = newQuota;
        this.setQuotaImpl(newQuota);
        return this;
    }

    /**
     * Set the size for the particles.
     *
     * @param newWidth  New width for the particles.
     * @param newHeight New height for the particles.
     * @return This.
     */
    public final ParticleSystem setSize(final float newWidth, final float newHeight) {
        this.setSizeImpl(newWidth, newHeight);
        return this;
    }

    public final ParticleSystem setSize(final Size size) {
        this.setSizeImpl(size.width, size.height);
        return this;
    }

    /**
     * Set the particle orientation type.
     *
     * @param newOrientation New orientation.
     * @return This.
     */
    public final ParticleSystem setOrientation(final Orientation newOrientation) {
        this.orientation = newOrientation;
        this.setOrientationImpl(newOrientation);
        return this;
    }

    /**
     * Get the emitter for the given index.
     *
     * @param index Index of the emitter to retrieve.
     * @return The Emitter matching the given index.
     */
    public final ParticleEmitter getEmitter(final int index) {
        return this.emitterList.get(index);
    }

    /**
     * Set the particle orientation type in implementation.
     *
     * @param newOrientation New orientation value.
     */
    protected abstract void setOrientationImpl(Orientation newOrientation);

    /**
     * set the particle size in implementation.
     *
     * @param width  New width value.
     * @param height New height value.
     */
    protected abstract void setSizeImpl(float width, float height);

    /**
     * Set the material in implementation.
     *
     * @param newMaterial New material to use.
     */
    protected abstract void setMaterialImpl(Material newMaterial);

    /**
     * Set the quota value in implementation.
     *
     * @param newQuota New quota value.
     */
    protected abstract void setQuotaImpl(int newQuota);

    /**
     * Implementation specific to set the particle origin.
     *
     * @param origin Particle origin.
     */
    protected abstract void setOriginImpl(Origin origin);

    public abstract void hide();

    public abstract void show();

    public abstract void rotate(float yaw, float pitch);

    /**
     * Particle orientation possibilities.
     *
     * @author Van Den Borre Grégory
     * @version 1.0
     * @since 0.9(14 / 03 / 2011)
     */
    public enum Orientation {

        /**
         * The particles always face the camera.
         */
        FACING_CAMERA,

        /**
         * The particles are oriented around a shared direction vector (used as Y axis) and only rotate around this to face the camera.
         */
        ORIENTED_COMMON,
        /**
         * The particles have their own orientation.
         */
        ORIENTED_SELF,

        /**
         * Particles are perpendicular to a common, typically fixed direction vector, which acts as their local Z axis, and their local Y axis coplanar with common direction and the common up vector.
         * The billboard never rotates to face the camera, you might use double-side material to ensure particles never culled by back-facing. Good for aureolas, rings etc where the particles will
         * perpendicular to the ground - this is slightly faster than perpendicular_self.
         */
        PERPENDICULAR_COMMON,

        /**
         * Particles are perpendicular to their own direction vector, which acts as their local Z axis, and their local Y axis coplanar with their own direction vector and the common up vector. The
         * billboard never rotates to face the camera, you might use double-side material to ensure particles never culled by back-facing. Good for rings stack etc where the particles will
         * perpendicular to their traveling direction.
         */
        PERPENDICULAR_SELF
    }

    /**
     * Particle origin.
     *
     * @author Grégory Van den Borre
     */
    public enum Origin {

        /**
         * The billboard origin is the center of bottom edge.
         */
        BOTTOM_CENTER(0),

        /**
         * The billboard origin is the bottom-left corner.
         */
        BOTTOM_LEFT(1),

        /**
         * The billboard origin is the bottom-right corner.
         */
        BOTTOM_RIGHT(2),

        /**
         * The billboard origin is the center.
         */
        CENTER(3),

        /**
         * The billboard origin is the center of left edge.
         */
        CENTER_LEFT(4),

        /**
         * The billboard origin is the center of right edge.
         */
        CENTER_RIGHT(5),

        /**
         * The billboard origin is the center of top edge.
         */
        TOP_CENTER(6),

        /**
         * The billboard origin is the top-left corner.
         */
        TOP_LEFT(7),

        /**
         * The billboard origin is the top-right corner.
         */
        TOP_RIGHT(8);

        /**
         * Associated value to avoid to rely on enumeration order.
         */
        private final int value;

        /**
         * Full constructor.
         *
         * @param value Associated value.
         */
        Origin(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
