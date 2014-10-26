package com.cts.elt.thread;

import java.util.Random;

public class Bank {

	private int[] account = null;
	private int from;
	private int to;

	public Bank(int balance, int accountNumber) {
		account = new int[accountNumber];
		for (int i = 0; i < accountNumber; i++) {
			account[i] = balance;
		}
	}

	private void draw() {
		account[from] -= 100;
	}

	private void deposit() {

		account[to] += 100;
	}

	public void showBankAmount(String transactionName) {
		double amount = 0;
		for (int i = 0; i < this.account.length; i++) {
			amount += account[i];
		}
		System.out.println(transactionName + "    current bank amount======"
				+ amount);
	}

	public  synchronized void transfer() {
		Random fromrd = new Random();
		Random tord = new Random();
		from = fromrd.nextInt(account.length);
		to = tord.nextInt(account.length);
		if (this.account[from] < 100) {
			return;
		}
		draw();
		deposit();

	}
}
