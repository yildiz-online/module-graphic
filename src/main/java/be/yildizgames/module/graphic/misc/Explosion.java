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

package be.yildizgames.module.graphic.misc;

import be.yildizgames.common.frame.EndFrameListener;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.graphic.animation.Animation;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.particle.ParticleColorAffector;
import be.yildizgames.module.graphic.particle.ParticleEmitter;
import be.yildizgames.module.graphic.particle.ParticleEmitter.EmitterType;
import be.yildizgames.module.graphic.particle.ParticleScaleAffector;
import be.yildizgames.module.graphic.particle.ParticleSystem;
import be.yildizgames.module.graphic.particle.ParticleSystem.Orientation;

/**
 * Explosion animation.
 *
 * @author Grégory Van den Borre
 */
public final class Explosion extends EndFrameListener implements Animation {

    /**
     * Time elapsed since the start.
     */
    private long runningTime;

    /**
     * Base flames.
     */
    private ParticleSystem smoke1;

    /**
     * Smoke.
     */
    private ParticleSystem smoke2;

    /**
     * Flash effect.
     */
    private ParticleSystem flash;

    /**
     * Sparks effect.
     */
    private ParticleSystem spark;

    private boolean playing = false;

    /**
     * Full constructor.
     *
     * @param smoke1 Smoke 1.
     * @param smoke2 Smoke 2.
     * @param flash  Flash.
     * @param spark  Spark.
     */
    public Explosion(final ParticleSystem smoke1, final ParticleSystem smoke2, final ParticleSystem flash, final ParticleSystem spark) {
        super();
        this.smoke1 = smoke1;
        this.smoke2 = smoke2;
        this.flash = flash;
        this.spark = spark;
    }

    /**
     * Set explosion position.
     *
     * @param position New position.
     */
    @Override
    public void setPosition(final Point3D position) {
        this.smoke1.setPosition(position);
        this.smoke2.setPosition(position);
        this.flash.setPosition(position);
        this.spark.setPosition(position);
    }

    @Override
    public void start() {
        this.playing = true;
        this.buildSmoke1();
        this.buildSmoke2();
        this.buildFlash();
        this.buildSpark();
    }

    /**
     * Build the flames.
     */
    private void buildSmoke1() {
        this.smoke1.setQuota(15);
        this.smoke1.setMaterial(Material.get("smoke1"));
        this.smoke1.setSize(50, 50);
        final ParticleEmitter smoke1Emitter = this.smoke1.addEmitter(EmitterType.POINT);
        smoke1Emitter.setAngle(180);
        smoke1Emitter.setStartColor(Color.ORANGE);
        smoke1Emitter.setEndColor(Color.YELLOW);
        smoke1Emitter.setRate(200);
        smoke1Emitter.setMinSpeed(40);
        smoke1Emitter.setMaxSpeed(50);
        smoke1Emitter.setLifeTime(0.5f);
        smoke1Emitter.setDuration(0.1f);
        final ParticleColorAffector smoke1ColorAffector = this.smoke1.addColorAffector();
        smoke1ColorAffector.setAlphaVariation(-80);
    }

    /**
     * Build the smoke.
     */
    private void buildSmoke2() {
        this.smoke2.setQuota(12);
        this.smoke2.setSize(50, 50);
        this.smoke2.setMaterial(Material.get("smoke2"));

        final ParticleEmitter smoke2Emitter = this.smoke2.addEmitter(EmitterType.POINT);
        smoke2Emitter.setStartColor(Color.YELLOW);
        smoke2Emitter.setEndColor(Color.ORANGE);
        smoke2Emitter.setAngle(180);
        smoke2Emitter.setRate(200);
        smoke2Emitter.setMinSpeed(50);
        smoke2Emitter.setMaxSpeed(60);
        smoke2Emitter.setLifeTime(0.4f);
        smoke2Emitter.setDuration(0.1f);
        final ParticleColorAffector smoke2ColorAffector = this.smoke2.addColorAffector();
        smoke2ColorAffector.setAlphaVariation(-60);
    }

    /**
     * Build the flash.
     */
    private void buildFlash() {
        this.flash.setQuota(1);
        this.flash.setSize(80, 80);
        //FIXME <HIGH> material name is hardcoded, what if material not loaded?
        this.flash.setMaterial(Material.get("flash1"));
        final ParticleEmitter flashEmitter = this.flash.addEmitter(EmitterType.POINT);
        flashEmitter.setRate(40);
        flashEmitter.setStartColor(Color.rgba(255, 255, 80, 255));
        flashEmitter.setEndColor(Color.rgba(255, 255, 80, 255));
        flashEmitter.setMinSpeed(0);
        flashEmitter.setMaxSpeed(0);
        flashEmitter.setLifeTime(0.3f);
        flashEmitter.setDuration(0.2f);
        final ParticleScaleAffector flashScaleAffector = this.flash.addScaleAffector();
        flashScaleAffector.setScale(200, 200);
    }

    /**
     * Build the sparks.
     */
    private void buildSpark() {
        this.spark.setOrientation(Orientation.ORIENTED_SELF);
        this.spark.setQuota(50);
        this.spark.setSize(20, 20);
        this.spark.setMaterial(Material.get("spark1"));
        final ParticleEmitter sparkEmitter = this.spark.addEmitter(EmitterType.POINT);
        sparkEmitter.setAngle(180);
        sparkEmitter.setStartColor(Color.ORANGE);
        sparkEmitter.setEndColor(Color.YELLOW);
        sparkEmitter.setRate(50);
        sparkEmitter.setMinSpeed(60);
        sparkEmitter.setMaxSpeed(80);
        sparkEmitter.setLifeTime(1f);
        sparkEmitter.setDuration(1f);
    }

    @Override
    public boolean frameEnded(final long time) {
        this.runningTime += time;
        if (this.runningTime > 3000) {
            this.runningTime = 0;
            this.playing = false;
            return false;
        }
        return true;
    }

    @Override
    public boolean isPlaying() {
        return this.playing;
    }
}
