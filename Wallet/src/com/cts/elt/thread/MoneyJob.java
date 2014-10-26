package com.cts.elt.thread;

public class MoneyJob extends Thread {

	private HusbandAndWife hw = null;
	private String coupleType = "";

	public MoneyJob(HusbandAndWife hw, String coupleType) {
		this.hw = hw;
		this.coupleType = coupleType;
	}

	public void run() {

		if (coupleType.equals("husband")) {
			for (int i = 0; i <10; i++) {
				this.hw.saveMoney();
				try {
					sleep(5);
				} catch (Exception e) {
				}
			}
		} else if (coupleType.equals("wife")) {
			for (int i = 0; i < 10; i++) {
				this.hw.takeMoney();
				try {
					sleep(5);
				} catch (Exception e) {
				}
			}
		}
		this.hw.displayBalance();
	}
}
