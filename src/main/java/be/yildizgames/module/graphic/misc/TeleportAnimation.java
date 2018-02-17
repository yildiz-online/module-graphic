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

package be.yildizgames.module.graphic.misc;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.graphic.animation.Animation;
import be.yildizgames.module.graphic.billboard.Billboard;
import be.yildizgames.module.graphic.billboard.BillboardSet;

import java.util.Optional;

/**
 * Animation to play when an entity is making a teleportation.
 *
 * @author Grégory Van den Borre
 */
public final class TeleportAnimation implements Animation {

    /**
     * Time to execute the animation.
     */
    private static final long TIME = 200;

    /**
     * Billboard maximum size.
     */
    private static final int MAX_SIZE = 1000;
    /**
     * Set to create the billboards for the animation.
     */
    private final BillboardSet set;
    /**
     * Current animation running time.
     */
    private long runningTime;
    /**
     * Billboard used when animation is playing, <code>null</code> if animation is stopped.
     */
    private Billboard current = null;

    private Point3D positionToSet = Point3D.ZERO;

    /**
     * Full constructor.
     *
     * @param set Set to create the billboards for the animation.
     */
    public TeleportAnimation(final BillboardSet set) {
        super();
        this.set = set;
    }

    @Override
    public void start() {
        Optional.ofNullable(this.current).ifPresent(this.set::removeBillboard);
        this.current = this.set.createBillboard();
        this.current.setPosition(this.positionToSet);
    }

    @Override
    public boolean runOneFrame(final long time) {
        this.runningTime += time;
        if (this.runningTime > TIME) {
            this.runningTime = 0;
            this.set.removeBillboard(this.current);
            this.current = null;
            return false;
        } else {
            float v = ((float) this.runningTime / (float) (TIME)) * MAX_SIZE;
            this.current.setSize(v, v);
        }
        return true;
    }

    @Override
    public void setPosition(final Point3D position) {
        if (this.current == null) {
            this.positionToSet = position;
        } else {
            this.current.setPosition(position);
        }
    }

}
