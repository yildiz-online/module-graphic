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

package be.yildizgames.module.graphic.query;

import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.common.geometry.Rectangle;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.module.window.input.MousePosition;

import java.util.List;
import java.util.Optional;

/**
 * Result of a ray trace, try to retrieve an entity.
 * @author Grégory Van den Borre
 */
public interface Query {

    /**
     * Find an entity under the mouse position.
     * @param p Mouse position.
     * @return An optional entity.
     */
    default Optional<EntityId> getEntity(MousePosition p) {
        return getEntity(p.getX(), p.getY());
    }

    /**
     * Find an entity under a screen position.
     * @param x Screen x position.
     * @param y Screen y position.
     * @return An optional entity.
     */
    Optional<EntityId> getEntity(float x, float y);

    /**
     * Find all the entities contained in a rectangle facing the camera.
     * @param r Recrangle to use.
     * @return All the entities in the rectangle.
     */
    List<EntityId> getEntities(Rectangle r);
    
}
