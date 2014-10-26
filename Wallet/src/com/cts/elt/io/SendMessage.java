package com.cts.elt.io;

import java.io.*;

public class SendMessage implements Runnable {
	private PipedOutputStream out = null;

	public PipedOutputStream getOut() {
		return this.out;
	}

	public SendMessage() {
		this.out = new PipedOutputStream();
	}

	public void send() {

		String msg = "start";
		try {
			out.write(msg.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
			}
		}
	}

	public void run() {
		try {
			System.out.println("waiting for signal...");
			Thread.sleep(2000);
			send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
