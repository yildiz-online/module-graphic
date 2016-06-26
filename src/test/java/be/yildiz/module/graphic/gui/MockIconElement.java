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

package be.yildiz.module.graphic.gui;

import be.yildiz.common.BaseCoordinate;
import be.yildiz.module.graphic.Material;

/**
 * @author Grégory Van den Borre
 */
public class MockIconElement extends AbstractIconElement {

    protected MockIconElement(String name, BaseCoordinate coordinates, Material iconMaterial) {
        super(name, coordinates, iconMaterial);
    }

    @Override
    protected void rotateRadian(float value) {

    }

    @Override
    protected int getZ() {
        return 0;
    }

    @Override
    protected void setMaterialImpl(Material newMaterial) {

    }

    @Override
    protected String getParentName() {
        return null;
    }

    @Override
    protected void delete() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void showImpl() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void hideImpl() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void setSizeImpl(int newWidth, int newHeight) {
        // TODO Auto-generated method stub

    }

    @Override
    protected Element setPositionImpl(int newLeft, int newTop) {
        return this;
    }

}
