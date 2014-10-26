package com.cts.elt.datastructure.duplicate;

import java.util.*;

public class DuplicateValue {

	public void removeDuplicateValue() {
		List myList = new ArrayList();
		myList.add(1);
		myList.add(2);
		myList.add(1);
		myList.add(3);
		myList.add(4);
		myList.add(5);
		myList.add(6);
		myList.add(5);

		Set myset = new HashSet(myList);
		myList = new ArrayList(myset);
		Iterator it = myList.iterator();
		while (it.hasNext()) {
			System.out.println(""+it.next());
		}
	}

	public static void main(String[] args) {
		DuplicateValue dv = new DuplicateValue();
		dv.removeDuplicateValue();

	}

}
