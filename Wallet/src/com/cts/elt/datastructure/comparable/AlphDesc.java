package com.cts.elt.datastructure.comparable;

import java.util.Comparator;

public class AlphDesc implements Comparator<PersonBean> {

	public int compare(PersonBean personA, PersonBean personB) {
		int cop = personA.age - personB.age;
		if (cop != 0)
			return cop;
		else
			return personB.getName().compareTo(personA.getName());

	}

}
