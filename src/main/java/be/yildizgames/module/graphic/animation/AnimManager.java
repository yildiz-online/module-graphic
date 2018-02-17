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

import be.yildizgames.common.frame.EndFrameListener;
import be.yildizgames.common.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Register and play animations.
 *
 * @author Grégory Van den Borre
 */
public final class AnimManager extends EndFrameListener {

    /**
     * List of all animations.
     */
    private final Map<String, List<Animation>> animationList = new HashMap<>();

    /**
     * List of running animations.
     */
    private final Map<String, List<Animation>> currentAnimation = new HashMap<>();

    /**
     * Register an animation.
     *
     * @param type     Name of the animation, used to retrieve it.
     * @param animPool Pool of animations to play.
     */
    public void addAnimation(final String type, final List<Animation> animPool) {
        this.animationList.put(type, animPool);
        this.currentAnimation.put(type, new ArrayList<>());
    }

    /**
     * Starts an animation.
     *
     * @param type     Type of the animation to play.
     * @param position Animation position.
     */
    public void play(final String type, final Point3D position) {
        Animation aa = this.animationList.get(type).remove(0);
        this.currentAnimation.get(type).add(aa);
        aa.start();
        aa.setPosition(position);
    }

    @Override
    protected boolean frameEnded(final long time) {
        for (String key : this.currentAnimation.keySet()) {
            List<Animation> al = this.animationList.get(key);
            List<Animation> ca = this.currentAnimation.get(key);
            for (int i = 0; i < ca.size(); i++) {
                if (!ca.get(i).runOneFrame(time)) {
                    Animation aa = ca.remove(i);
                    al.add(aa);
                    i--;
                }
            }
        }
        return true;
    }
}
