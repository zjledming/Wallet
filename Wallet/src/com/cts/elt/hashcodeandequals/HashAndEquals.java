package com.cts.elt.hashcodeandequals;

public class HashAndEquals {

	/**
	 * @param args
	 */
	public void compareTwoSameObjCorrect() {
		Student std1 = new Student(28, "Tonny");
		Student std2 = new Student(28, "Tonny");
		
		Person person1 = new Person(28, "Tonny",std1);
		Person person2 = new Person(28, "Tonny",std2);
		if (person1.equals(person2)) {
			System.out.println("same person");
			System.out.println(person1.hashCode()==person2.hashCode()?"t":"f");
		} else {
			System.out.println("not the same person");
		}
	}

	public void compareTwoSameObjWrong() {
		Student std1 = new Student(28, "Tonny");
		Student std2 = new Student(28, "Tonny");
		if (std1.equals(std2)) {
			System.out.println("same student");
		} else {
			System.out.println("not the same student");
		}
	}

	public static void main(String[] args) {
		HashAndEquals he = new HashAndEquals();
		he.compareTwoSameObjCorrect();
		he.compareTwoSameObjWrong();

	}

}
