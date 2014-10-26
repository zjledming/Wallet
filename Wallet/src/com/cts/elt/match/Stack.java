package com.cts.elt.match;

public class Stack {
	private Node top;
	private int size;

	public Stack() {
		super();
		this.top = new Node();
		this.size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Object getTop() throws Exception {
		if (isEmpty())
			throw new Exception("����ջΪ�գ�");
		return top.getNext().getElem();
	}

	public Object pop() throws Exception {
		System.out.println("һ��Ԫ�س�ջ");
		if (isEmpty())
			throw new Exception("����ջΪ�գ�");
		Node first = top.getNext();
		Node second = first.getNext();
		top.setNext(second);
		size--;
		return first.getElem();
	}

	public void push(Object obj) {
		System.out.println("һ��Ԫ�ؽ�ջ");
		Node first = top.getNext();
		Node newNode = new Node(obj, first);
		top.setNext(newNode);
		size++;
	}

	public void clear() {
		top.setNext(null);
		size = 0;
	}

	public void showMe() {
		Node node = top.getNext();
		while (node.getNext() != null) {
			System.out.print(node.getElem() + "->");
			node = node.getNext();
		}
		System.out.println(node.getElem());
	}
}
