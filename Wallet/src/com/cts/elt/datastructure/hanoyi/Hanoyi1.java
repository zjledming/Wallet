package com.cts.elt.datastructure.hanoyi;

public class Hanoyi1 {

	/**
	 * @param args
	 */
	public void Move(char chSour, char chDest) {
		System.out.println("Move the top plate of " + chSour + "-->" + chDest);
	}

	public void Hanoi(int n, char a, char b, char c) {
		if (n == 1)
			Move(a, c);
		else {
			Hanoi(n - 1, a, c, b);
			this.Move(a, c);
			Hanoi(n - 1, b, a, c);
		}
	}

	public static void main(String[] args) {
		int n = 3;
		Hanoyi1 han1 = new Hanoyi1();
		han1.Hanoi(n, 'A', 'C', 'B');

	}
}
