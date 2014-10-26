package com.cts.elt.datastructure.factory;

public class SimpleFactory {

	private int factory(int num, int result) {
		int val = 0;
		val = num;
		if (val <= 0) {
			return result;
		}
		result = result * num;
		return factory(num - 1, result);
	}

	public static void main(String[] args) {
		SimpleFactory sf = new SimpleFactory();
		int result = sf.factory(5, 1);
		System.out.println(result);
	}
}
