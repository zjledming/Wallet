package com.cts.elt.datastructure.missednumber;

public class MissedNumber {

	public int findMissedOne(int[] numArray) {
		int sum = 0;
		int idx = -1;
		for (int i = 0; i < numArray.length; i++) {
			if (numArray[i] == 0) {
				idx = i;
			} else {
				sum += numArray[i];
			}
		}

		// the total sum of numbers between 1 and arr.length.
		int total = (numArray.length + 1) * numArray.length / 2;
		int missedNumber = total - sum;
		return missedNumber;

	}
}
