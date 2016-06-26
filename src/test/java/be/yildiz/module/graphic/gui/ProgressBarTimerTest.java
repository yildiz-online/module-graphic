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

import be.yildiz.common.util.Time;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * @author Grégory Van den Borre
 */
public class ProgressBarTimerTest {

    @Rule
    public final ExpectedException rule = ExpectedException.none();

    @Test
    public void test() {
        ProgressBar b = Mockito.mock(ProgressBar.class);
        new ProgressBarTimer(b, Time.seconds(10));
        this.rule.expect(NullPointerException.class);
        new ProgressBarTimer(null, Time.seconds(10));
        this.rule.expect(NullPointerException.class);
        new ProgressBarTimer(b, null);
    }

    @Test
    public void testNeverStop() {
        ProgressBar b = Mockito.mock(ProgressBar.class);
        ProgressBarTimer t = new ProgressBarTimer(b, Time.seconds(0));
        Assert.assertFalse(t.frameEnded());
        t.neverStop();
        Assert.assertTrue(t.frameEnded());
    }

    @Test
    public void testSetValuesNegative() {
        ProgressBar b = Mockito.mock(ProgressBar.class);
        ProgressBarTimer t = new ProgressBarTimer(b, Time.seconds(0));
        Assert.assertFalse(t.frameEnded());
        this.rule.expect(IllegalArgumentException.class);
        t.setValues(-5, 10);
        this.rule.expect(IllegalArgumentException.class);
        t.setValues(5, -10);
    }
}
