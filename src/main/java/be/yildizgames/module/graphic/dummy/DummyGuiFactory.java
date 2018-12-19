package be.yildizgames.module.graphic.dummy;

import be.yildizgames.common.client.translation.TranslationKey;
import be.yildizgames.common.frame.FrameListener;
import be.yildizgames.module.graphic.gui.GuiFactory;
import be.yildizgames.module.graphic.gui.button.Button;
import be.yildizgames.module.graphic.gui.button.ButtonBuilder;
import be.yildizgames.module.graphic.gui.button.ButtonTemplate;
import be.yildizgames.module.graphic.gui.checkbox.CheckBoxBuilder;
import be.yildizgames.module.graphic.gui.container.Container;
import be.yildizgames.module.graphic.gui.container.ContainerBuilder;
import be.yildizgames.module.graphic.gui.image.ImageBuilder;
import be.yildizgames.module.graphic.gui.inputbox.InputBoxBuilder;
import be.yildizgames.module.graphic.gui.progressbar.ProgressBarBuilder;
import be.yildizgames.module.graphic.gui.textarea.TextAreaBuilder;
import be.yildizgames.module.graphic.gui.textline.TextLine;
import be.yildizgames.module.graphic.gui.textline.TextLineBuilder;
import be.yildizgames.module.graphic.gui.textline.TextLineTemplate;
import be.yildizgames.module.window.ScreenSize;
import be.yildizgames.module.window.input.MouseLeftClickListener;

public class DummyGuiFactory implements GuiFactory {

    @Override
    public ContainerBuilder container() {
        return null;
    }

    @Override
    public ButtonBuilder button() {
        return null;
    }


    @Override
    public TextLineBuilder textLine() {
        return null;
    }

    @Override
    public TextLine textLine(Container parent, TextLineTemplate template) {
        return null;
    }

    @Override
    public TextLine textLine(Container parent, TextLineTemplate template, TranslationKey text) {
        return null;
    }

    @Override
    public TextAreaBuilder textArea() {
        return null;
    }

    @Override
    public CheckBoxBuilder checkbox() {
        return null;
    }

    @Override
    public ImageBuilder image() {
        return null;
    }

    @Override
    public FrameListener getAnimationManager() {
        return null;
    }

    @Override
    public InputBoxBuilder inputBox() {
        return null;
    }

    @Override
    public ProgressBarBuilder progressBar() {
        return null;
    }

    @Override
    public ScreenSize getScreenSize() {
        return new ScreenSize(1,1);
    }
}
