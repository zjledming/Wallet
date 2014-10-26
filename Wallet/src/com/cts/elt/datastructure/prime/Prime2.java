package com.cts.elt.datastructure.prime;

public class Prime2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int n = 20;

		int[] array = new int[n];

		for (int i = 2; i < n; i++) {

			array[i] = i;

		}

		for (int i = 2; i < n; i++) {
			if (array[i] != 0) {

				int j, temp;

				temp = array[i];

				for (j = 2 * temp; j < n; j = j + temp) {
					System.out.print("   j===" + j);
					array[j] = 0;
				}
				System.out.println("\n");

			}

		}

		for (int i = 0; i < n; i++) {

			if (array[i] != 0) {

				System.out.print(i + " ");

			}

		}

	}

}
