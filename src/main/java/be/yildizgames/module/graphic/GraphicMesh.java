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

package be.yildizgames.module.graphic;

import be.yildizgames.module.graphic.material.Material;

/**
 * Contains the data for a graphic mesh file.
 *
 * @author Grégory Van den Borre
 */
public class GraphicMesh {

    /**
     * Mesh file path.
     */
    private final String file;

    /**
     * Material to use with the mesh.
     */
    private final Material material;

    /**
     * Full constructor.
     *
     * @param path     Path and name of the mesh to use(without extension, msh is expected as extension).
     * @param material Material to use with the mesh.
     * @deprecated Use mesh instead, this one use hard coded extension
     */
    @Deprecated
    public GraphicMesh(final String path, final Material material) {
        super();
        this.file = path + ".msh";
        this.material = material;
    }

    protected GraphicMesh(final String path, String extension, final Material material) {
        super();
        this.file = path + "." + extension;
        this.material = material;
    }

    /**
     * Create a new instance.
     *
     * @param path     Path and name of the mesh to use(without extension, msh is expected as extension).
     * @param material Material to use with the mesh.
     */
    public static GraphicMesh mesh(String path, Material material) {
        return new GraphicMesh(path, "msh", material);
    }

    public final String getFile() {
        return file;
    }

    public final Material getMaterial() {
        return material;
    }
}
