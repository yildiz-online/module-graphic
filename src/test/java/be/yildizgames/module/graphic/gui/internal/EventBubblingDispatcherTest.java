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

package be.yildizgames.module.graphic.gui.internal;

import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Position;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.dummy.DummyGuiFactory;
import be.yildizgames.module.graphic.gui.Zorder;
import be.yildizgames.module.graphic.gui.button.ButtonMaterial;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.impl.SimpleContainer;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.window.input.MousePosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class EventBubblingDispatcherTest {

    @Nested
    public class MouseMove {

        @Test
        public void oneContainer() {
            EventBubblingDispatcher dispatcher = new EventBubblingDispatcher();
            Container screen = buildScreen();

            SimpleContainer container = new DummyGuiFactory().buildContainerElement("window", new Coordinates(new Size(200,200), new Position(100, 100)), Material.empty(), screen, false);
            container.show();
            dispatcher.addContainer(container);
            Assertions.assertFalse(container.isMouseOver());
            dispatcher.mouseMove(new MousePosition(120,120));
            Assertions.assertTrue(container.isMouseOver());
        }

        @Test
        public void oneContainerWithOneWidget() {
            DummyGuiFactory factory = new DummyGuiFactory();
            EventBubblingDispatcher dispatcher = new EventBubblingDispatcher();
            Container screen = buildScreen();

            SimpleContainer container = factory.buildContainerElement("window", new Coordinates(new Size(200,200), new Position(100, 100)), Material.empty(), screen, false);
            container.show();
            dispatcher.addContainer(container);
            BaseWidget button = factory.buildButton("button", new Coordinates(new Size(20,20), new Position(50, 50)), new ButtonMaterial(Material.empty(), Material.empty(), Font.get("default")), container);
            Assertions.assertFalse(container.isMouseOver());
            Assertions.assertFalse(button.isMouseOver());
            dispatcher.mouseMove(new MousePosition(120,120));
            Assertions.assertFalse(button.isMouseOver());
            Assertions.assertTrue(container.isMouseOver());
            dispatcher.mouseMove(new MousePosition(160,160));
            Assertions.assertTrue(button.isMouseOver());
            Assertions.assertFalse(container.isMouseOver());
            dispatcher.mouseMove(new MousePosition(260,260));
            Assertions.assertFalse(button.isMouseOver());
            Assertions.assertTrue(container.isMouseOver());
        }

        @Test
        public void twoDistantContainer() {
            EventBubblingDispatcher dispatcher = new EventBubblingDispatcher();
            Container screen = buildScreen();

            SimpleContainer container1 = new DummyGuiFactory().buildContainerElement("window1", new Coordinates(new Size(200,200), new Position(100, 100)), Material.empty(), screen, false);
            container1.show();
            SimpleContainer container2 = new DummyGuiFactory().buildContainerElement("window2", new Coordinates(new Size(200,200), new Position(600, 100)), Material.empty(), screen, false);
            container2.show();
            dispatcher.addContainer(container1);
            dispatcher.addContainer(container2);
            Assertions.assertFalse(container1.isMouseOver());
            Assertions.assertFalse(container2.isMouseOver());
            dispatcher.mouseMove(new MousePosition(120,120));
            Assertions.assertTrue(container1.isMouseOver());
            Assertions.assertFalse(container2.isMouseOver());
            dispatcher.mouseMove(new MousePosition(620,120));
            Assertions.assertFalse(container1.isMouseOver());
            Assertions.assertTrue(container2.isMouseOver());
        }

        @Test
        public void twoOverlappedContainer() {
            EventBubblingDispatcher dispatcher = new EventBubblingDispatcher();
            Container screen = buildScreen();

            SimpleContainer container1 = new DummyGuiFactory().buildContainerElement("window1", new Coordinates(new Size(200,200), new Position(100, 100)), Material.empty(), screen, false);
            container1.show();
            SimpleContainer container2 = new DummyGuiFactory().buildContainerElement("window2", new Coordinates(new Size(200,200), new Position(150, 150)), Material.empty(), screen, false);
            container2.show();
            dispatcher.addContainer(container1);
            dispatcher.addContainer(container2);
            dispatcher.mouseMove(new MousePosition(120,120));
            Assertions.assertTrue(container1.isMouseOver());
            Assertions.assertFalse(container2.isMouseOver());
            dispatcher.mouseMove(new MousePosition(160,160));
            Assertions.assertFalse(container1.isMouseOver());
            Assertions.assertTrue(container2.isMouseOver());
            container1.setZ(new Zorder(10));
            dispatcher.mouseMove(new MousePosition(160,160));
            Assertions.assertTrue(container1.isMouseOver());
            Assertions.assertFalse(container2.isMouseOver());
        }

        @Test
        public void twoOverlappedContainerWithOneWidget() {
            DummyGuiFactory factory = new DummyGuiFactory();
            EventBubblingDispatcher dispatcher = new EventBubblingDispatcher();
            Container screen = buildScreen();

            SimpleContainer container1 = factory.buildContainerElement("window1", new Coordinates(new Size(200,200), new Position(100, 100)), Material.empty(), screen, false);
            BaseWidget button = factory.buildButton("button", new Coordinates(new Size(20,20), new Position(0, 0)), new ButtonMaterial(Material.empty(), Material.empty(), Font.get("default")), container1);
            container1.show();
            SimpleContainer container2 = factory.buildContainerElement("window2", new Coordinates(new Size(200,200), new Position(150, 150)), Material.empty(), screen, false);
            container2.show();
            dispatcher.addContainer(container1);
            dispatcher.addContainer(container2);
            dispatcher.mouseMove(new MousePosition(110,110));
            Assertions.assertTrue(button.isMouseOver());
            Assertions.assertFalse(container1.isMouseOver());
            Assertions.assertFalse(container2.isMouseOver());
        }
    }

    private SimpleContainer buildScreen() {
        return new DummyGuiFactory().buildContainerElement("screen", new Coordinates(new Size(1920,1080), new Position(0, 0)), Material.empty());
    }
}
