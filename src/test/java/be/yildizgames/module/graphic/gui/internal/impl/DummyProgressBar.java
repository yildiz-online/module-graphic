package be.yildizgames.module.graphic.gui.internal.impl;

import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.coordinate.Coordinates;
import be.yildizgames.module.graphic.gui.internal.Element;
import be.yildizgames.module.graphic.material.Material;

/**
 * @author Grégory Van den Borre
 */
public class DummyProgressBar extends BaseProgressBar {

    protected DummyProgressBar() {
        super(StringUtil.buildRandomString("pb"), Coordinates.ZERO, new DummyContainer());
    }

    @Override
    protected void updateView(float progress) {

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
    protected Element setPositionImpl(int newLeft, int newTop) {
        return this;
    }

    @Override
    public void setContentMaterial(Material mat) {

    }
}
