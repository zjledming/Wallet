package com.cts.elt.thread;

import java.util.Random;

public class SynchronizedStatic {

	private static int[] a = new int[] { 0, 1, 2, 3, 4 };
	static byte[] lock = new byte[0];

	public static void method1() {
		synchronized (lock) {
			for (int i = 0; i < 5; i++) {
				Random rnd = new Random();
				int getA = a[rnd.nextInt(a.length)];
				System.out.println(Thread.currentThread().getName() + "   "
						+ getA);
			}
		}
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}

	}

	public static void main(String[] args) {
		final SynchronizedStatic st = new SynchronizedStatic();
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				st.method1();
			}
		});
		Thread t2 = new Thread(new Runnable() {

			public void run() {
				st.method1();
			}
		});
		t1.start();
		t2.start();
	}
}
