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

package be.yildizgames.module.graphic.gui.image.animation;

import be.yildizgames.common.time.ManualElapsedTimeComputer;
import be.yildizgames.module.graphic.gui.image.ImageAnimation;

/**
 * Animation to show and hide an image alternatively.
 * @author Grégory Van den Borre
 */
public class BlinkingImage extends ImageAnimation {

    /**
     * Timer between 2 state switches.
     */
    private final ManualElapsedTimeComputer timer;

    /**
     * Create a blinking image animation.
     * @param name Animation name.
     * @param time Time between show and hide states.
     */
    public BlinkingImage(final String name, final long time) {
        super(name);
        this.timer = new ManualElapsedTimeComputer(time);
    }

    @Override
    protected final void updateImpl(final long time) {
        if(timer.isTimeElapsed(time)) {
            this.image.setVisible(!this.image.isVisible());
        }
    }

    @Override
    protected final void startImpl() {
        //Nothing to do.
    }
}
