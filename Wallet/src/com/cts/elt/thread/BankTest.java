package com.cts.elt.thread;

public class BankTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bank b = new Bank(100, 10);
		for (int i = 0; i < 10; i++) {
			Transferable t = new Transferable(b);
			t.start();
		}

	}
}
