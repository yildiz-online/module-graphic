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

package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.common.time.TimeFormatter;
import be.yildizgames.common.util.Registerer;
import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.coordinates.Coordinates;
import be.yildizgames.module.coordinates.FullCoordinates;
import be.yildizgames.module.coordinates.Position;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.GuiFactory;
import be.yildizgames.module.graphic.gui.SimpleView;
import be.yildizgames.module.graphic.gui.View;
import be.yildizgames.module.graphic.gui.button.Button;
import be.yildizgames.module.graphic.gui.button.ButtonMaterial;
import be.yildizgames.module.graphic.gui.checkbox.CheckBox;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.container.ContainerBuilder;
import be.yildizgames.module.graphic.gui.element.AbstractIconElement;
import be.yildizgames.module.graphic.gui.element.AbstractTextElement;
import be.yildizgames.module.graphic.gui.image.Image;
import be.yildizgames.module.graphic.gui.image.ImageBuilder;
import be.yildizgames.module.graphic.gui.inputbox.InputBox;
import be.yildizgames.module.graphic.gui.inputbox.InputBoxTemplate;
import be.yildizgames.module.graphic.gui.internal.GuiAnimationManager;
import be.yildizgames.module.graphic.gui.progressbar.ProgressBar;
import be.yildizgames.module.graphic.gui.progressbar.ProgressBarTimer;
import be.yildizgames.module.graphic.gui.table.TabContainer;
import be.yildizgames.module.graphic.gui.textarea.TextArea;
import be.yildizgames.module.graphic.gui.textline.TextLine;
import be.yildizgames.module.graphic.gui.textline.TextLineTemplate;
import be.yildizgames.module.graphic.gui.textline.TimeTextLine;
import be.yildizgames.module.graphic.material.Material;
import be.yildizgames.module.window.ScreenSize;

import java.time.Duration;
import java.util.Optional;

/**
 * Build and manage all GUI elements.
 *
 * @author Grégory Van den Borre
 */
public abstract class StandardGuiFactory implements GuiFactory {

    /**
     * Contains the screen size data.
     */
    protected final ScreenSize screenSize;

    /**
     * List of all registered buttons, key is their name.
     */
    private final Registerer<Button> buttonList = Registerer.newRegisterer();

    /**
     * List of all registered check boxes, key is their name.
     */
    private final Registerer<CheckBox> checkBoxList = Registerer.newRegisterer();

    /**
     * List of all registered images, key is their name.
     */
    private final Registerer<Image> imageList = Registerer.newRegisterer();

    /**
     * List of all GuiContainer, key is their name.
     */
    private final Registerer<SimpleContainer> containerList = Registerer.newRegisterer();

    /**
     * List of all registered input boxes, key is their name.
     */
    private final Registerer<InputBox> inputList = Registerer.newRegisterer();

    /**
     * List of all registered progress bar, key is their name.
     */
    private final Registerer<ProgressBar> progressBarList = Registerer.newRegisterer();

    /**
     * List of all registered GuiTextLine, key is their name.
     */
    private final Registerer<TextLine> textLineList = Registerer.newRegisterer();

    /**
     * List of all registered TextAreaGui, key is their unique name.
     */
    private final Registerer<TextArea> textAreaList = Registerer.newRegisterer();

    private final GuiAnimationManager animationManager = new GuiAnimationManager();

    protected StandardGuiFactory(ScreenSize screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public final ContainerBuilder container() {
        return new SimpleContainerBuilder(this);
    }

    @Override
    public final SimpleButtonBuilder button() {
        return new SimpleButtonBuilder(this);
    }

    @Override
    public final SimpleTextLineBuilder textLine() {
        return new SimpleTextLineBuilder(this);
    }

    @Override
    public final TextLine textLine(Container parent, TextLineTemplate template) {
        return this.textLine()
                .withCoordinates(template.getCoordinates())
                .withFont(template.getFont())
                .build(parent);
    }

    @Override
    public final TextLine textLine(Container parent, TextLineTemplate template, TranslationKey key) {
        TextLine line = this.textLine(parent, template);
        line.setText(key);
        return line;
    }

    @Override
    public final SimpleTextAreaBuilder textArea() {
        return new SimpleTextAreaBuilder(this);
    }

    @Override
    public final SimpleCheckBoxBuilder checkbox() {
        return new SimpleCheckBoxBuilder(this);
    }

    @Override
    public final ImageBuilder image() {
        return new ImageBuilder(this);
    }

    @Override
    public final SimpleInputBoxBuilder inputBox() {
        return new SimpleInputBoxBuilder(this);
    }

    @Override
    public final SimpleProgressBarBuilder progressBar() {
        return new SimpleProgressBarBuilder(this);
    }

    /**
     * Build a new widget button.
     *
     * @param name        Unique button name.
     * @param coordinates GuiButton coordinates.
     * @param material    GuiButton materials.
     * @param container   Container holding the button.
     * @return The build button.
     */
    public final SimpleButton buildButton(final String name, final Coordinates coordinates, final ButtonMaterial material, final SimpleContainer container) {
        final SimpleContainer c = this.buildOverlayContainer(name, Material.empty(), coordinates, container, true);
        final AbstractTextElement text = this.buildTextElement(coordinates, material.font, c);

        final SimpleButton button = new SimpleButton(name, text, c, material, container);
        this.buttonList.register(button);
        return button;
    }

    /**
     * Retrieve a GuiButton from its name.
     *
     * @param name Name of the button to retrieve.
     * @return The matching GuiButton.
     * @throws IllegalArgumentException If no match is done.
     */
    public final Button getButton(final String name) {
        return this.buttonList.get(name);
    }

    /**
     * Try to retrieve a GuiButton from its name.
     *
     * @param name Name of the button to search for.
     * @return An optional button.
     */
    public final Optional<Button> findButton(final String name) {
        return this.buttonList.find(name);
    }

    /**
     * Delete a button.
     *
     * @param button GuiButton to delete.
     */
    public final void delete(final Button button) {
        button.detachFromParent();
        this.buttonList.remove(button);
    }

    /**
     * Build a new image widget.
     *
     * @param name        Unique image name.
     * @param coordinates Image coordinates.
     * @param background  Image background material.
     * @param container   Container holding the image.
     * @return The new image widget.
     */
    public final Image buildImage(final String name, final Coordinates coordinates, final Material background, final Container container) {
        final AbstractIconElement icon = this.buildIconElement(name, coordinates, background, container);
        final SimpleImage image = new SimpleImage(name, coordinates, icon, container);
        image.setStatic();
        this.imageList.register(image);
        return image;
    }

    /**
     * Build a new image widget.
     *
     * @param name        Unique image name.
     * @param coordinates Image coordinates.
     * @param background  Image background material.
     * @param border      Size of the border, in pixels.
     * @param container   Container holding the image.
     * @return The new image widget.
     */
    public final EmptyRectangleImage buildEmptyRectangleImage(final String name, final Coordinates coordinates, final Material background, final int border, final SimpleContainer container) {
        final AbstractIconElement left = this.buildIconElement(name + "_left", FullCoordinates.full(border, coordinates.getHeight(), coordinates.getLeft(), coordinates.getTop()), background, container);
        final AbstractIconElement top = this.buildIconElement(name + "_top", FullCoordinates.full(coordinates.getWidth(), border, coordinates.getLeft(), coordinates.getTop()), background, container);
        final AbstractIconElement right = this.buildIconElement(name + "_right", FullCoordinates.full(border, coordinates.getHeight(), coordinates.getLeft() + coordinates.getWidth(), coordinates.getTop()), background,
                container);
        final AbstractIconElement bottom = this.buildIconElement(name + "_bottom", FullCoordinates.full(coordinates.getWidth(), border, coordinates.getLeft(), coordinates.getTop() + coordinates.getHeight()), background,
                container);
        final EmptyRectangleImage image = new EmptyRectangleImage(name, coordinates, left, top, right, bottom, container);
        this.imageList.register(image);
        image.setStatic();
        return image;
    }

    public final EmptyRectangleImage buildEmptyUnderlineImage(final String name, final Coordinates coordinates, final Material background, final int border, final SimpleContainer container) {
        final AbstractIconElement left = this.buildIconElement(name + "_left", FullCoordinates.full(border, coordinates.getHeight(), coordinates.getLeft(), coordinates.getTop()), Material.empty(), container);
        final AbstractIconElement top = this.buildIconElement(name + "_top", FullCoordinates.full(coordinates.getWidth(), border, coordinates.getLeft(), coordinates.getTop()), Material.empty(), container);
        final AbstractIconElement right = this.buildIconElement(name + "_right", FullCoordinates.full(border, coordinates.getHeight(), coordinates.getLeft() + coordinates.getWidth(), coordinates.getTop()), Material.empty(),
                container);
        final AbstractIconElement bottom = this.buildIconElement(name + "_bottom", FullCoordinates.full(coordinates.getWidth(), border, coordinates.getLeft(), coordinates.getTop() + coordinates.getHeight()), background,
                container);
        final EmptyRectangleImage image = new EmptyRectangleImage(name, coordinates, left, top, right, bottom, container);
        this.imageList.register(image);
        image.setStatic();
        return image;
    }

    /**
     * Retrieve an Image from its name, throw {@link IllegalArgumentException} if no matching name is found.
     *
     * @param name Image name to retrieve.
     * @return The matching Image.
     */
    public final Image getImage(final String name) {
        return this.imageList.get(name);
    }

    /**
     * try to retrieve an Image from its name.
     *
     * @param name Image name to retrieve.
     * @return The optional Image.
     */
    public final Optional<Image> findImage(final String name) {
        return this.imageList.find(name);
    }

    /**
     * Delete an image.
     *
     * @param image Image to delete.
     */
    public final void delete(final Image image) {
        image.detachFromParent();
        this.imageList.remove(image);
    }

    /**
     * Build a new line of text widget.
     *
     * @param name        Text line unique name.
     * @param coordinates Text line size and position.
     * @param font        Font used for the text.
     * @param container   Container holding the text line.
     * @return The new text line.
     */
    public final SimpleTextLine buildTextLine(final String name, final Coordinates coordinates, final Font font, final SimpleContainer container) {
        final AbstractTextElement text = this.buildTextElement(coordinates, font, container);
        final SimpleTextLine textLine = new SimpleTextLine(name, text, container);
        textLine.setStatic();
        this.textLineList.register(textLine);
        return textLine;
    }

    /**
     * Retrieve a GuiTextLine from its name, throw {@link IllegalArgumentException} if no matching name is found.
     *
     * @param name GuiTextLine name to retrieve.
     * @return The matching GuiTextLine.
     */
    public final TextLine getTextLine(final String name) {
        return this.textLineList.get(name);
    }

    /**
     * Delete a text line.
     *
     * @param textLine Text line to delete.
     */
    public final void delete(final TextLine textLine) {
        textLine.detachFromParent();
        this.textLineList.remove(textLine);
    }

    /**
     * Build a new multiple line text widget.
     *
     * @param name        Unique text area name.
     * @param coordinates Text area coordinates.
     * @param font        Font used for the text.
     * @param background  Background image.
     * @param textPadding Padding value for the text.
     * @param container   Container holding the text area.
     * @return The new text area widget.
     */
    public final TextArea buildTextArea(final String name, final Coordinates coordinates, final Font font, final Material background, final int textPadding, final SimpleContainer container) {
        final AbstractIconElement image = this.buildIconElement(name, coordinates, background, container);
        final AbstractTextElement text = this.buildTextElement(coordinates, font, container);
        final SimpleTextArea textArea = new SimpleTextArea(name, coordinates, image, text, textPadding, container);
        this.textAreaList.register(textArea);
        textArea.setStatic();
        return textArea;
    }

    /**
     * Delete a text area.
     *
     * @param textArea Text area to delete.
     */
    public final void delete(final TextArea textArea) {
        textArea.detachFromParent();
        this.textAreaList.remove(textArea);
    }

    /**
     * Build a new progress bar widget.
     *
     * @param name           Unique progress bar name.
     * @param coordinates    Progress bar coordinates.
     * @param leftMat        Material for the left corner.
     * @param middleMat      Material for extensible part(will be stretched when the progress bar progress increase).
     * @param rightMat       Material for the right corner.
     * @param emptyMiddleMat Material for the middle part to fill when still empty.
     * @param emptyRightMat  Material for the right border part to fill when still empty.
     * @param borderWidth    Width of the border element.
     * @param container      Container holding the progress bar.
     * @return The new progress bar widget.
     */
    public final ProgressBar buildProgressBar(final String name, final Coordinates coordinates, final Material leftMat, final Material middleMat, final Material rightMat,
                                                  final Material emptyMiddleMat, final Material emptyRightMat, final int borderWidth, final SimpleContainer container) {
        final Coordinates border = FullCoordinates.full(borderWidth, coordinates.getHeight(), coordinates.getLeft(), coordinates.getTop());
        final Coordinates middleC = FullCoordinates.full(0, coordinates.getHeight(), coordinates.getLeft() + border.getWidth(), coordinates.getTop());
        final AbstractIconElement left = this.buildIconElement(name + "lf", border, leftMat, container);
        // build empty first to be under color version
        final AbstractIconElement middleEmpty = this.buildIconElement(name + "mdempty", FullCoordinates.full(coordinates.getWidth() - 2 * borderWidth, coordinates.getHeight(), middleC.getLeft(), coordinates.getTop()),
                emptyMiddleMat, container);
        final AbstractIconElement rightEmpty = this.buildIconElement(name + "rtempty",
                FullCoordinates.full(borderWidth, coordinates.getHeight(), coordinates.getLeft() + coordinates.getWidth() - borderWidth, coordinates.getTop()), emptyRightMat, container);

        final Container child = this.buildContainerElement(container.getName() + name + "_child", container.getCoordinates(), Material.empty(), container, false);
        final AbstractIconElement middle = this.buildIconElement(name + "mdfilled", middleC, middleMat, child);
        final AbstractIconElement right = this.buildIconElement(name + "rtfilled", border, rightMat, child);
        final ProgressBar progressBar = new ComplexProgressBar(name, coordinates, left, middle, right, middleEmpty, rightEmpty, container);
        this.progressBarList.register(progressBar);
        return progressBar;
    }

    /**
     * Build a new progress bar widget.
     *
     * @param name       Unique progress bar name.
     * @param background Image to use as empty background.
     * @param filled     Image to use as filled background.
     * @param parent     Container holding the progress bar.
     * @return The new progress bar widget.
     */
    public final ProgressBar buildProgressBar(final String name, final Image background, final Image filled, final SimpleContainer parent) {
        RectangleProgressBar progressBar = new RectangleProgressBar(name, background.getCoordinates(), background, filled, background.getBorderSize(), parent);
        this.progressBarList.register(progressBar);
        progressBar.setStatic();
        return progressBar;
    }

    /**
     * Build a new progress bar widget, all parameter are set to empty.
     *
     * @param name      Unique progress bar name.
     * @param container Container holding the progress bar.
     * @return The new progress bar widget.
     */
    public final ProgressBar buildProgressBar(final String name, final SimpleContainer container) {
        return this.buildProgressBar(name, FullCoordinates.ZERO, Material.empty(), Material.empty(), Material.empty(), Material.empty(), Material.empty(), 0, container);
    }

    /**
     * Build a new simple progress bar widget.
     *
     * @param name        Unique progress bar name.
     * @param coordinates Progress bar coordinates.
     * @param empty       Material to use on empty part.
     * @param filled      Material to use on filled part.
     * @param container   Container holding the progress bar.
     * @return The new progress bar widget.
     */
    public final ProgressBar buildProgressBar(final String name, final Coordinates coordinates, final Material empty, final Material filled, final SimpleContainer container) {
        final AbstractIconElement emptyIcon = this.buildIconElement(name + "empty", coordinates, empty, container);
        final AbstractIconElement filledIcon = this.buildIconElement(name + "filled", coordinates, filled, container);
        final ProgressBar progressBar = new SimpleProgressBar(name, coordinates, emptyIcon, filledIcon, container);
        this.progressBarList.register(progressBar);
        return progressBar;
    }

    /**
     * Build a new simple progress bar widget with a random unique name.
     *
     * @param coordinates Progress bar coordinates.
     * @param empty       Material to use on empty part.
     * @param filled      Material to use on filled part.
     * @param container   Container holding the progress bar.
     * @return The new progress bar widget.
     */
    public final ProgressBar buildProgressBar(final Coordinates coordinates, final Material empty, final Material filled, final SimpleContainer container) {
        final String name = StringUtil.buildRandomString("progressbar");
        final AbstractIconElement emptyIcon = this.buildIconElement(name + "empty", coordinates, empty, container);
        final AbstractIconElement filledIcon = this.buildIconElement(name + "filled", coordinates, filled, container);
        final ProgressBar progressBar = new SimpleProgressBar(name, coordinates, emptyIcon, filledIcon, container);
        this.progressBarList.register(progressBar);
        return progressBar;
    }

    public final ProgressBarTimer buildProgressBar(final Coordinates coordinates, final Material empty, final Material filled, Font font, Duration duration, TimeFormatter formatter, final SimpleContainer container) {
        final String name = StringUtil.buildRandomString("progressbar");
        final AbstractIconElement emptyIcon = this.buildIconElement(name + "empty", coordinates, empty, container);
        final AbstractIconElement filledIcon = this.buildIconElement(name + "filled", coordinates, filled, container);
        final AbstractTextElement text = this.buildTextElement(coordinates, font, container);
        final ProgressBar progressBar = new SimpleProgressBar(name, coordinates, emptyIcon, filledIcon, container);
        final TimeTextLine timeTextLine = new SimpleTimeTextLine(name + "_txt", text, container, formatter);
        final SimpleProgressBarTimer timer = new SimpleProgressBarTimer(progressBar, timeTextLine, duration);
        this.progressBarList.register(timer);
        return timer;
    }

    /**
     * Retrieve a ProgressBar from its name, if no name matches, an {@link IllegalArgumentException} is thrown.
     *
     * @param name Name of the ProgressBar to retrieve.
     * @return The ProgressBar associated with the name.
     */
    public final ProgressBar getProgressBar(final String name) {
        return this.progressBarList.get(name);
    }

    /**
     * Delete a progress bar.
     *
     * @param progressBar Progress bar to delete.
     */
    public final void delete(final ProgressBar progressBar) {
        progressBar.detachFromParent();
        this.progressBarList.remove(progressBar);
    }

    /**
     * Build a new input box widget.
     *
     * @param name             Unique input box name.
     * @param coordinates      Input box coordinates.
     * @param font             Font used for the caption.
     * @param background       Background material.
     * @param backgroundHlight Background material when the widget is focused.
     * @param cursorMaterial   Material to use for the cursor.
     * @param container        Container holding the input box.
     * @return The new input box widget.
     */
    public final SimpleInputBox buildInputBox(final String name, final Coordinates coordinates, final Font font, final Material background, final Material backgroundHlight, final Material cursorMaterial,
                                              final Container container) {
        return this.buildInputBox(name, coordinates, font, font, background, backgroundHlight, cursorMaterial, container);
    }

    /**
     * Build a new input box widget.
     *
     * @param name             Unique input box name.
     * @param coordinates      Input box coordinates.
     * @param defaultFont      Font used for the default message.
     * @param captionFont      Font used for the text and the caption.
     * @param background       Background material.
     * @param backgroundHlight Background material when the widget is focused.
     * @param cursorMaterial   Material to use for the cursor.
     * @param container        Container holding the input box.
     * @return The new input box widget.
     */
    public final SimpleInputBox buildInputBox(
            final String name,
            final Coordinates coordinates,
            final Font defaultFont,
            final Font captionFont,
            final Material background,
            final Material backgroundHlight,
            final Material cursorMaterial,
            final Container container) {
        //FIXME the caption font is the same as the one for the text, for a password type input, it will not be the right font
        SimpleContainer parent = this.containerList.get(container.getName());
        SimpleContainer c = this.buildOverlayContainer(
                name,
                Material.empty(),
                coordinates,
                parent,
                true);
        //Use an image to create complex background.
        Image i = this.buildEmptyUnderlineImage(
                name + "_bi",
                FullCoordinates.full(coordinates.getWidth(), coordinates.getHeight(), 0, 0),
                background, 1, c);
        final AbstractTextElement text = this.buildTextElement(coordinates, captionFont, c);
        final AbstractTextElement caption = this.buildTextElement(coordinates, captionFont, c);
        final AbstractIconElement cursor = this.buildIconElement(name + "_cursor", FullCoordinates.full(3, 20, 0, 0), cursorMaterial, c);
        final AbstractTextElement defaultMessage = this.buildTextElement(coordinates, captionFont, c);
        ButtonMaterial materials = new ButtonMaterial(background, backgroundHlight, captionFont);
        final SimpleInputBox inputBox = new SimpleInputBox(name, coordinates, text, caption, c, i, materials, cursor, defaultMessage, parent);
        this.inputList.register(inputBox);

        return inputBox;
    }

    /**
     * Build a new input box widget.
     *
     * @param name           Unique input box name.
     * @param coordinates    Input box coordinates.
     * @param material       Background materials.
     * @param cursorMaterial Material to use for the cursor.
     * @param container      Container holding the input box.
     * @return The new input box widget.
     */
    public final InputBox buildInputBox(final String name, final Coordinates coordinates, final ButtonMaterial material, final Material cursorMaterial, final SimpleContainer container) {
        return this.buildInputBox(name, coordinates, material.font, material.material, material.highlight, cursorMaterial, container);
    }

    /**
     * Build a new input box widget.
     *
     * @param position  Input box position.
     * @param def       Input box data.
     * @param container Container holding the input box.
     * @return The new input box widget.
     */
    public final InputBox buildInputBox(final Position position, final InputBoxTemplate def, final SimpleContainer container) {
        return this.buildInputBox(StringUtil.buildRandomString("inputbox"), FullCoordinates.full(def.getSize(), position), def.getFont(), def.getCaptionFont(), def.getMaterial(), def.getFocus(), def.getCursor(), container);
    }

    public final InputBox buildInputBox(String name, final Position position, final InputBoxTemplate def, final SimpleContainer container) {
        return this.buildInputBox(name, FullCoordinates.full(def.getSize(), position), def.getFont(), def.getMaterial(), def.getFocus(), def.getCursor(), container);
    }

    /**
     * Retrieve a InputBoxGui from its name, if no name matches, an {@link IllegalArgumentException} is thrown.
     *
     * @param name Name of the InputBoxGui to retrieve.
     * @return The InputBoxGui associated with the name.
     */
    public final InputBox getInputBox(final String name) {
        return this.inputList.get(name);
    }

    /**
     * Delete an input box.
     *
     * @param inputBox Input box to delete.
     */
    public final void delete(final InputBox inputBox) {
        inputBox.detachFromParent();
        this.inputList.remove(inputBox);
    }

    /**
     * Build a new check box widget.
     *
     * @param name        Unique check box name.
     * @param coordinates Check box coordinates.
     * @param background  Background material.
     * @param hover       Hover material.
     * @param check       Material when checked.
     * @param checkHover  Material when checked and hovered.
     * @param font        Font to use for caption.
     * @param container   Container holding the check box widget.
     * @return The new check box widget.
     */
    public final SimpleCheckBox buildCheckBox(final String name,
                                              final Coordinates coordinates,
                                              final Material background,
                                              final Material hover,
                                              final Material check,
                                              final Material checkHover,
                                              final Font font,
                                              final SimpleContainer container) {
        assert name != null;
        assert coordinates != null;
        assert background != null;
        assert hover != null;
        assert check != null;
        assert font != null;
        assert container != null;
        final AbstractIconElement icon = this.buildIconElement(name, coordinates, background, container);
        final AbstractIconElement checkIcon = this.buildIconElement(name + "ck", coordinates, check, container);
        //FIXME LOW hardcoded
        final int textHeight = 50;
        final int textWidth = 250;
        final int textXPosition = coordinates.getLeft() + coordinates.getWidth() + 10;
        final int textYPosition = coordinates.getTop() + coordinates.getHeight() >> 1 - (font.size >> 1);
        final Coordinates textCoord = FullCoordinates.full(textWidth, textHeight, textXPosition, textYPosition);
        final AbstractTextElement text = this.buildTextElement(textCoord, font, container);

        final SimpleCheckBox checkbox = new SimpleCheckBox(name, coordinates, icon, hover, checkIcon, checkHover, text, container);
        this.checkBoxList.register(checkbox);
        checkbox.setCaptionColor(font.color);
        return checkbox;
    }

    /**
     * Retrieve a CheckBoxGui from its name, if no name matches, an {@link IllegalArgumentException} is thrown.
     *
     * @param name Name of the CheckBoxGui to retrieve.
     * @return The CheckBoxGui associated with the name.
     */
    public final CheckBox getCheckBox(final String name) {
        return this.checkBoxList.get(name);
    }

    /**
     * Delete a check box.
     *
     * @param checkBox Check box to delete.
     */
    public final void delete(final CheckBox checkBox) {
        checkBox.detachFromParent();
        this.checkBoxList.remove(checkBox);
    }

    /**
     * Build a new GUI container, a GUI container is not meant to be used alone, it must be element of a view.
     *
     * @param name        Unique container name.
     * @param background  Material to use as background.
     * @param coordinates Container size and position.
     * @return The newly built container.
     */
    public final SimpleContainer buildOverlayContainer(final String name, final Material background, final Coordinates coordinates) {
        final SimpleContainer container = this.buildContainerElement(name, coordinates, background);
        this.containerList.register(container);
        return container;
    }

    /**
     * Build a container.
     *
     * @param name        Unique name.
     * @param background  Material to use as background.
     * @param coordinates Container size and position.
     * @param parent      Parent container.
     * @param widget      <code>true</code> if this container is meant to be used as part of a widget instead of container.
     * @return The newly built container.
     */
    private SimpleContainer buildOverlayContainer(final String name, final Material background, final Coordinates coordinates, final SimpleContainer parent, final boolean widget) {
        SimpleContainer c = this.buildContainerElement(name, coordinates, background, parent, widget);
        this.containerList.register(c);
        return c;
    }

    public SimpleContainer buildOverlayContainer(String name, Material background, Coordinates coordinates, SimpleContainer container) {
        return this.buildOverlayContainer(name, background, coordinates, container, false);
    }

    /**
     * Build a new full screen GUI container, the position is set to (0, 0) and the size is the screen size, a GUI container is not meant to be used alone, it must be element of a view.
     *
     * @param name       Unique container name.
     * @param background Container background material.
     * @return The newly built container.
     */
    public final SimpleContainer buildFullScreenOverlayContainer(final String name, final Material background) {
        SimpleContainer c = this.buildContainerElement(name, FullCoordinates.full(this.screenSize.width, this.screenSize.height,0,0), background);
        this.containerList.register(c);
        return c;
    }

    /**
     * Retrieve a GuiContainer from its name, if no name matches, an {@link IllegalArgumentException} is thrown.
     *
     * @param name Name of the GuiContainer to retrieve.
     * @return The GuiContainer associated with the name.
     */
    public final SimpleContainer getContainer(final String name) {
        return this.containerList.get(name);
    }

    /**
     * Delete a container.
     *
     * @param container Gui container to delete.
     */
    public final void delete(final SimpleContainer container) {
        // FIXME implements
        container.delete();
        this.containerList.remove(container);
    }

    /**
     * Build a new tab container.
     *
     * @param name        Container name, must be unique.
     * @param titles      Tab titles.
     * @param coordinates Container position and size.
     * @param tabWidth    Tab width size.
     * @param tabHeight   Tab height size.
     * @param background  Container background material.
     * @param tabMaterial Material on inactive tab.
     * @param highlight   Material on tab with mouse over.
     * @param pushed      Material on active tab.
     * @param font        Font to use on the tabs.
     * @param container   Container holding the element.
     * @return The newly created tab container.
     */
    public final TabContainer buildTabcontainer(final String name, final String[] titles, final Coordinates coordinates, final int tabWidth, final int tabHeight, final Material background,
                                                final Material tabMaterial, final Material highlight, final Material pushed, final Font font, final SimpleContainer container) {

        View[] children = new View[titles.length];
        for (int i = 0; i < children.length; i++) {
            Container c = this.buildContainerElement("childc" + name + i, coordinates, Material.empty());
            children[i] = new SimpleView(c, container.getZ().add(10), null);
        }
        Image bg = this.buildImage(name + "bg", FullCoordinates.full(coordinates.getWidth(), coordinates.getHeight() - tabHeight, coordinates.getLeft(), coordinates.getTop()), background, container);
        SimpleButton[] buttons = new SimpleButton[titles.length];
        for (int i = 0; i < titles.length; i++) {
            buttons[i] = this.buildButton(titles[i], FullCoordinates.full(tabWidth, tabHeight, 30 + coordinates.getLeft() + i * tabWidth + 5, coordinates.getTop() - tabHeight + 10), new ButtonMaterial(tabMaterial, highlight, font),
                    container);
            buttons[i].setPushedMaterial(pushed);
            buttons[i].setCaptionFont(font);
            buttons[i].setCaptionText(titles[i]);
        }

        return new TabContainer(name, coordinates, children, buttons, bg, container);
    }

    public ScreenSize getScreenSize() {
        return this.screenSize;
    }

    public GuiAnimationManager getAnimationManager() {
        return animationManager;
    }

    public final SimpleContainer getSimpleContainer(String name) {
        return this.containerList.get(name);
    }

    /**
     * Build an image base element in implementation.
     *
     * @param name        Unique image name.
     * @param coordinates Image size and position.
     * @param material    Material to set to the image.
     * @param container   Container holding the image.
     * @return The newly built image.
     */
    protected abstract AbstractIconElement buildIconElement(String name, Coordinates coordinates, Material material, Container container);

    /**
     * Build an text base element in implementation.
     *
     * @param coordinates Text size and position.
     * @param font        Font to set to the text.
     * @param container   Container holding the text.
     * @return The newly built text.
     */
    protected abstract AbstractTextElement buildTextElement(Coordinates coordinates, Font font, Container container);

    /**
     * Build a container base element in implementation.
     *
     * @param name        Unique container name.
     * @param coordinates Container size and position.
     * @param background  Container background material.
     * @return The newly built container.
     */
    public abstract SimpleContainer buildContainerElement(String name, Coordinates coordinates, Material background);

    /**
     * Build a container base element with a container parent in implementation.
     *
     * @param name        Unique container name.
     * @param coordinates Container size and position.
     * @param background  Container background material.
     * @param parent      Parent container.
     * @param widget      <code>true</code> to use this container as part of a widget instead of widget container.
     * @return The newly built container.
     */
    public abstract SimpleContainer buildContainerElement(String name, Coordinates coordinates, Material background, Container parent, boolean widget);
}
