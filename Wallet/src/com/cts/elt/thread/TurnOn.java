package com.cts.elt.thread;

public class TurnOn {

	public synchronized static void push() {
		System.out.println("turn on");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}
}
