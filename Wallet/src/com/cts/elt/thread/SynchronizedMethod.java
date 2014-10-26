package com.cts.elt.thread;

import java.util.Random;

public class SynchronizedMethod {

	private int[] a = new int[] { 0, 1, 2, 3, 4 };

	public  synchronized void method1() {

		for (int i = 0; i < 10; i++) {
			Random rnd = new Random();
			int getA = a[rnd.nextInt(a.length)];
			System.out.println(Thread.currentThread().getName() + "   " + getA);
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}

	}


	public static void main(String[] args) {
		final SynchronizedMethod sm = new SynchronizedMethod();
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				sm.method1();
			}
		});
		Thread t2 = new Thread(new Runnable() {

			public void run() {
				sm.method1();
			}
		});
		t1.start();
		t2.start();
	}

}
