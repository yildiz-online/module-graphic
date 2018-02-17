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

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.util.Util;
import be.yildizgames.module.color.Color;

/**
 * Emitter for a particle system.
 *
 * @author Grégory Van den Borre
 */
public abstract class ParticleEmitter {

    /**
     * Emitting angle in degree, 0 will emit only in the emitter direction will 180 will emit in any direction.
     */
    private float angle;
    /**
     * How many particle emitted per second.
     */
    private float rate;
    /**
     * Emitter activity in second, 0 is infinite duration.
     */
    private float duration;
    /**
     * Particle life in second.
     */
    private float lifeTime;
    /**
     * Emitter direction.
     */
    private Point3D direction;
    /**
     * Particle minimum speed.
     */
    private float minSpeed;
    /**
     * Particle maximum speed.
     */
    private float maxSpeed;
    /**
     * Initial particle color.
     */
    private Color startColor;
    /**
     * Final particle color.
     */
    private Color endColor;
    /**
     * Time before the emitter restart after finishing its job.
     */
    private float repeatDelay;
    /**
     * Flag to check if started or not.
     */
    private boolean started = true;

    /**
     * Simple constructor.
     */
    protected ParticleEmitter() {
        super();
        // FIXME the attributes values are not coherent with the implementation
        // default values(i.e Ogre rate is 10, here it is 0), use a constructor
        // in child to init values in this class.
    }

    /**
     * Set the emitter angle.
     *
     * @param newAngle New angle value in degree, must be between 0 and 180.
     */
    public final void setAngle(final float newAngle) {
        this.angle = Util.setValue(newAngle, 0, 180);
        this.setAngleImpl(this.angle);
    }

    /**
     * Set the emission rate per second, minimum value is 0.
     *
     * @param newRate Number of particles emitted per second.
     * @return This object.
     */
    public final ParticleEmitter setRate(final float newRate) {
        this.rate = Util.setPositiveValue(newRate);
        this.setRateImpl(this.rate);
        return this;
    }

    /**
     * Set the emission duration, in second, minimum value is 0. 0 is infinite duration.
     *
     * @param newDuration New emission duration value.
     */
    public final void setDuration(final float newDuration) {
        this.duration = Util.setPositiveValue(newDuration);
        this.setDurationImpl(this.duration);
    }

    /**
     * Set the time a particle live, in seconds.
     *
     * @param lifeTime Particle life time.
     */
    public final void setLifeTime(final float lifeTime) {
        this.lifeTime = lifeTime;
        this.setLifeTimeImpl(lifeTime);
    }

    /**
     * Change the emitter direction.
     *
     * @param direction Emitter direction, it is a copy, modifying it from outside wont affect the emitter direction.
     */
    public final void setDirection(final Point3D direction) {
        this.direction = direction;
        this.setDirectionImpl(direction);
    }

    /**
     * Set the particle minimum speed.
     *
     * @param minSpeed Particle minimum speed value.
     */
    public final void setMinSpeed(final float minSpeed) {
        this.minSpeed = minSpeed;
        this.setMinSpeedImpl(minSpeed);
    }

    /**
     * Set the particle maximum speed.
     *
     * @param maxSpeed Particle maximum speed value.
     */
    public final void setMaxSpeed(final float maxSpeed) {
        this.maxSpeed = maxSpeed;
        this.setMaxSpeedImpl(maxSpeed);
    }

    /**
     * Set the particle color when created.
     *
     * @param start Particle color at the beginning of their life.
     */
    public final void setStartColor(final Color start) {
        this.startColor = start;
        this.setStartColorImpl(start);
    }

    /**
     * Set the particle color when dying.
     *
     * @param end Particle color at the end of their life.
     */
    public final void setEndColor(final Color end) {
        this.endColor = end;
        this.setEndColorImpl(end);
    }

    /**
     * Set the time to wait for the emitter restart when it has finished its job.
     *
     * @param repeatDelay Time to wait.
     */
    public final void setRepeatDelay(final float repeatDelay) {
        this.repeatDelay = repeatDelay;
        this.setRepeatDelayImpl(repeatDelay);
    }

    /**
     * Stop the emission by setting the rate at 0, call start to restart it.
     */
    public final void stop() {
        if (this.started) {
            // call impl to avoid overwriting rate value.
            // FIXME use setEmitting(false)
            this.setRateImpl(0);
            this.started = false;
        }
    }

    /**
     * Restart the emission if it has been stopped by the stop method.
     */
    public final void start() {
        // FIXME use setEmitting(true)
        if (!this.started) {
            this.setRateImpl(this.rate);
            this.started = true;
        }
    }

    /**
     * Set a constant speed for the emission.
     *
     * @param speed The particles speed.
     */
    public final void setSpeed(final float speed) {
        this.setMinSpeed(speed);
        this.setMaxSpeed(speed);
    }

    public float getAngle() {
        return angle;
    }

    public float getRate() {
        return rate;
    }

    public float getDuration() {
        return duration;
    }

    public float getLifeTime() {
        return lifeTime;
    }

    public Point3D getDirection() {
        return direction;
    }

    public float getMinSpeed() {
        return minSpeed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public Color getStartColor() {
        return startColor;
    }

    public Color getEndColor() {
        return endColor;
    }

    public float getRepeatDelay() {
        return repeatDelay;
    }

    /**
     * Set the angle in implementation.
     *
     * @param angle New angle value.
     */
    protected abstract void setAngleImpl(float angle);

    /**
     * Set the delay in implementation.
     *
     * @param repeatDelay new Delay value.
     */
    protected abstract void setRepeatDelayImpl(float repeatDelay);

    /**
     * Set the end color in implementation.
     *
     * @param end New color value.
     */
    protected abstract void setEndColorImpl(Color end);

    /**
     * Set the minimum speed in implementation.
     *
     * @param minSpeed New speed value.
     */
    protected abstract void setMinSpeedImpl(float minSpeed);

    /**
     * Set the maximum speed in implementation.
     *
     * @param maxSpeed New speed value.
     */
    protected abstract void setMaxSpeedImpl(float maxSpeed);

    /**
     * Set the start color in implementation.
     *
     * @param start New start color.
     */
    protected abstract void setStartColorImpl(Color start);

    /**
     * Set the direction in implementation.
     *
     * @param direction New direction value.
     */
    protected abstract void setDirectionImpl(Point3D direction);

    /**
     * Set the duration in implementation.
     *
     * @param duration New duration value.
     */
    protected abstract void setDurationImpl(float duration);

    /**
     * Set the rate in implementation.
     *
     * @param rate New rate value.
     */
    protected abstract void setRateImpl(float rate);

    /**
     * Set the life time in implementation.
     *
     * @param lifeTime New lifeTime value.
     */
    protected abstract void setLifeTimeImpl(float lifeTime);

    /**
     * Possible emitter types.
     *
     * @author Van Den Borre Grégory
     */
    public enum EmitterType {

        /**
         * Emit from a single point.
         */
        POINT
    }
}
