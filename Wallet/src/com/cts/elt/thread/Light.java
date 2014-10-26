package com.cts.elt.thread;

public class Light {
	boolean lightswitch = false;

	public void turnOn() {
		this.lightswitch = true;
	}

	public void turnOff() {
		this.lightswitch = false;
	}

	public void display() {
		if (lightswitch) {
			System.out.print("lighton");
		} else {
			System.out.print("lightoff");
		}
	}

	public synchronized void push() {
		turnOn();
		System.out.print("turn on  ");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
		turnOff();
		System.out.print("turn off  ");
		System.out.println("");
	}
}
