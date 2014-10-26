package com.cts.elt.datastructure.hanoyi;

public class Hanoyi2 {
	/**
	 * @param args
	 */
	public void Move(char chSour, char chDest) {
		System.out.println("Move the top plate of " + chSour + "-->" + chDest);
	}

	public void Hanoi(int n, char chA, char chB, char chC) {
		if (n == 1)
			Move(chA, chB);
		else {
			Hanoi(n - 1, chA, chC, chB);
			this.Move(chA, chB);
			Hanoi(n - 1, chC, chB, chA);
		}
	}

	public static void main(String[] args) {
		int n = 3;
		Hanoyi2 han2 = new Hanoyi2();
		han2.Hanoi(n, 'A', 'B', 'C');

	}
}
