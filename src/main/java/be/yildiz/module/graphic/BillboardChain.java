//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.module.graphic;

import be.yildiz.common.vector.Point3D;

/**
 * Billboards attached together.
 *
 * @author Grégory Van den Borre
 */
public interface BillboardChain {

    /**
     * Set the billboards material.
     *
     * @param material New material to use.
     */
    void setMaterial(Material material);

    /**
     * Set the chain position.
     *
     * @param position New chain position.
     */
    void setPosition(Point3D position);

    /**
     * Add a new element to the chain.
     *
     * @param pos          Element position.
     * @param elementWidth Billboard width.
     */
    void addElement(Point3D pos, float elementWidth);

    /**
     * Set an element position.
     *
     * @param listPos Element in the chain.
     * @param pos     Element new position.
     */
    void setElementPosition(int listPos, Point3D pos);

    void stop();

    void start();

    void setPosition(Point3D position, Point3D position2);

    /**
     * Delete the object.
     */
    void delete();

}
