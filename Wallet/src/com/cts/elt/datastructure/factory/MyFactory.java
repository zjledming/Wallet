package com.cts.elt.datastructure.factory;

public class MyFactory {

	public int getChar(String str) {

		String[] strF = str.split("_");
		int len = strF.length;
		if (len == 1) {
			System.out.println("the last one is " + strF[0]);
			return 0;
		} else {
			System.out.print("take  " + strF[0]);
			for (int i = 0; i < len; i++) {
				String strNF = getSubValue(strF, i);
				System.out.print("   reamain " + strNF + "\n");
				if (strNF.length() < 1) {
					return 0;
				}
				return getChar(strNF);

			}
		}
		return 0;
	}

	private String getSubValue(String[] strObj, int nPast) {
		String strReturn = "";
		for (int i = 1 + nPast; i < strObj.length; i++) {
			strReturn = strReturn + "_" + strObj[i];
		}
		return strReturn.substring(1);

	}

	public static void main(String[] args) {
		String str = "2_3_4_107";
		MyFactory myFactory = new MyFactory();
		myFactory.getChar(str);
	}
}
