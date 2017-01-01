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

package be.yildiz.module.graphic.gui;

import be.yildiz.common.util.Util;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Grégory Van den Borre
 */
public final class ZOrderTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    @Test
    public void testHashCode() {
        Assert.assertEquals(new Zorder(12).hashCode(), new Zorder(12).hashCode());
        Assert.assertEquals(new Zorder(222).hashCode(), new Zorder(222).hashCode());
        Assert.assertEquals(new Zorder(473).hashCode(), new Zorder(473).hashCode());
        Assert.assertEquals(new Zorder(0).hashCode(), new Zorder(0).hashCode());
        Assert.assertNotEquals(new Zorder(17).hashCode(), new Zorder(16).hashCode());
    }

    @Test
    public void testZorderMin() {
        new Zorder(0);
        this.rule.expect(IllegalArgumentException.class);
        new Zorder(-1);
    }

    @Test
    public void testZorderMax() {
        new Zorder(650);
        this.rule.expect(IllegalArgumentException.class);
        new Zorder(651);
    }

    @Test
    public void testZorder() {
        Assert.assertEquals(new Zorder(640), Zorder.GUI);
        Assert.assertEquals(new Zorder(0), Zorder.ZERO);
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(new Zorder(10), new Zorder(3).add(7));
        this.rule.expect(IllegalArgumentException.class);
        new Zorder(10).add(650);
    }

    @Test
    public void testCompareTo() {
        Assert.assertEquals(1, new Zorder(10).compareTo(new Zorder(11)));
        Assert.assertEquals(0, new Zorder(8).compareTo(new Zorder(8)));
        Assert.assertEquals(-1, new Zorder(200).compareTo(new Zorder(45)));
    }

    @Test
    public void testToString() {
        Assert.assertEquals("Z order:12", new Zorder(12).toString());
    }

    @Test
    public void testGetValue() {
        Assert.assertEquals(17, new Zorder(17).getValue());
    }

    @Test
    public void testEqualsObject() {
        for (int i = 0; i < 100; i++) {
            int v = Util.getRandom(650);
            Assert.assertEquals(new Zorder(v), new Zorder(v));
        }
        Assert.assertNotEquals(new Zorder(10), null);
        Assert.assertNotEquals(new Zorder(10), new Zorder(11));
        Assert.assertNotEquals(new Zorder(10), 10);
    }

}
