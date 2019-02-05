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

package be.yildizgames.module.graphic.gui;

import be.yildizgames.common.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Grégory Van den Borre
 */
public final class ZOrderTest {

    @Test
    public void testHashCode() {
        assertEquals(new Zorder(12).hashCode(), new Zorder(12).hashCode());
        assertEquals(new Zorder(222).hashCode(), new Zorder(222).hashCode());
        assertEquals(new Zorder(473).hashCode(), new Zorder(473).hashCode());
        assertEquals(new Zorder(0).hashCode(), new Zorder(0).hashCode());
        assertNotEquals(new Zorder(17).hashCode(), new Zorder(16).hashCode());
    }

    @Test
    public void testZorderMin() {
        Zorder z = new Zorder(-1);
        assertEquals(new Zorder(0), z);
    }

    @Test
    public void testZorderMax() {
        Zorder z = new Zorder(651);
        assertEquals(new Zorder(650), z);
    }

    @Test
    public void testZorder() {
        assertEquals(new Zorder(640), Zorder.GUI);
        assertEquals(new Zorder(0), Zorder.ZERO);
    }

    @Test
   public void testAdd() {
        assertEquals(new Zorder(10), new Zorder(3).add(7));
    }

    @Test
    public void testCompareTo() {
        assertEquals(1, new Zorder(10).compareTo(new Zorder(11)));
        assertEquals(0, new Zorder(8).compareTo(new Zorder(8)));
        assertEquals(-1, new Zorder(200).compareTo(new Zorder(45)));
    }

    @Test
    public void testToString() {
        assertEquals("Z order:12", new Zorder(12).toString());
    }

    @Test
    public void testGetValue() {
        assertEquals(17, new Zorder(17).getValue());
    }

    @Test
    public void testEqualsObject() {
        for (int i = 0; i < 100; i++) {
            int v = Util.getRandom(650);
            assertEquals(new Zorder(v), new Zorder(v));
        }
        assertNotEquals(new Zorder(10), null);
        assertNotEquals(new Zorder(10), new Zorder(11));
        assertNotEquals(new Zorder(10), 10);
    }

}
