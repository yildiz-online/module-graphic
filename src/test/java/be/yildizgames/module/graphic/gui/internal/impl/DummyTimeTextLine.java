package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.common.geometry.Rectangle;
import be.yildizgames.module.color.Color;
import be.yildizgames.module.coordinate.BaseCoordinate;
import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.coordinate.Relative;
import be.yildizgames.module.coordinate.Size;
import be.yildizgames.module.graphic.Font;
import be.yildizgames.module.graphic.gui.ContainerChild;
import be.yildizgames.module.graphic.gui.OnMouseOverListener;
import be.yildizgames.module.graphic.gui.PositionRelativeLeft;
import be.yildizgames.module.graphic.gui.PositionRelativeTop;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.internal.BaseAnimationGui;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.graphic.gui.textline.TextLine;
import be.yildizgames.module.graphic.gui.textline.TimeTextLine;
import be.yildizgames.module.window.input.KeyboardListener;
import be.yildizgames.module.window.input.MouseDoubleLeftClickListener;
import be.yildizgames.module.window.input.MouseDragListener;
import be.yildizgames.module.window.input.MouseLeftClickListener;
import be.yildizgames.module.window.input.MouseMoveListener;
import be.yildizgames.module.window.input.MousePosition;
import be.yildizgames.module.window.input.MouseReleaseListener;
import be.yildizgames.module.window.input.MouseRightClickListener;
import be.yildizgames.module.window.input.MouseWheelListener;

/**
 * @author Grégory Van den Borre
 */
public class DummyTimeTextLine implements TimeTextLine {

    @Override
    public void display(long time) {

    }

    @Override
    public TextLine setText(String text) {
        return this;
    }

    @Override
    public TextLine setText(TranslationKey key) {
        return this;
    }

    @Override
    public TextLine setText(TranslationKey.MultiKey key) {
        return this;
    }

    @Override
    public TextLine setText(int i) {
        return this;
    }

    @Override
    public TextLine setColor(Color color) {
        return this;
    }

    @Override
    public void setFont(Font font) {

    }

    @Override
    public TextLine setTextPosition(TextPosition textPosition) {
        return this;
    }

    @Override
    public String getContent() {
        return "";
    }

    @Override
    public void detachFromParent() {

    }

    @Override
    public ContainerChild setLeftFromParent(PositionRelativeLeft p) {
        return this;
    }

    @Override
    public ContainerChild setLeftFromParent(PositionRelativeLeft relative, int diff) {
        return this;
    }

    @Override
    public ContainerChild setTopFromParent(PositionRelativeTop relative) {
        return this;
    }

    @Override
    public ContainerChild setTopFromParent(PositionRelativeTop relative, int diff) {
        return this;
    }

    @Override
    public Container getParent() {
        return new DummyContainer();
    }

    @Override
    public void updateAddPositionValue(int left, int top) {

    }

    @Override
    public void updateSizeAfterZoom(float zoomFactor) {

    }

    @Override
    public void addMouseLeftClickListener(MouseLeftClickListener listener) {

    }

    @Override
    public void addMouseRightClickListener(MouseRightClickListener listener) {

    }

    @Override
    public void addMouseDoubleLeftClickListener(MouseDoubleLeftClickListener listener) {

    }

    @Override
    public void addMouseReleaseListener(MouseReleaseListener listener) {

    }

    @Override
    public void removeAllClickListeners() {

    }

    @Override
    public void addKeyboardListener(KeyboardListener listener) {

    }

    @Override
    public void addMouseMoveListener(MouseMoveListener listener) {

    }

    @Override
    public void addMouseWheelListener(MouseWheelListener listener) {

    }

    @Override
    public void addMouseDragListener(MouseDragListener listener) {

    }

    @Override
    public void addOnMouseOverListener(OnMouseOverListener listener) {

    }

    @Override
    public boolean contains(MousePosition position) {
        return false;
    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    @Override
    public void addEmptyZone(Rectangle zone) {

    }

    @Override
    public void align(Alignment alignment) {

    }

    @Override
    public int getAbsoluteLeft() {
        return 0;
    }

    @Override
    public int getAbsoluteTop() {
        return 0;
    }

    @Override
    public void registerAnimation(BaseAnimationGui anim) {

    }

    @Override
    public void playAnimation(String animation) {

    }

    @Override
    public void stopAnimation(String name) {

    }

    @Override
    public void focus(boolean b) {

    }

    @Override
    public boolean isFocusable() {
        return false;
    }

    @Override
    public void highlight(boolean b) {

    }

    @Override
    public Element hide() {
        return this;
    }

    @Override
    public Element setPosition(Element other) {
        return this;
    }

    @Override
    public Element show() {
        return this;
    }

    @Override
    public Element setTop(Element other, PositionRelativeTop relative, int diff) {
        return this;
    }

    @Override
    public Element setTop(Element other, PositionRelativeTop top, Relative relativeDiff) {
        return this;
    }

    @Override
    public Element setTop(Element other, PositionRelativeTop relative) {
        return this;
    }

    @Override
    public Element setLeft(Element other, PositionRelativeLeft relative) {
        return this;
    }

    @Override
    public Element setLeft(Element other, PositionRelativeLeft relative, int diff) {
        return this;
    }

    @Override
    public Element setLeft(Element other, PositionRelativeLeft left, Relative relativeDiff) {
        return this;
    }

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public Element setLeft(int left) {
        return this;
    }

    @Override
    public int getTop() {
        return 0;
    }

    @Override
    public Element setTop(int top) {
        return this;
    }

    @Override
    public void addToPosition(int xValue, int yValue) {

    }

    @Override
    public void addToLeft(int value) {

    }

    @Override
    public void addToTop(int value) {

    }

    @Override
    public Element setPosition(int left, int top) {
        return this;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public void setWidth(int width) {

    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setHeight(int height) {

    }

    @Override
    public int getBottom() {
        return 0;
    }

    @Override
    public void setSize(Size size) {

    }

    @Override
    public BaseCoordinate getCoordinates() {
        return Coordinates.ZERO;
    }

    @Override
    public void setCoordinates(BaseCoordinate coordinates) {

    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public Element setVisible(boolean visible) {
        return this;
    }

    @Override
    public void setSize(int newWidth, int newHeight) {

    }

    @Override
    public int getRight() {
        return 0;
    }

    @Override
    public void setVirtualHeight(int height) {

    }

    @Override
    public void resetVirtualHeight() {

    }

    @Override
    public String getName() {
        return "";
    }
}
