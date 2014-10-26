package com.cts.elt.designpattern.flyweight;

import java.util.*;

public class PersonMenu implements Menu {

	private String dish;

	public PersonMenu(String dish) {
		this.dish = dish;
	}

	public synchronized void setPersonMenu(String person, List list) {
		list.add(person);
		list.add(dish);
	}

	public List findPersonMenu(String person, List list) {
		List dishList = new ArrayList();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			if (person.equals((String) it.next()))

				dishList.add(it.next());
		}
		return dishList;
	}

}