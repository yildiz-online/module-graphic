/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2018 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE  SOFTWARE.
 */

package be.yildizgames.module.graphic.animation;

import be.yildizgames.common.frame.FrameManager;
import be.yildizgames.common.geometry.Point3D;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Register and play animations.
 *
 * @author Grégory Van den Borre
 */
public final class AnimManager {

    /**
     * List of all animations.
     */
    private final Map<String, List<Animation>> animationList = new HashMap<>();

    private final FrameManager frameManager;

    public AnimManager(FrameManager frameManager) {
        super();
        this.frameManager = frameManager;
    }


    /**
     * Register an animation.
     *
     * @param type     Name of the animation, used to retrieve it.
     * @param animPool Pool of animations to play.
     */
    public void addAnimation(final String type, final List<Animation> animPool) {
        this.animationList.put(type, animPool);
    }

    /**
     * Starts an animation.
     *
     * @param type     Type of the animation to play.
     * @param position Animation position.
     */
    public void play(final String type, final Point3D position) {
        this.animationList.getOrDefault(type, Collections.emptyList())
                .stream()
                .filter(Animation::isNotPlaying)
                .findFirst()
                .ifPresent(a -> this.frameManager.addFrameListener(a.startAtPosition(position)));
    }

}
