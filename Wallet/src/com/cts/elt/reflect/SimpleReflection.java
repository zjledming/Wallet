package com.cts.elt.reflect;

import java.util.*;
import java.lang.reflect.*;

public class SimpleReflection {
	private static Object c1 = null;

	public static Object reflectMyMethod(String className, String methodName) {
		Class[] types = new Class[] { String.class };
		String value = "reflected value";
		try {
			c1 = Class.forName(className).newInstance();
			Method method = c1.getClass().getMethod(methodName, types);
			method.invoke(c1, new Object[] { value });

		} catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
	}
}
