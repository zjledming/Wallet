package com.cts.elt.thread;

public class Transferable extends Thread {

	private Bank b = null;

	public Transferable(Bank b) {
		this.b = b;
	}

	public void run() {
		try {
			while (true) {
				this.b.transfer();
				this.b.showBankAmount(this.getName());
				sleep(5);
			}
		} catch (Exception e) {

		}
	}
}
