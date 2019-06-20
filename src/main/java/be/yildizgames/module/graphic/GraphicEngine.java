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
package be.yildizgames.module.graphic;

import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.graphic.gui.GuiEventManager;
import be.yildizgames.module.graphic.gui.GuiFactory;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.graphic.material.MaterialManager;
import be.yildizgames.module.graphic.misc.SelectionRectangle;
import be.yildizgames.module.graphic.misc.SkyBox;
import be.yildizgames.module.window.ScreenSize;

public interface GraphicEngine {

    /**
     * Print the current rendering frame and save it in a file.
     */
    void printScreen();

    GuiEventManager getEventManager();

    /**
     * Provide the module responsible to build GUI elements.
     *
     * @return The GuiBuilder.
     */
    GuiFactory getGuiFactory();

    ScreenSize getScreenSize();

    MaterialManager getMaterialManager();

    /**
     * Create a new Selection Rectangle, only one can be used at a time, if more
     * than one is created, an exception is thrown.
     *
     * @param border Material to set.
     * @param inner  Inner material.
     * @return The created object.
     */
    SelectionRectangle createSelectionRectangle(Material border, Material inner);

    /**
     * Create a sky box.
     *
     * @param name Sky box unique name.
     * @param path Path to images to use.
     * @return The created object.
     */
    SkyBox createSkybox(String name, String path);

    /**
     * Create a new font with a given color and a random name.
     *
     * @param path  Path to the font file.
     * @param size  Size of the font.
     * @param color Font color.
     * @return The newly created font.
     */
    Font createFont(final String path, final Relative size, final Color color);

    /**
     * Create a new font.
     *
     * @param name Font name, must be unique.
     * @param path Path to the font file.
     * @param size Size of the font.
     * @return The newly created font.
     */
    Font createFont(final String name, final String path, final Relative size);

    /**
     * Create a new font with a given color.
     *
     * @param name  Font name, must be unique.
     * @param path  Path to the font file.
     * @param size  Size of the font.
     * @param color Font color.
     * @return The newly created font.
     */
    Font createFont(final String name, final String path, final Relative size, final Color color);

    /**
     * Create a new font to use to print text.
     *
     * @param name  Name of the font, must be unique, used to retrieve it from the
     *              registerer.
     * @param path  Path to the font file to load.
     * @param size  Size of the font.
     * @param color Font color.
     * @return The newly built font.
     */
    Font createFont(String name, String path, int size, Color color);

    /**
     * Create a new font to use to print text.
     *
     * @param name Name of the font, must be unique, used to retrieve it from the registerer.
     * @param path Path to the font file to load.
     * @param size Size of the font.
     * @return The newly built font.
     */
    Font createFont(String name, String path, int size);

    /**
     * Create a new font with a given color and a random name.
     *
     * @param path  Path to the font file.
     * @param size  Size of the font.
     * @param color Font color.
     * @return The newly created font.
     */
    Font createFont(String path, int size, Color color);
}
