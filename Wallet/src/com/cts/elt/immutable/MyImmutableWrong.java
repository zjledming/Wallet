package com.cts.elt.immutable;

public final class MyImmutableWrong {
	private final int[] myArray;

	public MyImmutableWrong(int[] anArray) {
		this.myArray = anArray; // wrong
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Numbers are: ");
		for (int i = 0; i < myArray.length; i++) {
			sb.append(myArray[i] + " ");
		}
		return sb.toString();
	}
}
