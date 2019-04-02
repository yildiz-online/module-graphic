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
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.shape.Box;
import be.yildizgames.common.shape.Plane;
import be.yildizgames.common.shape.Sphere;
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
import be.yildizgames.module.graphic.misc.Skybox;
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

    /**
     * Create a movable graphic box, it has no Id, is not selectable and will not be affected in any way by physics. It is usually used to represent not selectable object like small animals,
     * unreachable background objects...
     *
     * @param box      Box to use as graphic resource.
     * @param material The material to set.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createMovableDoodad(Box box, Material material);

    /**
     * Create a movable graphic sphere, it has no Id, is not selectable and will not be affected in any way by physics. It is usually used to represent not selectable object like small animals,
     * unreachable background objects...
     *
     * @param sphere   Sphere to use as graphic resource.
     * @param material The material to set.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createMovableDoodad(Sphere sphere, Material material);

    /**
     * Create a movable graphic plane, it has no Id, is not selectable and will not be affected in any way by physics. It is usually used to represent not selectable object like small animals,
     * unreachable background objects...
     *
     * @param plane    Plane to use as graphic resource.
     * @param material The material to set.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createMovableDoodad(Plane plane, Material material);

    /**
     * Create a movable graphic object, it has no Id, is not selectable and will not be affected in any way by physics. It is usually used to represent not selectable object like small animals,
     * unreachable background objects...
     *
     * @param mesh Mesh to use as graphic resource.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createMovableDoodad(GraphicMesh mesh);

    /**
     * Create a static graphic box, it has no Id, is not selectable, cannot move, and will not be affected in any way by physics. It is usually used to represent unselectable object like small
     * vegetation, small rocks...
     *
     * @param box       Box to use as graphic resource.
     * @param material  The material to set.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticDoodad(Box box, Material material, Point3D position, Point3D direction);

    /**
     * Create a static graphic plane, it has no Id, is not selectable, cannot move, and will not be affected in any way by physics. It is usually used to represent unselectable object like small
     * vegetation, small rocks...
     *
     * @param plane     Plane to use as graphic resource.
     * @param material  The material to set.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticDoodad(Plane plane, Material material, Point3D position, Point3D direction);

    /**
     * Create a static graphic sphere, it has no Id, is not selectable, cannot move, and will not be affected in any way by physics. It is usually used to represent unselectable object like small
     * vegetation, small rocks...
     *
     * @param sphere    Sphere to use as graphic resource.
     * @param material  The material to set.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticDoodad(Sphere sphere, Material material, Point3D position, Point3D direction);

    /**
     * Create a static graphic sphere, it has no Id, is not selectable, cannot move, the direction is the base direction and will not be affected in any way by physics. It is usually used to represent
     * unselectable object like small vegetation, small rocks...
     *
     * @param sphere   Sphere to use as graphic resource.
     * @param material The material to set.
     * @param position Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticDoodad(Sphere sphere, Material material, Point3D position);

    /**
     * Create a static graphic object, it has no Id, is not selectable, cannot move, and will not be affected in any way by physics. It is usually used to represent unselectable object like small
     * vegetation, small rocks...
     *
     * @param mesh     Mesh to use as graphic resource.
     * @param position Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    default GraphicObject createStaticDoodad(GraphicMesh mesh, Point3D position) {
        return this.createStaticDoodad(mesh, position, Point3D.BASE_DIRECTION);
    }

    /**
     * Create a static graphic object, it has no Id, is not selectable, cannot move, and will not be affected in any way by physics. It is usually used to represent unselectable object like small
     * vegetation, small rocks...
     *
     * @param mesh      Mesh to use as graphic resource.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticDoodad(GraphicMesh mesh, Point3D position, Point3D direction);

    /**
     * Create a static graphic physic box, it has a given Id, is selectable, cannot move, and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent
     * selectable or collidable object like buildings, trees...
     *
     * @param id        id to retrieve when object is selected, has collisions...
     * @param box       Box to use as graphic and physic resource.
     * @param material  The material to set.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticObject(EntityId id, Box box, Material material, Point3D position, Point3D direction);

    /**
     * Create a static graphic physic sphere, it has a given Id, is selectable, cannot move, and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent
     * selectable or collidable object like buildings, trees...
     *
     * @param id        id to retrieve when object is selected, has collisions...
     * @param sphere    Sphere to use as graphic and physic resource.
     * @param material  The material to set.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticObject(EntityId id, Sphere sphere, Material material, Point3D position, Point3D direction);

    /**
     * Create a static graphic physic plane, it has a given Id, is selectable, cannot move, and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent
     * selectable or collidable object like buildings, trees...
     *
     * @param id        id to retrieve when object is selected, has collisions...
     * @param plane     Plane to use as graphic resource.
     * @param material  The material to set.
     * @param position  Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @param direction Object immutable direction, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticObject(EntityId id, Plane plane, Material material, Point3D position, Point3D direction);

    /**
     * Create a static graphic physic object, the physic object is a primitive instead of a mesh, to improve performances, it has a given Id, is selectable, cannot move, and will not be affected in
     * any way by physics . It is usually used to represent selectable object like buildings, trees...
     *
     * @param id       id to retrieve when object is selected, has collisions...
     * @param shape    Shape to use as graphic and physic resource, physic shape is a primitive instead of a mesh to improve performance.
     * @param position Object immutable position, InvalidParamException is thrown in case of null parameter.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticObject(EntityId id, GraphicMesh shape, Point3D position);

    /**
     * Create a movable graphic physic box, it has a given Id, is selectable and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent playable or
     * movable object like characters, vehicles...
     *
     * @param id       id to retrieve when object is selected, has collisions...
     * @param box      Box to use as graphic and physic resource.
     * @param material The material to set.
     * @param position Initial object position.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createMovableObject(EntityId id, Box box, Material material, Point3D position);

    /**
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createMovableObject(EntityId id, Plane plane, Material material, Point3D position);

    /**
     * Create a movable graphic physic sphere, it has a given Id, is selectable and will not be affected in any way by physics dynamic, but is collidable. It is usually used to represent playable or
     * movable object like characters, vehicles...
     *
     * @param id       id to retrieve when object is selected, has collisions...
     * @param sphere   Sphere to use as graphic and physic resource.
     * @param material The material to set.
     * @param position Initial object position.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createMovableObject(EntityId id, Sphere sphere, Material material, Point3D position);

    /**
     * Create a movable graphic object, it has a given Id, is selectable and will not be affected in any way by physics dynamic, and is not collidable. It is usually used to represent playable or
     * movable object like characters, vehicles...
     *
     * @param id       id to retrieve when object is selected.
     * @param shape    A container for a graphic mesh.
     * @param position Initial object position.
     * @return The built object.
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createMovableObject(EntityId id, GraphicMesh shape, Point3D position);

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
    void setSkybox(Skybox sky);

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

    /**
     * @deprecated Use createObject instead.
     */
    @Deprecated(forRemoval = true, since = "2.1.4")
    GraphicObject createStaticObject(final EntityId id, final GraphicMesh mesh, final Point3D position, final Point3D direction);

    String getName();
}
