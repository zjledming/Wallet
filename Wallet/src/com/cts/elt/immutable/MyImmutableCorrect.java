package com.cts.elt.immutable;

public final class MyImmutableCorrect {
	private final int[] myArray;

	public MyImmutableCorrect(int[] anArray) {
		this.myArray = anArray.clone(); 
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Numbers are: ");
		for (int i = 0; i < myArray.length; i++) {
			sb.append(myArray[i] + " ");
		}
		return sb.toString();
	}
}
