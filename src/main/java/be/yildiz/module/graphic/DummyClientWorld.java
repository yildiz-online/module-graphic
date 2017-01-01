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

import be.yildiz.common.Color;
import be.yildiz.common.id.EntityId;
import be.yildiz.common.shape.Box;
import be.yildiz.common.shape.Plane;
import be.yildiz.common.shape.Sphere;
import be.yildiz.common.vector.Point3D;

/**
 * @author Grégory Van den Borre
 */
public class DummyClientWorld implements ClientWorld {
    @Override
    public ClientGameEntity createMovableDoodad(Box box, Material material) {
        return null;
    }

    @Override
    public ClientGameEntity createMovableDoodad(Sphere sphere, Material material) {
        return null;
    }

    @Override
    public ClientGameEntity createMovableDoodad(Plane plane, Material material) {
        return null;
    }

    @Override
    public ClientGameEntity createMovableDoodad(GraphicMesh mesh) {
        return null;
    }

    @Override
    public ClientGameEntity createStaticDoodad(Box box, Material material, Point3D position, Point3D direction) {
        return null;
    }

    @Override
    public ClientGameEntity createStaticDoodad(Plane plane, Material material, Point3D position, Point3D direction) {
        return null;
    }

    @Override
    public ClientGameEntity createStaticDoodad(Sphere sphere, Material material, Point3D position, Point3D direction) {
        return null;
    }

    @Override
    public ClientGameEntity createStaticDoodad(Sphere sphere, Material material, Point3D position) {
        return null;
    }

    @Override
    public ClientGameEntity createStaticDoodad(GraphicMesh mesh, Point3D position, Point3D direction) {
        return null;
    }

    @Override
    public ClientGameEntity createStaticObject(EntityId id, Box box, Material material, Point3D position, Point3D direction) {
        return null;
    }

    @Override
    public ClientGameEntity createStaticObject(EntityId id, Sphere sphere, Material material, Point3D position, Point3D direction) {
        return null;
    }

    @Override
    public ClientGameEntity createStaticObject(EntityId id, GraphicMesh shape, Point3D position) {
        return null;
    }

    @Override
    public ClientGameEntity createMovableObject(EntityId id, Box box, Material material, Point3D position) {
        return null;
    }

    @Override
    public ClientGameEntity createMovableObject(EntityId id, Sphere sphere, Material material, Point3D position) {
        return null;
    }

    @Override
    public ClientGameEntity createMovableObject(EntityId id, GraphicMesh shape, Point3D position) {
        return null;
    }

    @Override
    public AbstractCamera createCamera(String name) {
        return null;
    }

    @Override
    public void setSkybox(Skybox sky) {

    }

    @Override
    public void setDebugMode() {

    }

    @Override
    public void setAmbientLight(Color color) {

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
    public AbstractParticleSystem createParticleSystem() {
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
    public AbstractCamera getDefaultCamera() {
        return null;
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

    }

    @Override
    public boolean isDebug() {
        return false;
    }

    @Override
    public void deleteLight(AbstractLight light) {

    }

    @Override
    public AbstractCamera getCamera(String name) {
        return null;
    }

    @Override
    public AbstractLight getLight(String name) {
        return null;
    }

    @Override
    public void deleteLight(String name) {

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
    public ClientGameEntity createStaticObject(EntityId id, GraphicMesh mesh, Point3D position, Point3D direction) {
        return null;
    }
}
