package com.cts.elt.annotation;

import java.lang.reflect.*;

public class PersistStudent {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
		Object c = Class.forName("com.cts.elt.annotation.Student")
				.newInstance();
		try {
			Method[] methodArray = c.getClass().getDeclaredMethods();
			for (int i = 0; i < methodArray.length; i++) {
				if (methodArray[i].isAnnotationPresent(ValueBind.class)) {
					ValueBind annotation = methodArray[i]
							.getAnnotation(ValueBind.class);
					String type = String.valueOf(annotation.type());
					String value = annotation.value();
					if (type.equals("INT")) {
						methodArray[i].invoke(c, new Integer[] { new Integer(
								value) });
					} else {
						methodArray[i].invoke(c, new String[] { value });
					}
				}
			}
			Student annotaedStudent = (Student) c;
			System.out.println("studentId====" + annotaedStudent.getStudentId()
					+ "  studentnName====" + annotaedStudent.getName()
					+ "   student Age====" + annotaedStudent.getAge());

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
