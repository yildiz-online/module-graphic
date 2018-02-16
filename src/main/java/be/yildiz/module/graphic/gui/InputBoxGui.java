/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
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

package be.yildiz.module.graphic.gui;

import be.yildiz.module.color.Color;
import be.yildiz.module.coordinate.BaseCoordinate;
import be.yildiz.module.coordinate.Size;
import be.yildiz.module.graphic.Font;
import be.yildiz.module.graphic.Material;
import be.yildiz.module.window.input.ArrowKey;
import be.yildiz.module.window.input.KeyboardListener;
import be.yildiz.module.window.input.MouseLeftClickListener;
import be.yildiz.module.window.input.MousePosition;
import be.yildizgames.common.client.translation.Key;
import be.yildizgames.common.client.translation.Translation;
import be.yildizgames.common.util.StringUtil;

import java.util.Optional;

/**
 * Box used to receive keyboard input.
 *
 * @author Grégory Van den Borre
 */
public final class InputBoxGui extends ContainerChild implements InputBox {

    /**
     * Caption distance from the input box.
     */
    private static final int CAPTION_OFFSET = 5;
    /**
     * Text to print in the box.
     */
    private final AbstractTextElement text;
    /**
     * Box description.
     */
    private final AbstractTextElement captionText;
    /**
     * Default message to display when the input box is empty.
     */
    private final GuiTextLine defaultMessage;
    /**
     * Box background image.
     */
    private final Image background;
    /**
     * Associated key and mouse listener.
     */
    private final InputBoxListener listener;
    /**
     * Cursor image.
     */
    private final AbstractIconElement cursor;
    private final GuiContainer innerContainer;
    /**
     * Content currently printed.
     */
    private String content = "";
    /**
     * Total text.
     */
    private String totalText = "";
    /**
     * True will hide the content of the box and replace it by the password replacement character.
     */
    private boolean password;
    /**
     * Char to print to replace true input in case of password box, default is '*'.
     */
    private char pwdReplacement = '*';
    /**
     * Max size allowed in a line.
     */
    private float maxLineSize;
    /**
     * Cursor position.
     */
    private int cursorPosition;
    /**
     * Current displayed text position.
     */
    private int displayedTextPosition;
    /**
     * Flag to check if box is focused or not.
     */
    private boolean focused;
    private ButtonMaterial materials;

    /**
     * Full constructor.
     *
     * @param name              Input box name, must be unique.
     * @param coordinates       Box size and position.
     * @param textElement       Text to print in the box.
     * @param caption           Text to print next the box(usually box description).
     * @param c
     * @param backgroundElement Box background.
     * @param material          Materials to assign.
     * @param cursor            Cursor image.
     * @param container         Container holding the input box.
     */
    InputBoxGui(final String name, final BaseCoordinate coordinates, final AbstractTextElement textElement, final AbstractTextElement caption, GuiContainer c, final Image backgroundElement,
                final ButtonMaterial material, final AbstractIconElement cursor, final GuiTextLine defaultMessage, final GuiContainer container) {
        super(name, coordinates, Optional.of(container));
        this.text = textElement;
        this.captionText = caption;
        this.defaultMessage = defaultMessage;
        this.defaultMessage.setColor(Color.GRAY);
        this.maxLineSize = coordinates.width - 40;
        this.background = backgroundElement;
        this.innerContainer = c;
        this.materials = material;
        this.setPosition(coordinates.left, coordinates.top);
        this.cursor = cursor;
        this.showImpl();
        this.listener = new InputBoxListener();
        this.defaultMessage.setLeft(this, PositionRelativeLeft.CENTER);
        this.defaultMessage.setTop(this, PositionRelativeTop.CENTER);
        this.defaultMessage.hide();
        this.addKeyboardListener(this.listener);
        this.addMouseLeftClickListener(this.listener);
    }

    @Override
    public void setCaptionText(final String caption) {
        this.captionText.setText(caption);
        this.captionText.setLeft(this.getLeft() - this.captionText.getTextWidth() - CAPTION_OFFSET);
    }

    @Override
    public void setCaptionText(final Key caption) {
        this.setCaptionText(Translation.getInstance().translate(caption));
    }

    /**
     * Add a char to the current contained text.
     *
     * @param key Char to add ASCII value.
     */
    public void addChar(final int key) {
        if (key < 256) {
            this.totalText = new StringBuilder(this.totalText).insert(this.cursorPosition, (char) key).toString();
            this.updateContent();
            this.listener.arrowKeyPressed(ArrowKey.RIGHT);
        }
        this.showDefault();
    }

    @Override
    public void removeChar() {
        if (this.cursorPosition > 0) {
            this.totalText = StringUtil.removeChar(this.totalText, this.cursorPosition - 1);
        }
        if (this.totalText.length() == this.cursorPosition - 1) {
            this.displayedTextPosition--;
            if (this.displayedTextPosition < 0) {
                this.displayedTextPosition = 0;
            }
        }
        this.listener.arrowKeyPressed(ArrowKey.LEFT);
        this.showDefault();
    }

    /**
     * @return <code>true</code> if the cursor is after the last char of the complete text.
     */
    private boolean isCursorAfterText() {
        return this.cursorPosition == this.totalText.length();
    }

    /**
     * @return <code>true</code> if the cursor is after the last char of the visible text.
     */
    private boolean isCursorAtEndOfLine() {
        return this.cursorPosition == this.displayedTextPosition + this.content.length();
    }

    /**
     * @return <code>true</code> if the cursor is positioned left at the visible area and there is other char left from the cursor.
     */
    private boolean cursorIsLeft() {
        return this.cursorPosition == this.displayedTextPosition && this.displayedTextPosition > 0;
    }

    @Override
    public void deleteText() {
        this.content = "";
        this.text.setText("");
        this.totalText = "";
        this.cursorPosition = 0;
        this.displayedTextPosition = 0;
        this.cursor.setLeft(this, PositionRelativeLeft.INSIDE_LEFT, this.text.getLeft());
        this.cursor.setTop(this.text.getTop());
        this.showDefault();
    }

    private void showDefault() {
        if (!this.focused && this.text.getText().isEmpty()) {
            this.defaultMessage.show();
        } else {
            this.defaultMessage.hide();
        }
    }

    @Override
    protected void highlightImpl(final boolean over) {
        if (over) {
            this.focused = true;
            this.cursor.show();
        } else {
            this.focused = false;
            this.cursor.hide();
        }
        this.showDefault();
    }

    @Override
    public InputBoxGui setPassword() {
        this.password = true;
        return this;
    }

    @Override
    protected void delete() {
        // FIXME leak, container not deleted, see guibuilder
        this.background.delete();
        this.captionText.delete();
        this.text.delete();
    }

    @Override
    protected void showImpl() {
        this.text.show();
        this.background.show();
        this.captionText.show();
        this.cursor.setVisible(this.focused);
        this.showDefault();
    }

    @Override
    protected void hideImpl() {
        this.text.hide();
        this.background.hide();
        this.captionText.hide();
        this.cursor.hide();
        this.defaultMessage.hide();
    }

    @Override
    public InputBoxGui setMaterial(final Material material) {
        this.background.setMaterial(material);
        return this;
    }

    @Override
    public InputBoxGui setTextAlignment(final PositionRelativeLeft left, final PositionRelativeTop top) {
        this.text.setLeft(this, left, -this.getLeft());
        this.text.setTop(this, top, -this.getTop());
        // FIXME dirty fix to avoid the getLeft = 0 even if centered if text
        // empty
        this.addChar('a');
        this.removeChar();
        return this;
    }

    @Override
    public void setFont(final Font font) {
        this.captionText.setFont(font);
        this.text.setFont(font);
    }

    @Override
    public void setPasswordReplacement(final char replacement) {
        this.pwdReplacement = replacement;
    }

    @Override
    public String getText() {
        return this.totalText;
    }

    @Override
    public InputBoxGui setText(final String newText) {
        for (final char c : newText.toCharArray()) {
            this.addChar(c);
        }
        this.showDefault();
        return this;
    }

    @Override
    public InputBoxGui setText(final Key newText) {
        return this.setText(Translation.getInstance().translate(newText));
    }

    @Override
    protected Element setPositionImpl(final int left, final int top) {
        this.innerContainer.setPosition(left, top);
        //FIXME set top does not affect the position
        this.text.setTop(CAPTION_OFFSET);
        this.captionText.setPosition(left - this.captionText.getTextWidth() - CAPTION_OFFSET, CAPTION_OFFSET);
        return this;
    }

    @Override
    protected void setSizeImpl(final int w, final int h) {
        this.innerContainer.setSize(w, h);
        this.maxLineSize = w - 40.0f;
    }

    /**
     * Update the displayed text and the cursor position.
     */
    private void updateContent() {
        int size = 0;
        final StringBuilder builder = new StringBuilder();
        for (int i = this.displayedTextPosition; i < this.totalText.length() && size <= this.maxLineSize; i++) {
            char c;
            if (this.password) {
                c = this.pwdReplacement;
            } else {
                c = this.totalText.charAt(i);
            }
            builder.append(c);
            size = this.text.getFont().computeTextWidth(builder.toString());
        }
        this.content = builder.toString();
        this.text.setText(this.content);
        int cursorSize;
        int i = this.cursorPosition - this.displayedTextPosition;
        if (i < this.content.length()) {
            cursorSize = this.text.getFont().computeTextWidth(this.content.substring(0, i));
        } else {
            cursorSize = this.text.getFont().computeTextWidth(this.content);
        }
        this.cursor.setLeft(this, PositionRelativeLeft.INSIDE_LEFT, this.text.getLeft() + cursorSize);
        this.cursor.setTop(this.text.getTop());
    }

    @Override
    public InputBox setDefaultMessage(String message) {
        this.defaultMessage.setText(message);
        return this;
    }

    @Override
    public InputBox setDefaultMessage(Key message) {
        return this.setDefaultMessage(Translation.getInstance().translate(message));
    }

    @Override
    public InputBoxGui setTextCenter() {
        this.setTextAlignment(PositionRelativeLeft.CENTER, PositionRelativeTop.CENTER);
        return this;
    }

    /**
     * Template to build the input box.
     *
     * @author Van den Borre Grégory
     */
    public static final class InputBoxTemplate {

        /**
         * Input box size.
         */
        private final Size size;

        /**
         * Input box base material.
         */
        private final Material material;

        /**
         * Input box material when selected.
         */
        private final Material focus;

        /**
         * Input box cursor material.
         */
        private final Material cursor;

        /**
         * Input box font.
         */
        private final Font font;
        private final Font captionFont;

        public InputBoxTemplate(Size size, Material material, Material focus, Material cursor, Font font) {
            this.size = size;
            this.material = material;
            this.focus = focus;
            this.cursor = cursor;
            this.font = font;
            this.captionFont = font;
        }

        public InputBoxTemplate(Size size, Material material, Material focus, Material cursor, Font font, Font captionFont) {
            this.size = size;
            this.material = material;
            this.focus = focus;
            this.cursor = cursor;
            this.font = font;
            this.captionFont = captionFont;
        }

        public Size getSize() {
            return size;
        }

        public Material getMaterial() {
            return material;
        }

        public Material getFocus() {
            return focus;
        }

        public Material getCursor() {
            return cursor;
        }

        public Font getFont() {
            return font;
        }

        public Font getCaptionFont() {
            return captionFont;
        }
    }

    /**
     * Listener to associate to an input box to update the text displayed when the user is typing.
     *
     * @author Grégory Van den Borre
     */
    private final class InputBoxListener implements KeyboardListener, MouseLeftClickListener {

        /**
         * Simple constructor.
         */
        private InputBoxListener() {
            super();
        }

        @Override
        public boolean keyPressed(final char key) {
            InputBoxGui.this.addChar(key);
            return true;
        }

        @Override
        public boolean deleteKeyPressed() {
            InputBoxGui.this.removeChar();
            return true;
        }

        @Override
        public boolean arrowKeyPressed(final ArrowKey arrow) {
            if (arrow == ArrowKey.LEFT) {
                if (InputBoxGui.this.cursorIsLeft()) {
                    InputBoxGui.this.displayedTextPosition--;
                }
                InputBoxGui.this.cursorPosition--;
                if (InputBoxGui.this.cursorPosition < 0) {
                    InputBoxGui.this.cursorPosition = 0;
                }
                InputBoxGui.this.updateContent();
                return true;
            }
            if (arrow == ArrowKey.RIGHT) {
                if (!InputBoxGui.this.isCursorAfterText() && InputBoxGui.this.isCursorAtEndOfLine() && InputBoxGui.this.text.getFont().computeTextWidth(InputBoxGui.this.totalText) > InputBoxGui.this.maxLineSize) {
                    InputBoxGui.this.displayedTextPosition++;
                }
                InputBoxGui.this.cursorPosition++;
                if (InputBoxGui.this.cursorPosition > InputBoxGui.this.totalText.length()) {
                    InputBoxGui.this.cursorPosition = InputBoxGui.this.totalText.length();
                }
                InputBoxGui.this.updateContent();
                return true;
            }
            return false;
        }

        @Override
        public void click() {
        }

        @Override
        public void clickAt(final MousePosition position) {
            highlightImpl(true);
            float cursorPixelPosition = position.getX() - getAbsoluteLeft();
            while (cursorPosition != displayedTextPosition) {
                this.arrowKeyPressed(ArrowKey.LEFT);
            }
            for (int i = 0; i < InputBoxGui.this.content.length(); i++) {
                if (InputBoxGui.this.cursor.getLeft() > cursorPixelPosition) {
                    break;
                }
                this.arrowKeyPressed(ArrowKey.RIGHT);
            }

        }
    }
}