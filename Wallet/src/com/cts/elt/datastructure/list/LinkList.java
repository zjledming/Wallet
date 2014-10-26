package com.cts.elt.datastructure.list;

public class LinkList {
	/* �ñ�����ʵ�ֱ�ͷ */
	private Node Head = null;

	private Node Tail = null;

	private Node Pointer = null; // point to previous

	private int Length = 0;

	public void deleteAll() {// �����������
		Head = null;
		Tail = null;
		Pointer = null;
		Length = 0;
	}

	// ����λ��ʹ��һ���ڵ��Ϊ��ǰ�ڵ�
	public void reset() {
		Pointer = null;
	}

	// �ж������Ƿ�Ϊ��
	public boolean isEmpty() {
		return (Length == 0);
	}

	// �жϵ�ǰ����Ƿ�Ϊ���һ�����
	public boolean isEnd() {
		if (Length == 0)
			throw new java.lang.NullPointerException();
		else if (Length == 1)
			return true;
		else
			return (cursor() == Tail);
	}

	// ���ص�ǰ������һ������ֵ����ʹ���Ϊ��ǰ���
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

	// ���ص�ǰ����ֵ
	public Object currentNode() {
		Node temp = cursor();
		return temp.data;
	}

	// �ڵ�ǰ���ǰ����һ����㣬��ʹ���Ϊ��ǰ���
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

	// ��������Ĵ�С
	public int size() {
		return Length;
	}

	// ����ǰ����Ƴ�������һ������Ϊ��ǰ��㣬
	// ����Ƴ��Ľ�������һ����㣬
	// ���һ������Ϊ��ǰ���
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

	// ���ص�ǰ����ָ��
	private Node cursor() {
		if (Head == null)
			throw new java.lang.NullPointerException();
		else if (Pointer == null)
			return Head;
		else
			return Pointer.next;
	}

	// ����ļ�Ӧ�þ���
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
