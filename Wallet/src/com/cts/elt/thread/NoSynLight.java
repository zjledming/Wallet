package com.cts.elt.thread;

public class NoSynLight {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int max = 50;
		final TurnOn on = new TurnOn();
		final TurnOff off = new TurnOff();
		Thread[] ongroup = new Thread[max];
		Thread[] offgroup = new Thread[max];
		for (int i = 0; i < max; i++) {
			ongroup[i] = new Thread(new Runnable() {
				public void run() {
					on.push();
				}
			});
		}
		for (int i = 0; i < max; i++) {
			offgroup[i] = new Thread(new Runnable() {
				public void run() {
					off.push();
				}
			});
		}
		for (int i = 0; i < max; i++) {
			ongroup[i].start();
			offgroup[i].start();
		}
	}

}
