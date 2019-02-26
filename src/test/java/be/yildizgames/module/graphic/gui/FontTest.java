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

import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.dummy.DummyGuiFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Grégory Van den Borre
 */
public class FontTest {

    @Nested
    public class Crop {

        @Test
        public void nothing() {
            Font f = DummyGuiFactory.defaultFont;
            f.load();
            assertEquals("azerty", f.crop("azerty", 10));
        }

        @Test
        public void tooLong() {
            Font f = DummyGuiFactory.defaultFont;
            f.load();
            assertEquals("a...", f.crop("azerty", 4));
        }

        @Test
        public void tooShort() {
            Font f = DummyGuiFactory.defaultFont;
            f.load();
            assertEquals("", f.crop("azerty", 2));
        }

        @Test
        public void tooLong3chars() {
            Font f = DummyGuiFactory.defaultFont;
            f.load();
            assertEquals("...", f.crop("azerty", 3));
        }

        @Test
        public void withNullParameter() {
            Font f = DummyGuiFactory.defaultFont;
            f.load();
            assertThrows(ImplementationException.class, () -> f.crop(null, 3));
        }
    }

}
