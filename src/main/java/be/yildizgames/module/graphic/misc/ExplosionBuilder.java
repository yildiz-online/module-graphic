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

import be.yildizgames.module.color.Color;
import be.yildizgames.module.graphic.GraphicWorld;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.particle.ParticleColorAffector;
import be.yildizgames.module.graphic.particle.ParticleEmitter;
import be.yildizgames.module.graphic.particle.ParticleScaleAffector;
import be.yildizgames.module.graphic.particle.ParticleSystem;

/**
 * Explosion animation.
 *
 * @author Grégory Van den Borre
 */
public class ExplosionBuilder {

    private final FlamesBuilder flameBuilder;

    private final SmokeBuilder smokeBuilder;

    private final FlashBuilder flashBuilder;

    private final SparkBuilder sparkBuilder;

    public ExplosionBuilder(final GraphicWorld world) {
        super();
        this.flameBuilder = new FlamesBuilder(world.createParticleSystem());
        this.smokeBuilder = new SmokeBuilder(world.createParticleSystem());
        this.flashBuilder = new FlashBuilder(world.createParticleSystem());
        this.sparkBuilder = new SparkBuilder(world.createParticleSystem());
    }

    public FlamesBuilder withFlames() {
        return this.flameBuilder;
    }

    public Explosion build() {
        return new Explosion(this.flameBuilder.flames, this.smokeBuilder.smoke, this.flashBuilder.flash,this.sparkBuilder.spark);
    }

    public class FlamesBuilder {

        private final ParticleSystem flames;

        private FlamesBuilder(ParticleSystem flames) {
            this.flames = flames;
            this.flames.setQuota(15);
            this.flames.setMaterial(Material.empty());
            this.flames.setSize(50, 50);
            final ParticleEmitter flamesEmitter = this.flames.addEmitter(ParticleEmitter.EmitterType.POINT);
            flamesEmitter.setAngle(180);
            flamesEmitter.setStartColor(Color.ORANGE);
            flamesEmitter.setEndColor(Color.YELLOW);
            flamesEmitter.setRate(200);
            flamesEmitter.setMinSpeed(40);
            flamesEmitter.setMaxSpeed(50);
            flamesEmitter.setLifeTime(0.5f);
            flamesEmitter.setDuration(0.1f);
            final ParticleColorAffector flamesColorAffector = this.flames.addColorAffector();
            flamesColorAffector.setAlphaVariation(-80);
        }

        public FlamesBuilder withMaterial(final Material material) {
            this.flames.setMaterial(material);
            return this;
        }

        public ExplosionBuilder build() {
            return ExplosionBuilder.this;
        }
    }

    public class SmokeBuilder {

        private final ParticleSystem smoke;

        private SmokeBuilder(ParticleSystem smoke) {
            this.smoke = smoke;
            this.smoke.setQuota(12);
            this.smoke.setSize(50, 50);
            this.smoke.setMaterial(Material.empty());

            final ParticleEmitter smokeEmitter = this.smoke.addEmitter(ParticleEmitter.EmitterType.POINT);
            smokeEmitter.setStartColor(Color.YELLOW);
            smokeEmitter.setEndColor(Color.ORANGE);
            smokeEmitter.setAngle(180);
            smokeEmitter.setRate(200);
            smokeEmitter.setMinSpeed(50);
            smokeEmitter.setMaxSpeed(60);
            smokeEmitter.setLifeTime(0.4f);
            smokeEmitter.setDuration(0.1f);
            final ParticleColorAffector smokeColorAffector = this.smoke.addColorAffector();
            smokeColorAffector.setAlphaVariation(-60);
        }

        public SmokeBuilder withMaterial(final Material material) {
            this.smoke.setMaterial(material);
            return this;
        }

        public ExplosionBuilder build() {
            return ExplosionBuilder.this;
        }
    }

    public class FlashBuilder {

        private final ParticleSystem flash;

        private FlashBuilder(ParticleSystem flash) {
            this.flash = flash;
            this.flash.setQuota(1);
            this.flash.setSize(80, 80);
            this.flash.setMaterial(Material.empty());
            final ParticleEmitter flashEmitter = this.flash.addEmitter(ParticleEmitter.EmitterType.POINT);
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

        public FlashBuilder withMaterial(final Material material) {
            this.flash.setMaterial(material);
            return this;
        }

    }

    public class SparkBuilder {

        private final ParticleSystem spark;

        private SparkBuilder(ParticleSystem spark) {
            this.spark = spark;
            this.spark.setOrientation(ParticleSystem.Orientation.ORIENTED_SELF);
            this.spark.setQuota(50);
            this.spark.setSize(20, 20);
            this.spark.setMaterial(Material.get("spark1"));
            final ParticleEmitter sparkEmitter = this.spark.addEmitter(ParticleEmitter.EmitterType.POINT);
            sparkEmitter.setAngle(180);
            sparkEmitter.setStartColor(Color.ORANGE);
            sparkEmitter.setEndColor(Color.YELLOW);
            sparkEmitter.setRate(50);
            sparkEmitter.setMinSpeed(60);
            sparkEmitter.setMaxSpeed(80);
            sparkEmitter.setLifeTime(1f);
            sparkEmitter.setDuration(1f);
        }

        public SparkBuilder withMaterial(final Material material) {
            this.spark.setMaterial(material);
            return this;
        }
    }
}
