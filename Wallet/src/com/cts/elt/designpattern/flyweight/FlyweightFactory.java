package com.cts.elt.designpattern.flyweight;

import java.util.*;

public class FlyweightFactory {
	private Map menuList = new HashMap();

	private static FlyweightFactory factory = new FlyweightFactory();

	private FlyweightFactory() {
	}

	public static FlyweightFactory getInstance() {
		return factory;
	}

	public synchronized Menu factory(String dish) {
		if (menuList.containsKey(dish)) {
			return (Menu) menuList.get(dish);
		} else {
			Menu menu = new PersonMenu(dish);
			menuList.put(dish, menu);
			return menu;
		}
	}

	public int getNumber() {
		return menuList.size();
	}
}
