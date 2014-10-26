package com.cts.elt.match;

public class ParenthMatch {
	public static boolean parse(String exp) {
		Stack stack = new Stack();

		for (int i = 0; i < exp.length(); i++) {
			switch (exp.charAt(i)) {
			case '(':
			case '[':
			case '<':
				stack.push(exp.charAt(i));

				break;
			case ')':
			case ']':
			case '>':
				try {
					
					if (stack.isEmpty())
						return false;
					if (!match(stack.pop().toString(), exp.charAt(i)))
						return false;
				} catch (Exception e1) {
				}
				break;
			}
		}
		if (!stack.isEmpty()){
			System.out.println("stack !isEmpty()");
			return false;
		}
		return true;
	}

	public static boolean match(String ch1, char ch2) {
		System.out.println("compare "+ch1+"     and    "+ch2);
		switch (ch1.charAt(0)) {
		case '(':
			if (ch2 == ')')
				return true;
			break;
		case '[':
			if (ch2 == ']')
				return true;
			break;
		case '<':
			if (ch2 == '>')
				return true;
			break;
		}
		return false;
	}
}
