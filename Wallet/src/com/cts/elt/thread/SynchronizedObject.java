package com.cts.elt.thread;

import java.util.Random;

public class SynchronizedObject {

	/**
	 * @param args
	 */
	private int[] a = new int[] { 0, 1, 2, 3, 4 };
	private byte[] lock = new byte[0];

	public void method1() {

		for (int i = 0; i < 5; i++) {
			synchronized (lock) {
				Random rnd = new Random();
				int getA = a[rnd.nextInt(a.length)];
				System.out.println(Thread.currentThread().getName() + "   "
						+ getA);
			}
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}

	}


	public static void main(String[] args) {
		final SynchronizedObject so = new SynchronizedObject();
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				so.method1();
			}
		});
		Thread t2 = new Thread(new Runnable() {

			public void run() {
				so.method1();
			}
		});
		t1.start();
		t2.start();
	}
}
