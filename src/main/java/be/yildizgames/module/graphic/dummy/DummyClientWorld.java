/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.module.graphic.dummy;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.shape.Box;
import be.yildizgames.common.shape.Plane;
import be.yildizgames.common.shape.Sphere;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.graphic.query.GroundQuery;
import be.yildizgames.module.graphic.query.Query;
import be.yildizgames.module.graphic.light.Light;
import be.yildizgames.module.graphic.particle.ParticleSystem;
import be.yildizgames.module.graphic.billboard.BillboardSet;
import be.yildizgames.module.graphic.camera.Camera;
import be.yildizgames.module.graphic.light.DirectionalLight;
import be.yildizgames.module.graphic.misc.ElectricArc;
import be.yildizgames.module.graphic.misc.Explosion;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.GraphicMesh;
import be.yildizgames.module.graphic.GraphicObject;
import be.yildizgames.module.graphic.GraphicWorld;
import be.yildizgames.module.graphic.light.LensFlare;
import be.yildizgames.module.graphic.misc.Line;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.misc.MovableText;
import be.yildizgames.module.graphic.misc.Ocean;
import be.yildizgames.module.graphic.light.PointLight;
import be.yildizgames.module.graphic.misc.Sky;
import be.yildizgames.module.graphic.misc.Skybox;
import be.yildizgames.module.graphic.light.SpotLight;
import be.yildizgames.module.graphic.RayProvider;

/**
 * @author Grégory Van den Borre
 */
public class DummyClientWorld implements GraphicWorld {

    private final GraphicObject dummyObject = new DummyGraphicObject();

    private final Camera dummyCamera = new DummyCamera();

    @Override
    public Query createQuery(RayProvider provider){ return null; }

    @Override
    public GroundQuery createGroundQuery(RayProvider provider) { return null; }

    @Override
    public GraphicObject createMovableDoodad(Box box, Material material) {
        return dummyObject;
    }

    @Override
    public GraphicObject createMovableDoodad(Sphere sphere, Material material) {
        return dummyObject;
    }

    @Override
    public GraphicObject createMovableDoodad(Plane plane, Material material) {
        return dummyObject;
    }

    @Override
    public GraphicObject createMovableDoodad(GraphicMesh mesh) {
        return dummyObject;
    }

    @Override
    public GraphicObject createStaticDoodad(Box box, Material material, Point3D position, Point3D direction) {
        return dummyObject;
    }

    @Override
    public GraphicObject createStaticDoodad(Plane plane, Material material, Point3D position, Point3D direction) {
        return dummyObject;
    }

    @Override
    public GraphicObject createStaticDoodad(Sphere sphere, Material material, Point3D position, Point3D direction) {
        return dummyObject;
    }

    @Override
    public GraphicObject createStaticDoodad(Sphere sphere, Material material, Point3D position) {
        return dummyObject;
    }

    @Override
    public GraphicObject createStaticDoodad(GraphicMesh mesh, Point3D position, Point3D direction) {
        return dummyObject;
    }

    @Override
    public GraphicObject createStaticObject(EntityId id, Box box, Material material, Point3D position, Point3D direction) {
        return dummyObject;
    }

    @Override
    public GraphicObject createStaticObject(EntityId id, Sphere sphere, Material material, Point3D position, Point3D direction) {
        return dummyObject;
    }

    @Override
    public GraphicObject createStaticObject(EntityId id, Plane plane, Material material, Point3D position, Point3D direction) {
        return dummyObject;
    }

    @Override
    public GraphicObject createStaticObject(EntityId id, GraphicMesh shape, Point3D position) {
        return dummyObject;
    }

    @Override
    public GraphicObject createMovableObject(EntityId id, Box box, Material material, Point3D position) {
        return dummyObject;
    }

    @Override
    public GraphicObject createMovableObject(EntityId id, Plane plane, Material material, Point3D position) {
        return dummyObject;
    }

    @Override
    public GraphicObject createMovableObject(EntityId id, Sphere sphere, Material material, Point3D position) {
        return dummyObject;
    }

    @Override
    public GraphicObject createMovableObject(EntityId id, GraphicMesh shape, Point3D position) {
        return dummyObject;
    }

    @Override
    public Camera createCamera(String name) {
        return dummyCamera;
    }

    @Override
    public void setSkybox(Skybox sky) {
        // does nothing.
    }

    @Override
    public void setDebugMode() {
        // does nothing.
    }

    @Override
    public void setAmbientLight(Color color) {
        // does nothing.
    }

    @Override
    public PointLight createPointLight(String name, Point3D position) {
        return null;
    }

    @Override
    public ElectricArc createElectricArc(Point3D origin, Point3D end, float width) {
        return null;
    }

    @Override
    public Explosion createExplosion() {
        return null;
    }

    @Override
    public ParticleSystem createParticleSystem() {
        return null;
    }

    @Override
    public Sky createSky() {
        return null;
    }

    @Override
    public Ocean createOcean() {
        return null;
    }

    @Override
    public Camera getDefaultCamera() {
        return dummyCamera;
    }

    @Override
    public Line create3DLine() {
        return null;
    }

    @Override
    public LensFlare createLensFlare(LensFlare.LensFlareMaterial mat, Point3D position) {
        return null;
    }

    @Override
    public SpotLight createSpotLight(String name, Point3D position, Point3D direction) {
        return null;
    }

    @Override
    public DirectionalLight createDirectionalLight(String name, Point3D position, Point3D direction) {
        return null;
    }

    @Override
    public void serializeShapeFromMesh(String mesh, String file, String name) {
        // does nothing.
    }

    @Override
    public boolean isDebug() {
        return false;
    }

    @Override
    public void deleteLight(Light light) {
        // does nothing.
    }

    @Override
    public Camera getCamera(String name) {
        return dummyCamera;
    }

    @Override
    public Light getLight(String name) {
        return null;
    }

    @Override
    public void deleteLight(String name) {
        // does nothing.
    }

    @Override
    public MovableText createMovableText(String name, String text, Font font) {
        return null;
    }

    @Override
    public BillboardSet createBillboardSet(Material material) {
        return null;
    }

    @Override
    public GraphicObject createStaticObject(EntityId id, GraphicMesh mesh, Point3D position, Point3D direction) {
        return this.dummyObject;
    }
}
