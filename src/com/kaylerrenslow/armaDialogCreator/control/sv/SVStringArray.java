/*
 * Copyright (c) 2016 Kayler Renslow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. in no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.
 */

package com.kaylerrenslow.armaDialogCreator.control.sv;

import com.kaylerrenslow.armaDialogCreator.util.DataContext;
import com.kaylerrenslow.armaDialogCreator.util.ValueConverter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 Created by Kayler on 07/13/2016.
 */
public final class SVStringArray extends SerializableValue {
	
	public static final ValueConverter<SVStringArray> CONVERTER = new ValueConverter<SVStringArray>() {
		@Override
		public SVStringArray convert(DataContext context, @NotNull String... values) throws Exception {
			return new SVStringArray(values);
		}
	};
	
	public SVStringArray(String... strings) {
		super(strings);
	}
	
	/** Set the string at index equal to s */
	public void setString(String s, int index) {
		this.valuesAsArray[index] = s;
	}
	
	public void setStrings(String[] strings) {
		for (int i = 0; i < strings.length; i++) {
			valuesAsArray[i] = strings[i];
		}
	}
	
	@Override
	public String toString() {
		String ret = "{";
		for (int i = 0; i < valuesAsArray.length; i++) {
			ret += valuesAsArray[i] + (i != valuesAsArray.length - 1 ? ", " : "}");
		}
		return ret;
	}
	
	@Override
	public SerializableValue deepCopy() {
		String[] copy = new String[valuesAsArray.length];
		System.arraycopy(valuesAsArray, 0, copy, 0, copy.length);
		return new SVStringArray(copy);
	}
	
	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}
		if(o instanceof SVStringArray){
			SVStringArray other = (SVStringArray) o;
			return Arrays.equals(this.valuesAsArray, other.valuesAsArray);
		}
		return false;
	}
	
	
}
