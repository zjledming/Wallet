package com.cts.elt.thread;

public class HusbandAndWife {

	private static int balance=2000;

	public  synchronized void saveMoney() {
		balance+=100;
		System.out.println("husband put 100$ into box");
	}

	public  synchronized void takeMoney() {
		if(balance<100){
			return;
		}
		balance-=100;
		System.out.println("wife take 100$ from box");
	}
	public  void displayBalance(){
		System.out.println("current balance====="+this.balance);
	}
}
