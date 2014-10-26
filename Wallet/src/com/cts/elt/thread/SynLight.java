package com.cts.elt.thread;

public class SynLight {

	public static void main(String[] args) {
		final int max = 5;
		final Light light = new Light();
		Thread[] on = new Thread[max];
		for (int i = 0; i < max; i++) {
			on[i] = new Thread(new Runnable() {
				public void run() {
					light.push();
				}
			});
		}
		for (int i = 0; i < max; i++) {
			on[i].start();
		}
	}

}
