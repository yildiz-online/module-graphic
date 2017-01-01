/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.module.graphic;

/**
 * Possible auto constant values.
 *
 * @author Grégory Van den Borre
 */
public enum ShaderConstantType {
    /***/
    WORLD_MATRIX(0),
    /***/
    INVERSE_WORLD_MATRIX(1),
    /***/
    TRANSPOSE_WORLD_MATRIX(2),
    /***/
    INVERSE_TRANSPOSE_WORLD_MATRIX(3),
    /***/
    WORLD_MATRIX_ARRAY_3X4(4),
    /***/
    WORLD_MATRIX_ARRAY(5),
    /***/
    WORLD_DUALQUATERNION_ARRAY_2X4(6),
    /***/
    WORLD_SCALE_SHEAR_MATRIX_ARRAY_3X4(7),
    /***/
    VIEW_MATRIX(8),
    /***/
    INVERSE_VIEW_MATRIX(9),
    /***/
    TRANSPOSE_VIEW_MATRIX(10),
    /***/
    INVERSE_TRANSPOSE_VIEW_MATRIX(11),
    /***/
    PROJECTION_MATRIX(12),
    /***/
    INVERSE_PROJECTION_MATRIX(13),
    /***/
    TRANSPOSE_PROJECTION_MATRIX(14),
    /***/
    INVERSE_TRANSPOSE_PROJECTION_MATRIX(15),
    /***/
    VIEWPROJ_MATRIX(16),
    /***/
    INVERSE_VIEWPROJ_MATRIX(17),
    /***/
    TRANSPOSE_VIEWPROJ_MATRIX(18),
    /***/
    INVERSE_TRANSPOSE_VIEWPROJ_MATRIX(19),
    /***/
    WORLDVIEW_MATRIX(20),
    /***/
    INVERSE_WORLDVIEW_MATRIX(21),
    /***/
    TRANSPOSE_WORLDVIEW_MATRIX(22),
    /***/
    INVERSE_TRANSPOSE_WORLDVIEW_MATRIX(23),
    /***/
    WORLDVIEWPROJ_MATRIX(24),
    /***/
    INVERSE_WORLDVIEWPROJ_MATRIX(25),
    /***/
    TRANSPOSE_WORLDVIEWPROJ_MATRIX(26),
    /***/
    INVERSE_TRANSPOSE_WORLDVIEWPROJ_MATRIX(27),
    /***/
    RENDER_TARGET_FLIPPING(28),
    /***/
    VERTEX_WINDING(29),
    /***/
    FOG_COLOUR(30),
    /***/
    FOG_PARAMS(31),
    /***/
    SURFACE_AMBIENT_COLOUR(32),
    /***/
    SURFACE_DIFFUSE_COLOUR(33),
    /***/
    SURFACE_SPECULAR_COLOUR(34),
    /***/
    SURFACE_EMISSIVE_COLOUR(35),
    /***/
    SURFACE_SHININESS(36),
    /***/
    LIGHT_COUNT(37),
    /***/
    AMBIENT_LIGHT_COLOUR(38),
    /***/
    LIGHT_DIFFUSE_COLOUR(39),
    /***/
    LIGHT_SPECULAR_COLOUR(40),
    /***/
    LIGHT_ATTENUATION(41),
    /***/
    SPOTLIGHT_PARAMS(42),
    /***/
    LIGHT_POSITION(43),
    /***/
    LIGHT_POSITION_OBJECT_SPACE(44),
    /***/
    LIGHT_POSITION_VIEW_SPACE(45),
    /***/
    LIGHT_DIRECTION(46),
    /***/
    LIGHT_DIRECTION_OBJECT_SPACE(47),
    /***/
    LIGHT_DIRECTION_VIEW_SPACE(48),
    /***/
    LIGHT_DISTANCE_OBJECT_SPACE(49),
    /***/
    LIGHT_POWER_SCALE(50),
    /***/
    LIGHT_DIFFUSE_COLOUR_POWER_SCALED(51),
    /***/
    LIGHT_SPECULAR_COLOUR_POWER_SCALED(52),
    /***/
    LIGHT_DIFFUSE_COLOUR_ARRAY(53),
    /***/
    LIGHT_SPECULAR_COLOUR_ARRAY(54),
    /***/
    LIGHT_DIFFUSE_COLOUR_POWER_SCALED_ARRAY(55),
    /***/
    LIGHT_SPECULAR_COLOUR_POWER_SCALED_ARRAY(56),
    /***/
    LIGHT_ATTENUATION_ARRAY(57),
    /***/
    LIGHT_POSITION_ARRAY(58),
    /***/
    LIGHT_POSITION_OBJECT_SPACE_ARRAY(59),
    /***/
    LIGHT_POSITION_VIEW_SPACE_ARRAY(60),
    /***/
    LIGHT_DIRECTION_ARRAY(61),
    /***/
    LIGHT_DIRECTION_OBJECT_SPACE_ARRAY(62),
    /***/
    LIGHT_DIRECTION_VIEW_SPACE_ARRAY(63),
    /***/
    LIGHT_DISTANCE_OBJECT_SPACE_ARRAY(64),
    /***/
    LIGHT_POWER_SCALE_ARRAY(65),
    /***/
    SPOTLIGHT_PARAMS_ARRAY(66),
    /***/
    DERIVED_AMBIENT_LIGHT_COLOUR(67),
    /***/
    DERIVED_SCENE_COLOUR(68),
    /***/
    DERIVED_LIGHT_DIFFUSE_COLOUR(69),
    /***/
    DERIVED_LIGHT_SPECULAR_COLOUR(70),
    /***/
    DERIVED_LIGHT_DIFFUSE_COLOUR_ARRAY(71),
    /***/
    DERIVED_LIGHT_SPECULAR_COLOUR_ARRAY(72),
    /***/
    LIGHT_NUMBER(73),
    /***/
    LIGHT_CASTS_SHADOWS(74),
    /***/
    SHADOW_EXTRUSION_DISTANCE(75),
    /***/
    CAMERA_POSITION(76),
    /***/
    CAMERA_POSITION_OBJECT_SPACE(77),
    /***/
    TEXTURE_VIEWPROJ_MATRIX(78),
    /***/
    TEXTURE_VIEWPROJ_MATRIX_ARRAY(79),
    /***/
    TEXTURE_WORLDVIEWPROJ_MATRIX(80),
    /***/
    TEXTURE_WORLDVIEWPROJ_MATRIX_ARRAY(81),
    /***/
    SPOTLIGHT_VIEWPROJ_MATRIX(82),
    /***/
    SPOTLIGHT_VIEWPROJ_MATRIX_ARRAY(83),
    /***/
    SPOTLIGHT_WORLDVIEWPROJ_MATRIX(84),
    /***/
    CUSTOM(85),
    /***/
    TIME(86),
    /***/
    TIME_0_X(87),
    /***/
    COSTIME_0_X(88),
    /***/
    SINTIME_0_X(89),
    /***/
    TANTIME_0_X(90),
    /***/
    TIME_0_X_PACKED(91),
    /***/
    TIME_0_1(92),
    /***/
    COSTIME_0_1(93),
    /***/
    SINTIME_0_1(94),
    /***/
    TANTIME_0_1(95),
    /***/
    TIME_0_1_PACKED(96),
    /***/
    TIME_0_2PI(97),
    /***/
    COSTIME_0_2PI(98),
    /***/
    SINTIME_0_2PI(99),
    /***/
    TANTIME_0_2PI(100),
    /***/
    TIME_0_2PI_PACKED(101),
    /***/
    FRAME_TIME(102),
    /***/
    FPS(103),
    /***/
    VIEWPORT_WIDTH(104),
    /***/
    VIEWPORT_HEIGHT(105),
    /***/
    INVERSE_VIEWPORT_WIDTH(106),
    /***/
    INVERSE_VIEWPORT_HEIGHT(107),
    /***/
    VIEWPORT_SIZE(108),
    /***/
    VIEW_DIRECTION(109),
    /***/
    VIEW_SIDE_VECTOR(110),
    /***/
    VIEW_UP_VECTOR(111),
    /***/
    FOV(112),
    /***/
    NEAR_CLIP_DISTANCE(113),
    /***/
    FAR_CLIP_DISTANCE(114),
    /***/
    PASS_NUMBER(115),
    /***/
    PASS_ITERATION_NUMBER(116),
    /***/
    ANIMATION_PARAMETRIC(117),
    /***/
    TEXEL_OFFSETS(118),
    /***/
    SCENE_DEPTH_RANGE(119),
    /***/
    SHADOW_SCENE_DEPTH_RANGE(120),
    /***/
    SHADOW_COLOUR(121),
    /***/
    TEXTURE_SIZE(122),
    /***/
    INVERSE_TEXTURE_SIZE(123),
    /***/
    PACKED_TEXTURE_SIZE(124),
    /***/
    TEXTURE_MATRIX(125),
    /***/
    LOD_CAMERA_POSITION(126),
    /***/
    LOD_CAMERA_POSITION_OBJECT_SPACE(127),
    /***/
    LIGHT_CUSTOM(128);

    /**
     * Associated value.
     */
    private final int value;

    /**
     * Full constructor.
     *
     * @param value Associated value.
     */
    ShaderConstantType(final int value) {
        this.value = value;
    }

    /**
     * @return The associated value.
     */
    public int getValue() {
        return this.value;
    }
}
