package com.cts.elt.thread;

import java.util.Random;

public class StaticSynLight {

	public static synchronized void turnOn() {
		for (int i = 0; i < 10; i++) {
			System.out.println("staticx");

		}
	}

	public synchronized void turnOff() {
		for (int i = 0; i < 10; i++) {
			System.out.println("x");
		}

	}

	public static void main(String[] args) {
		final StaticSynLight st1 = new StaticSynLight();
		final StaticSynLight st2 = new StaticSynLight();
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				st1.turnOn();
			}
		});
		Thread t2 = new Thread(new Runnable() {

			public void run() {
				st2.turnOff();
			}
		});
		t1.start();
		t2.start();

	}

}
