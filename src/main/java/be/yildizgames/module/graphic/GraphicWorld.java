/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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

package be.yildizgames.module.graphic;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.graphic.billboard.BillboardSet;
import be.yildizgames.module.graphic.camera.Camera;
import be.yildizgames.module.graphic.light.DirectionalLight;
import be.yildizgames.module.graphic.light.LensFlare;
import be.yildizgames.module.graphic.light.LensFlare.LensFlareMaterial;
import be.yildizgames.module.graphic.light.Light;
import be.yildizgames.module.graphic.light.PointLight;
import be.yildizgames.module.graphic.light.SpotLight;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.misc.ElectricArc;
import be.yildizgames.module.graphic.misc.Explosion;
import be.yildizgames.module.graphic.misc.Line;
import be.yildizgames.module.graphic.misc.MovableText;
import be.yildizgames.module.graphic.misc.Ocean;
import be.yildizgames.module.graphic.misc.Sky;
import be.yildizgames.module.graphic.misc.SkyBox;
import be.yildizgames.module.graphic.particle.ParticleSystem;
import be.yildizgames.module.graphic.query.GroundQuery;
import be.yildizgames.module.graphic.query.Query;

/**
 * A world is a specific part of the game, all entities in a world will never have interactions with entities from other worlds. A world manage the physic properties(gravity, collisions...), a
 * ClientWorld manage also the graphic properties(camera, particles...).
 *
 * @author Grégory Van den Borre
 */
public interface GraphicWorld {

    /**
     * Create a new query from a provider.
     * @param provider Query provider.
     * @return The query.
     */
    Query createQuery(RayProvider provider);

    GroundQuery createGroundQuery(RayProvider provider);

    GraphicObjectBuilder createObject();

    /**
     * Create a new camera with default behavior(freefly).
     *
     * @param name Camera name, must be unique.
     * @return The newly built camera.
     */
    Camera createCamera(String name);

    /**
     * Build a sky box.
     *
     * @param sky The material to use.
     */
    void setSkybox(SkyBox sky);

    /**
     * Activate the debug mode on this world.
     */
    void setDebugMode();

    /**
     * Set the scene ambient light.
     *
     * @param color Light color.
     */
    void setAmbientLight(Color color);

    /**
     * Create a new light of type point.
     *
     * @param name     Name of the light, must be unique.
     * @param position Light position.
     * @return The newly built light.
     */
    PointLight createPointLight(String name, Point3D position);

    /**
     * Create an electric arc effect.
     *
     * @param origin Electric arc origin position.
     * @param end    Electric arc end position.
     * @param width  Elements width.
     * @return The created ElectricArc.
     */
    ElectricArc createElectricArc(Point3D origin, Point3D end, float width);

    /**
     * Create an explosion effect.
     *
     * @return The created Explosion.
     */
    Explosion createExplosion();

    /**
     * Create a particle system.
     *
     * @return The created ParticleSystem.
     */
    ParticleSystem createParticleSystem();

    /**
     * Create a sky environment.
     *
     * @return The created Sky.
     */
    Sky createSky();

    /**
     * Create an ocean environment.
     *
     * @return The created Ocean.
     */
    Ocean createOcean();

    /**
     * @return The camera built at same time as this world.
     */
    Camera getDefaultCamera();

    /**
     * Create a simple line.
     *
     * @return The created Line.
     */
    Line create3DLine();

    /**
     * Create a new lens flare.
     *
     * @param mat      Lensflare materials.
     * @param position Lens flare origin position.
     * @return The created LensFlare.
     */
    LensFlare createLensFlare(LensFlareMaterial mat, Point3D position);

    /**
     * Create a light of type spot light.
     *
     * @param name      Light name, must be unique.
     * @param position  Spot position.
     * @param direction Spot direction.
     * @return The created light.
     */
    SpotLight createSpotLight(String name, Point3D position, Point3D direction);

    /**
     * Create a light of type directional light.
     *
     * @param name      Light name, must be unique.
     * @param position  Light position.
     * @param direction Light direction.
     * @return The created light.
     */
    DirectionalLight createDirectionalLight(String name, Point3D position, Point3D direction);

    /**
     * Create a physic serialized shape from a graphic mesh and save it in a file.
     *
     * @param mesh Mesh to use to get the vertices data.
     * @param file File to save.
     * @param name Associated name.
     */
    void serializeShapeFromMesh(String mesh, String file, String name);

    /**
     * @return <code>true</code> if the world is in debug mode.
     */
    boolean isDebug();

    /**
     * Delete a light.
     *
     * @param light Light to delete.
     */
    void deleteLight(Light light);

    /**
     * Retrieve a Camera from its unique name.
     *
     * @param name Unique camera name.
     * @return Found camera.
     */
    Camera getCamera(final String name);

    /**
     * Retrieve a Light from its unique name.
     *
     * @param name Unique light name.
     * @return Found light.
     */
    Light getLight(final String name);

    /**
     * Unregister and delete a light.
     *
     * @param name Name of the light to delete.
     */
    void deleteLight(String name);

    /**
     * Create a HUD text that will be displayed next an entity.
     *
     * @param name Object unique name.
     * @param text Text to display.
     * @param font Font to use.
     * @return The created movable text.
     */
    MovableText createMovableText(String name, String text, Font font);

    default MovableText createMovableText(String text, Font font) {
        return createMovableText(StringUtil.buildRandomString("movableText"), text, font);
    }

    /**
     * Create a billboard set.
     *
     * @param material Material to use.
     * @return The created billboard set.
     */
    BillboardSet createBillboardSet(Material material);

    String getName();
}
