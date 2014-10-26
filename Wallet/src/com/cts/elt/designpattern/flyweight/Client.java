package com.cts.elt.designpattern.flyweight;

import java.util.*;

public class Client {
	private static FlyweightFactory factory;

	public static void main(String[] args)

	{
		List list1 = new ArrayList();
		factory = FlyweightFactory.getInstance();
		
		Menu list = factory.factory("�⽷����˿");
		list.setPersonMenu("ai92", list1);

		list = factory.factory("������");
		list.setPersonMenu("ai92", list1);

		list = factory.factory("������");
		list.setPersonMenu("ai92", list1);

		list = factory.factory("������");
		list.setPersonMenu("ai92", list1);

		list = factory.factory("��������");
		list.setPersonMenu("ai92", list1);

		list = factory.factory("������");
		list.setPersonMenu("ai921", list1);

		list = factory.factory("��������");
		list.setPersonMenu("ai921", list1);

		list = factory.factory("������");
		list.setPersonMenu("ai921", list1);

		System.out.println(factory.getNumber());
		List list2 = list.findPersonMenu("ai921", list1);
		Iterator it = list2.iterator();
		while (it.hasNext()) {
			System.out.println(" " + it.next());
		}
	}
}
