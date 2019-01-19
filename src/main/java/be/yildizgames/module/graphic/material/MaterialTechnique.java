/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
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

package be.yildizgames.module.graphic.material;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A technique is an element of a material, it contains MaterialPass.
 *
 * @author Grégory Van den Borre
 */
public abstract class MaterialTechnique implements Comparable<MaterialTechnique> {

    /**
     * List of all MaterialPass contained in the technique.
     */
    private final List<MaterialPass> materialPassList = new ArrayList<>();

    /**
     * Technique index.
     */
    private final int index;

    /**
     * Protected constructor, only called from children.
     *
     * @param techniqueIndex Value of the index.
     */
    protected MaterialTechnique(final int techniqueIndex) {
        super();
        this.index = techniqueIndex;
    }

    /**
     * Protected copy constructor, only called from children.
     *
     * @param techniqueIndex Value of the index.
     * @param passList       List of pass contained in this technique.
     */
    protected MaterialTechnique(final int techniqueIndex, final List<MaterialPass> passList) {
        super();
        this.materialPassList.addAll(passList);
        this.index = techniqueIndex;
    }

    /**
     * Create a new MaterialPass and insert it in the list.
     *
     * @return The newly created MaterialPass.
     */
    public final MaterialPass createTexturePass() {
        final MaterialPass pass = this.createPassImpl(this.materialPassList.size());
        this.materialPassList.add(pass);
        return pass;
    }

    /**
     * Call implementation to create a new MaterialPass.
     *
     * @param index Pass index.
     * @return the created pass.
     */
    protected abstract MaterialPass createPassImpl(int index);

    /**
     * Get a pass used with this technique, throws InvalidParameterException If
     * no technique is found for the given parameter.
     *
     * @param indexValue Index of the technique used, first is 0.
     * @return The associated technique.
     */
    public final MaterialPass getPass(final int indexValue) {
        final MaterialPass p = this.materialPassList.get(indexValue);
        if (p == null) {
            throw new InvalidParameterException("No technique found for this index.");
        }
        return p;
    }

    /**
     * Activate glow for this technique.
     */
    public final void setGlow() {
        this.setGlowImpl();
    }

    /**
     * @return An unmodifiable list of all pass for this technique.
     */
    final List<MaterialPass> getPassList() {
        return Collections.unmodifiableList(this.materialPassList);
    }

    /**
     * Activate glow for this technique in implementation.
     */
    protected abstract void setGlowImpl();

    /**
     * Compare 2 MaterialTechnique based on their index number.
     *
     * @param other Other technique to compare.
     * @return 0
     */
    @Override
    public final int compareTo(final MaterialTechnique other) {
        if (this.index < other.index) {
            return -1;
        } else if (this.index > other.index) {
            return 1;
        }
        return 0;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.index;
        result = prime * result + this.materialPassList.hashCode();
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MaterialTechnique)) {
            return false;
        }
        MaterialTechnique other = (MaterialTechnique) obj;
        if (this.index != other.index) {
            return false;
        }
        if (!this.materialPassList.equals(other.materialPassList)) {
            return false;
        }
        return true;
    }
}
