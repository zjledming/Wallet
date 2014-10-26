package com.cts.elt.jvm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

public class PhantomObject {
	public static boolean isRun = true;

	public static void main(String[] args) throws Exception {
		final File file = new File("d:" + File.separator + "weakobject.txt");
		PrintStream ps = null;
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(file);
			ps = new PrintStream(fs);
			System.setOut(ps);
			String abc = new String("abc");
			System.out.println(abc.getClass() + "@" + abc.hashCode());
			final ReferenceQueue referenceQueue = new ReferenceQueue<String>();
			new Thread() {
				public void run() {

					while (isRun) {
						Object o = referenceQueue.poll();
						if (o != null) {
							try {

								Field rereferent = Reference.class
										.getDeclaredField("referent");
								rereferent.setAccessible(true);
								Object result = rereferent.get(o);
								System.out.println("gc will collect:"
										+ result.getClass() + "@"
										+ result.hashCode());
							} catch (Exception e) {

								e.printStackTrace();
							}
						}else{
							System.out.println("gc can not found any phantom object");
						}
					}
				}
			}.start();
			PhantomReference<String> abcWeakRef = new PhantomReference<String>(
					abc, referenceQueue);
			abc = null;
			Thread.currentThread().sleep(1000);
			System.gc();
			Thread.currentThread().sleep(1000);
			isRun = false;
		} catch (Exception e) {

		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
			} catch (Exception e) {
			}
			try {
				if (fs != null) {
					fs.close();
					fs = null;
				}
			} catch (Exception e) {
			}
		}
	}
}
