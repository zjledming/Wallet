package com.cts.elt.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldReflection {
	private static Object c1 = null;

	public static Object reflectMyField(String className, String fieldName) {
		String value = "reflected value";
		try {
			c1 = Class.forName(className).newInstance();
			Field f = c1.getClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			f.set(c1, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
	}
}
