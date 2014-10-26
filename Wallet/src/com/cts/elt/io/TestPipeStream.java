package com.cts.elt.io;

public class TestPipeStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SendMessage send = new SendMessage();
		ReceiveMessage receive = new ReceiveMessage();
		try {
			send.getOut().connect(receive.getInput());
			Thread t1 = new Thread(send);
			Thread t2 = new Thread(receive);
			t1.start();
			t2.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
