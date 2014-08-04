package cn.md5.util;

import java.io.PrintStream;

public class Test {
	public static void main(String[] args) {
		System.out.println(CryptUtil.encrypt("fewewÎÒ¿¿"));
		System.out.println(CryptUtil
				.decrypt("595f39958089a4fbd69830d2d99b1289"));
		
	}
}