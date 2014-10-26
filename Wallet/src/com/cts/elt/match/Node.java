package com.cts.elt.match;

public class Node implements Position {
	private Object element;
	private Node next;

	public Node(Object element, Node next) {
		super();
		this.element = element;
		this.next = next;
	}

	public Node() {
		this(null, null);
	}

	@Override
	public Object getElem() {
		// TODO Auto-generated method stub
		return this.element;
	}

	@Override
	public Object setElem(Object obj) {
		// TODO Auto-generated method stub
		Object oldElem = this.element;
		this.element = obj;
		return oldElem;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getNext() {
		return next;
	}
}
