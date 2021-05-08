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

package be.yildizgames.module.graphic.billboard;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.color.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BillboardTest {


    @Nested
    public class SetSize {

        @Test
        public void happyFlow() {
            Billboard b = new BillboardDummy();
            b.setSize(5,6);
            Assertions.assertEquals(5, b.getWidth());
            Assertions.assertEquals(6, b.getHeight());
        }

        @Disabled
        @Test
        public void zeroHeight() {
            Billboard b = new BillboardDummy();
            Assertions.assertThrows(IllegalArgumentException.class, () -> b.setSize(5, 0));
        }

        @Disabled
        @Test
        public void zeroWidth() {
            Billboard b =new BillboardDummy();
            Assertions.assertThrows(IllegalArgumentException.class, () -> b.setSize(0, 6));
        }

        @Test
        public void negativeHeight() {
            Billboard b = new BillboardDummy();
            Assertions.assertThrows(IllegalArgumentException.class, () -> b.setSize(5, -1));
        }

        @Test
        public void negativeWidth() {
            Billboard b = new BillboardDummy();
            Assertions.assertThrows(IllegalArgumentException.class, () -> b.setSize(-1, 6));
        }
    }

    @Nested
    public class SetPosition {

        @Test
        public void happyFlow() {
            BillboardDummy b = new BillboardDummy();
            Point3D pos = Point3D.valueOf(4,5,6);
            b.setPosition(pos);
            Assertions.assertEquals(pos, b.p);
        }

        @Test
        public void nullParam() {
            Billboard b = new BillboardDummy();
            Assertions.assertThrows(NullPointerException.class, () -> b.setPosition(null));
        }

    }

    public class BillboardDummy extends Billboard {

        private Point3D p;

        @Override
        public void setColor(Color color) {
            //Empty
        }

        @Override
        protected void setPositionImpl(Point3D position) {
            this.p = position;
        }

        @Override
           protected void setSizeImpl(float newWidth, float newHeight) {
            //Empty
        }
    }

}
