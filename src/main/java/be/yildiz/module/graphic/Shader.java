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

package be.yildiz.module.graphic;

/**
 * A shader data, can be fragment, vertex or geometry shader.
 *
 * @author Grégory Van den Borre
 */
public abstract class Shader {

    /**
     * Shader unique name.
     */
    private final String name;

    /**
     * Shader type.
     */
    private final ShaderType type;

    protected Shader(String name, ShaderType type) {
        super();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ShaderType getType() {
        return type;
    }

    /**
     * Possible shader types.
     *
     * @author Van den Borre Grégory
     */
    public enum ShaderType {

        /**
         * Pixel shader.
         */
        FRAGMENT(0),

        /**
         * Vertex shader.
         */
        VERTEX(1),

        /**
         * Geometry shader.
         */
        GEOMETRY(2);

        /**
         * Associated enum value.
         */
        private final int value;

        /**
         * Full constructor.
         *
         * @param v Associated enum value.
         */
        ShaderType(final int v) {
            this.value = v;
        }

        /**
         * @return The value.
         */
        public int getValue() {
            return value;
        }
    }

    /**
     * DirectX fragment profiles.
     *
     * @author Van den Borre Grégory
     */
    public enum FragmentDx {

        /**
         * DirectX pixel shader 1.1.
         */
        PS_1_1("ps_1_1"),

        /**
         * DirectX pixel shader 2.0.
         */
        PS_2_0("ps_2_0");

        /**
         * Associated name.
         */
        private final String name;

        /**
         * Full constructor.
         *
         * @param name Profile associated name.
         */
        FragmentDx(final String name) {
            this.name = name;
        }
    }

    /**
     * OpenGL fragment profiles.
     *
     * @author Van den Borre Grégory
     */
    public enum FragmentGL {

        /**
         * OpenGL fragment program 1.
         */
        ARBFP1("arbfp1");

        /**
         * Associated name.
         */
        private final String name;

        /**
         * Full constructor.
         *
         * @param name Profile associated name.
         */
        FragmentGL(final String name) {
            this.name = name;
        }
    }

    /**
     * DirectX vertex profiles.
     *
     * @author Van den Borre Grégory
     */
    public enum VertexDx {

        /**
         * DirectX vertex shader 1.1.
         */
        VS_1_1("vs_1_1");

        /**
         * Associated name.
         */
        private final String name;

        /**
         * Full constructor.
         *
         * @param name Profile associated name.
         */
        VertexDx(final String name) {
            this.name = name;
        }
    }

    /**
     * OpenGL vertex profiles.
     *
     * @author Van den Borre Grégory
     */
    public enum VertexGL {

        /**
         * OpenGL vertex program 1.
         */
        ARBVP1("arbvp1");

        /**
         * Associated name.
         */
        private final String name;

        /**
         * Full constructor.
         *
         * @param name Profile associated name.
         */
        VertexGL(final String name) {
            this.name = name;
        }
    }

    /**
     * Exception to throw in case of the shader type not being the one expected.
     *
     * @author Van den Borre Grégory
     */
    public static final class ShaderTypeException extends RuntimeException {

        /***/
        private static final long serialVersionUID = 7602881241212061971L;

    }

    /**
     * Base class for shader profiles list.
     *
     * @author Van den Borre Grégory
     */
    public static class ShaderProfile {

        /**
         * Profile value.
         */
        private final String name;

        ShaderProfile(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Contains the profile to use for a fragment shader.
     *
     * @author Van den Borre Grégory
     */
    public static final class FragmentProfileList extends ShaderProfile {

        /**
         * Create the profile.
         *
         * @param dx DirectX fragment profile.
         * @param gl OpenGL fragment profile.
         */
        public FragmentProfileList(final FragmentDx dx, final FragmentGL gl) {
            super(dx.name + " " + gl.name);
        }
    }

    /**
     * Contains the profile to use for a vertex shader.
     *
     * @author Van den Borre Grégory
     */
    public static final class VertexProfileList extends ShaderProfile {

        /**
         * Create the profile.
         *
         * @param dx DirectX vertex profile.
         * @param gl OpenGL vertex profile.
         */
        public VertexProfileList(final VertexDx dx, final VertexGL gl) {
            super(dx.name + " " + gl.name);
        }
    }
}
