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

package be.yildizgames.module.graphic.gui.container.animation;

import be.yildizgames.module.graphic.gui.container.ContainerAnimation;

/**
 * Set a container to the bottom of its current position, and once started, go up to the initial position.
 * @author Grégory Van den Borre
 */
public class MoveFromBottom extends ContainerAnimation {

    private final int speed;

    private int positionToReach;

    public MoveFromBottom(String name, int speed) {
        super(name);
        this.speed = speed;
    }

    @Override
    protected final void updateImpl(long time) {
        int top = this.container.getTop() - speed * (int) time;
        if(top < this.positionToReach) {
            top = positionToReach;
        }
        this.container.setTop(top);
        this.setCompleted(top == this.positionToReach);
    }

    /**
     * Set the container top position to its bottom position.
     * Set the position to reach to its top position.
     */
    @Override
    protected final void startImpl() {
        this.positionToReach = this.container.getTop();
        this.container.setTop(this.container.getBottom());
    }
}
