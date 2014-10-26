package com.cts.elt.io;

import java.io.*;
import java.util.*;

public class SerializablePersonToFile {

	/**
	 * @param args
	 */
	private List<Person> initList() {
		List<Person> userList = new ArrayList<Person>();
		Person loginUser = new Person();
		loginUser.setName("sam");
		loginUser.setAge("30");
		loginUser.setCellPhoneNo("13333333333");
		loginUser.setPersonId("111111111111111111");
		userList.add(loginUser);
		loginUser = new Person();
		loginUser.setName("tonny");
		loginUser.setAge("31");
		loginUser.setCellPhoneNo("14333333333");
		loginUser.setPersonId("111111111111111111");
		userList.add(loginUser);
		loginUser = new Person();
		loginUser.setName("jim");
		loginUser.setAge("28");
		loginUser.setCellPhoneNo("15333333333");
		loginUser.setPersonId("111111111111111111");
		userList.add(loginUser);
		loginUser = new Person();
		loginUser.setName("Simon");
		loginUser.setAge("30");
		loginUser.setCellPhoneNo("17333333333");
		loginUser.setPersonId("111111111111111111");
		userList.add(loginUser);
		return userList;
	}

	private  void serializeFromFile() {
		FileInputStream fs = null;
		ObjectInputStream ois = null;
		try {
			fs = new FileInputStream("person.txt");
			ois = new ObjectInputStream(fs);
			List<Person> userList = (ArrayList<Person>) ois.readObject();
			for (Person p : userList) {
				System.out.println(p.getName() + "   " + p.getAge() + "   "
						+ p.getCellPhoneNo() + "   " + p.getCellPhoneNo());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (Exception e) {
			}
			try {
				if (fs != null) {
					fs.close();
				}
			} catch (Exception e) {
			}
		}
	}

	private void serializeToFile() {
		List<Person> userList = new ArrayList<Person>();
		userList = initList();
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		try {
			fs = new FileOutputStream("person.txt");
			os = new ObjectOutputStream(fs);
			os.writeObject(userList);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (Exception e) {
			}
			try {
				if (fs != null) {
					fs.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		SerializablePersonToFile sf = new SerializablePersonToFile();
		//sf.serializeToFile();
		sf.serializeFromFile();
	}

}
