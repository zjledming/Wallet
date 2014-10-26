package com.cts.elt.clone;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TestDeepClone {

	/**
	 * @param args
	 */
	public List copyList(List list) {
		List mylist = list;
		return mylist;
	}

	public List deepcopy(List src) throws IOException, ClassNotFoundException {

		ByteArrayOutputStream byteout = new ByteArrayOutputStream();

		ObjectOutputStream out = new ObjectOutputStream(byteout);

		out.writeObject(src);

		ByteArrayInputStream bytein = new ByteArrayInputStream(byteout
				.toByteArray());

		ObjectInputStream in = new ObjectInputStream(bytein);

		List dest = (List) in.readObject();

		return dest;

	}

	public static void main(String[] args) {
		
		
		TestDeepClone tdcClient = new TestDeepClone();
		DeepClone dc = new DeepClone();
		List arrayList = new ArrayList();
		dc.setName("ymk");
		dc.setAge(33);
		arrayList.add(dc);
		dc = new DeepClone();
		dc.setName("sj");
		dc.setAge(35);
		arrayList.add(dc);

		 List copiedList = tdcClient.copyList(arrayList);
		
		//List copiedList = null;
		try {
			//copiedList = tdcClient.deepcopy(arrayList);
		} catch (Exception e) {
			e.printStackTrace();
			copiedList = null;
		}
		
		if (copiedList != null) {
			for (int i = 0; i < copiedList.size(); i++) {
				if (i == 1) {
					DeepClone mydc = (DeepClone) copiedList.get(i);
					System.out.println("before change the name====="
							+ mydc.getName());
				}
			}
			for (int i = 0; i < arrayList.size(); i++) {
				if (i == 1) {
					DeepClone mydc = (DeepClone) arrayList.get(i);
					mydc.setName("simon");
				}
			}
			for (int i = 0; i < copiedList.size(); i++) {
				if (i == 1) {
					DeepClone mydc = (DeepClone) copiedList.get(i);
					System.out.println("after change the name====="
							+ mydc.getName());
				}
			}
		}

	}
}
