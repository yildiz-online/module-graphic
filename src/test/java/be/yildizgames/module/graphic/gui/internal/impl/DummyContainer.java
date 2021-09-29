package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.coordinates.Coordinates;
import be.yildizgames.module.coordinates.FullCoordinates;
import be.yildizgames.module.graphic.gui.Zorder;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.graphic.material.Material;

/**
 * @author Grégory Van den Borre
 */
public class DummyContainer extends SimpleContainer {


    protected DummyContainer(String name, Coordinates coordinates, Material backgroundMaterial, SimpleContainer parent, boolean widget) {
        super(name, coordinates, backgroundMaterial, parent, widget);
    }

    protected DummyContainer() {
        super(StringUtil.buildRandomString("ct"), FullCoordinates.ZERO, Material.empty(), false);
    }

    @Override
    public String getElementName(int x, int y) {
        return "";
    }

    @Override
    protected void addChildrenPositionImpl(int left, int top) {

    }

    @Override
    protected void zoomImpl(float factor) {

    }

    @Override
    protected void setMaterialImpl(Material newMaterial) {

    }

    @Override
    protected void setZImpl(Zorder z) {

    }

    @Override
    public void delete() {

    }

    @Override
    protected void showImpl() {

    }

    @Override
    protected void hideImpl() {

    }

    @Override
    protected void setSizeImpl(int newWidth, int newHeight) {

    }

    @Override
    protected Element setPositionImpl(int newLeft, int newTop) {
        return this;
    }
}
