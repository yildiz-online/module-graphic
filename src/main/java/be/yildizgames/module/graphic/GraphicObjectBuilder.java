/*
 *
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 *
 */

package be.yildizgames.module.graphic;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.shape.Box;
import be.yildizgames.common.shape.Plane;
import be.yildizgames.common.shape.Sphere;
import be.yildizgames.module.graphic.material.Material;

public abstract class GraphicObjectBuilder {

    protected EntityId id = EntityId.WORLD;

    protected GraphicMesh mesh;

    protected Box box;

    protected Sphere sphere;

    protected Plane plane;

    protected Point3D position = Point3D.ZERO;

    protected Point3D direction = Point3D.BASE_DIRECTION;

    protected Material material = Material.empty();

    public final GraphicObjectBuilder withId(EntityId id) {
        this.id = id;
        return this;
    }

    public final GraphicObjectBuilder withShape(Box box) {
        this.box = box;
        return this;
    }

    public final GraphicObjectBuilder withShape(GraphicMesh mesh) {
        this.mesh = mesh;
        return this;
    }

    public final GraphicObjectBuilder withShape(Sphere sphere) {
        this.sphere = sphere;
        return this;
    }

    public GraphicObjectBuilder withShape(Plane plane) {
        this.plane = plane;
        return this;
    }

    public GraphicObjectBuilder atPosition(Point3D position) {
        this.position = position;
        return this;
    }

    public GraphicObjectBuilder withDirection(Point3D direction) {
        this.direction = direction;
        return this;
    }

    public GraphicObjectBuilder withMaterial(Material material) {
        this.material = material;
        return this;
    }

    public abstract GraphicObject buildMovable();

    public abstract GraphicObject buildStatic();
}
