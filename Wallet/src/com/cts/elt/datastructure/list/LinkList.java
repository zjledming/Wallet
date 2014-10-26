package com.cts.elt.datastructure.list;

public class LinkList {
	/* 用变量来实现表头 */
	private Node Head = null;

	private Node Tail = null;

	private Node Pointer = null; // point to previous

	private int Length = 0;

	public void deleteAll() {// 清空整个链表
		Head = null;
		Tail = null;
		Pointer = null;
		Length = 0;
	}

	// 链表复位，使第一个节点成为当前节点
	public void reset() {
		Pointer = null;
	}

	// 判断链表是否为空
	public boolean isEmpty() {
		return (Length == 0);
	}

	// 判断当前结点是否为最后一个结点
	public boolean isEnd() {
		if (Length == 0)
			throw new java.lang.NullPointerException();
		else if (Length == 1)
			return true;
		else
			return (cursor() == Tail);
	}

	// 返回当前结点的下一个结点的值，并使其成为当前结点
	public Object nextNode() {
		if (Length == 1)
			throw new java.util.NoSuchElementException();
		else if (Length == 0)
			throw new java.lang.NullPointerException();
		else {
			Node temp = cursor();
			Pointer = temp;
			if (temp != Tail)
				return (temp.next.data);
			else
				throw new java.util.NoSuchElementException();
		}
	}

	// 返回当前结点的值
	public Object currentNode() {
		Node temp = cursor();
		return temp.data;
	}

	// 在当前结点前插入一个结点，并使其成为当前结点
	public void insert(Object obj) {
		Node e = new Node(obj);
		if (Length == 0) {
			Tail = e;
			Head = e;
		} else {
			Node temp = cursor();
			e.next = temp;
			if (Pointer == null)
				Head = e;
			else
				Pointer.next = e;
		}
		Length++;
	}

	// 返回链表的大小
	public int size() {
		return Length;
	}

	// 将当前结点移出链表，下一个结点成为当前结点，
	// 如果移出的结点是最后一个结点，
	// 则第一个结点成为当前结点
	public Object remove() {
		Object temp;
		if (Length == 0)
			throw new java.util.NoSuchElementException();
		else if (Length == 1) {
			temp = Head.data;
			deleteAll();
		} else {
			Node cur = cursor();
			temp = cur.data;
			if (cur == Head)
				Head = cur.next;
			else if (cur == Tail) {
				Pointer.next = null;
				reset();
			} else
				Pointer.next = cur.next;
			Length--;
		}
		return temp;
	}

	// 返回当前结点的指针
	private Node cursor() {
		if (Head == null)
			throw new java.lang.NullPointerException();
		else if (Pointer == null)
			return Head;
		else
			return Pointer.next;
	}

	// 链表的简单应用举例
	public static void main(String[] args) {
		LinkList a = new LinkList();
		for (int i = 1; i <= 10; i++)
			a.insert(new Integer(i));
		Node mynode = a.Head;
		while (mynode.next != null) {
			System.out.println(mynode.data);
			mynode = mynode.next;
		}
		System.out.println("The currentNode is:" + a.currentNode());
		while (!a.isEnd())
			System.out.println("The nextNode is:" + a.nextNode());
		a.reset();
		while (!a.isEnd()) {
			a.remove();
		}
		a.remove();
		a.reset();
		if (a.isEmpty()) {
			System.out.println("There is no Node in List");
		}
	}
}
