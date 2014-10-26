package com.cts.elt.thread;

public class TurnOff {
	public synchronized static void push() {
		System.out.println("turn off");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}
}
