package com.cts.elt.datastructure.missednumber;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMissedNumber {

	protected MissedNumber mNum = new MissedNumber();

	@Test
	public void testFindMissedOne() {
		int[] testArray = new int[] { 0, 1, 2, 4 };
		int missedNumber = mNum.findMissedOne(testArray);
		System.out.println(missedNumber);
	}

}
