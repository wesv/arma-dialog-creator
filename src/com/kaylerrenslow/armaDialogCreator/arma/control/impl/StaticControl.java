/*
 * Copyright (c) 2016 Kayler Renslow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. in no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.
 */

package com.kaylerrenslow.armaDialogCreator.arma.control.impl;

import com.kaylerrenslow.armaDialogCreator.arma.control.ArmaControl;
import com.kaylerrenslow.armaDialogCreator.arma.control.ArmaControlSpecRequirement;
import com.kaylerrenslow.armaDialogCreator.arma.util.ArmaResolution;
import com.kaylerrenslow.armaDialogCreator.control.*;
import com.kaylerrenslow.armaDialogCreator.expression.Env;
import com.kaylerrenslow.armaDialogCreator.util.ArrayUtil;
import com.kaylerrenslow.armaDialogCreator.util.ReadOnlyList;
import org.jetbrains.annotations.NotNull;

/**
 Used for a controls with type=0 (Static)

 @author Kayler
 @since 05/25/2016. */
public class StaticControl extends ArmaControl {

	public final static ArmaControlSpecRequirement SPEC_PROVIDER = new ArmaControlSpecRequirement() {

		private final ReadOnlyList<ControlPropertyLookupConstant> requiredProperties = new ReadOnlyList<>(ArrayUtil.mergeAndSort(ControlPropertyLookupConstant.class, defaultRequiredProperties,
				new ControlPropertyLookup[]{
						ControlPropertyLookup.COLOR_BACKGROUND,
						ControlPropertyLookup.COLOR_TEXT,
						ControlPropertyLookup.TEXT,
						ControlPropertyLookup.FONT,
						ControlPropertyLookup.SIZE_EX
				},
				ControlPropertyLookupConstant.PRIORITY_SORT)
		);

		private final ReadOnlyList<ControlPropertyLookupConstant> optionalProperties = new ReadOnlyList<>(ArrayUtil.mergeAndSort(ControlPropertyLookupConstant.class, defaultOptionalProperties,
				new ControlPropertyLookup[]{
						ControlPropertyLookup.MOVING,
						ControlPropertyLookup.SHADOW,
						ControlPropertyLookup.TOOLTIP,
						ControlPropertyLookup.TOOLTIP_COLOR_SHADE,
						ControlPropertyLookup.TOOLTIP_COLOR_BOX,
						ControlPropertyLookup.TOOLTIP_COLOR_TEXT,
						ControlPropertyLookup.STATIC_FIXED_WIDTH,
						ControlPropertyLookup.STATIC_LINE_SPACING,
						ControlPropertyLookup.BLINKING_PERIOD
				},
				ControlPropertyLookupConstant.PRIORITY_SORT)
		);

		@NotNull
		@Override
		public ReadOnlyList<ControlPropertyLookupConstant> getRequiredProperties() {
			return requiredProperties;
		}

		@NotNull
		@Override
		public ReadOnlyList<ControlPropertyLookupConstant> getOptionalProperties() {
			return optionalProperties;
		}

		private final ControlStyle[] allowedStyles = {
				ControlStyle.LEFT,
				ControlStyle.RIGHT,
				ControlStyle.CENTER,
				ControlStyle.MULTI,
				ControlStyle.TITLE_BAR,
				ControlStyle.PICTURE,
				ControlStyle.FRAME,
				ControlStyle.BACKGROUND,
				ControlStyle.GROUP_BOX,
				ControlStyle.GROUP_BOX2,
				ControlStyle.HUD_BACKGROUND,
				ControlStyle.WITH_RECT,
				ControlStyle.LINE,
				ControlStyle.SHADOW,
				ControlStyle.NO_RECT,
				ControlStyle.KEEP_ASPECT_RATIO
		};

		@Override
		public ControlStyle[] getAllowedStyles() {
			return allowedStyles;
		}
	};

	public StaticControl(@NotNull String name, int idc, @NotNull ArmaResolution resolution, @NotNull Env env, @NotNull SpecificationRegistry registry) {
		super(ControlType.STATIC, name, SPEC_PROVIDER, idc, ControlStyle.CENTER.getStyleGroup(), resolution, RendererLookup.STATIC, env, registry);
	}

}
