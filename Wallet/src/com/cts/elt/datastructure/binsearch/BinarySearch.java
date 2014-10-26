package com.cts.elt.datastructure.binsearch;

import java.text.SimpleDateFormat;
import java.util.*;

public class BinarySearch {
	/**
	 * @param args
	 */

	class SumPossibleBean {
		private int first = 0;
		private int second = 0;

		private void setFirst(int first) {
			this.first = first;
		}

		private void setSecond(int second) {
			this.second = second;
		}

		private int getFirst() {
			return this.first;
		}

		private int getSecond() {
			return this.second;
		}
	}

	public Vector<SumPossibleBean> checkSumPossibleBinSearch(
			ArrayList<Integer> intList, int sumResult) {
		Vector<SumPossibleBean> possibleList = new Vector<SumPossibleBean>();
		int size = intList.size();
		Collections.sort(intList);
		for (int i = 0, j = (intList.size() - 1); i < j;) {
			Integer s = intList.get(i);
			Integer e = intList.get(j);
			if ((s + e) == sumResult) {
				SumPossibleBean spBean = new SumPossibleBean();
				spBean.setFirst(intList.get(i).intValue());
				spBean.setSecond(intList.get(j).intValue());
				possibleList.addElement(spBean);
				i++;
				j--;
			} else if ((s + e) > sumResult) {
				j--;
			} else if ((s + e) < sumResult) {
				i++;
			}
		}
		return possibleList;
	}

	public Vector<SumPossibleBean> checkSumPossible(ArrayList<Integer> intList,
			int sumResult) {
		Vector<SumPossibleBean> possibleList = new Vector<SumPossibleBean>();
		int size = intList.size();
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if ((intList.get(i).intValue() + intList.get(j).intValue()) == sumResult) {

					SumPossibleBean spBean = new SumPossibleBean();
					spBean.setFirst(intList.get(i).intValue());
					spBean.setSecond(intList.get(j).intValue());
					possibleList.addElement(spBean);
				}
			}
		}
		return possibleList;
	}

	public void display(Vector<SumPossibleBean> spList) {
		for (SumPossibleBean spBean : spList) {
			System.out.println("first: " + spBean.getFirst()
					+ "    and second: " + spBean.getSecond());
		}
	}

	public static void main(String[] args) {

		BinarySearch bsearch = new BinarySearch();

		ArrayList<Integer> intList = new ArrayList<Integer>();
		intList.add(new Integer(1));
		intList.add(new Integer(8));
		intList.add(new Integer(3));
		intList.add(new Integer(5));
		intList.add(new Integer(9));
		intList.add(new Integer(2));
		intList.add(new Integer(3));
		intList.add(new Integer(5));
		intList.add(new Integer(9));
		intList.add(new Integer(2));
		intList.add(new Integer(3));
		intList.add(new Integer(5));
		intList.add(new Integer(9));
		intList.add(new Integer(2));
		intList.add(new Integer(3));
		intList.add(new Integer(5));
		intList.add(new Integer(9));
		intList.add(new Integer(2));
		intList.add(new Integer(3));
		intList.add(new Integer(5));
		intList.add(new Integer(9));
		intList.add(new Integer(2));
		intList.add(new Integer(3));
		intList.add(new Integer(5));
		intList.add(new Integer(9));
		intList.add(new Integer(2));
		intList.add(new Integer(3));
		intList.add(new Integer(5));
		intList.add(new Integer(9));
		intList.add(new Integer(2));
		intList.add(new Integer(3));
		intList.add(new Integer(5));
		intList.add(new Integer(9));
		intList.add(new Integer(2));
		long sTime = System.currentTimeMillis();
		bsearch.display(bsearch.checkSumPossible(intList, 10));
		long eTime = System.currentTimeMillis();
		System.out.println("Total spend: " + (eTime - sTime));
		sTime = System.currentTimeMillis();
		bsearch.display(bsearch.checkSumPossibleBinSearch(intList, 10));
		eTime = System.currentTimeMillis();
		System.out.println("Total spend: " + (eTime - sTime));

	}
}
