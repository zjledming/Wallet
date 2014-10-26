package com.cts.elt.thread;

public class TestHusbandAndWife {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HusbandAndWife hw = new HusbandAndWife();
		MoneyJob money1 = new MoneyJob(hw, "husband");
		MoneyJob money2 = new MoneyJob(hw, "wife");
		money1.start();
		money2.start();


	}

}
