package com.cts.elt.datastructure.comparable;

import java.util.*;

public class AdvancedComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {

		String[] strF = ((String) arg0).split("_");
		String[] strL = ((String) arg1).split("_");
		int len = strF.length;
		if (len == 1) {
			return Integer.parseInt(strF[0]) - Integer.parseInt(strL[0]);
		}
		for (int i = 0; i < len; i++) {
			if (!strF[i].equals(strL[i])) {
				return Integer.parseInt(strF[i]) - Integer.parseInt(strL[i]);
			} else {
				String strNF = getSubValue(strF, i);
				String strNL = getSubValue(strL, i);
				return compare(strNF, strNL);
			}
		}
		return 0;
	}

	public String getSubValue(String[] strObj, int nPast) {
		String strReturn = "";
		for (int i = 1 + nPast; i < strObj.length; i++) {
			strReturn = strReturn + "_" + strObj[i];
		}
		return strReturn.substring(1);
	}

	public static void main(String[] args) {
		String[] st = { "2_2_107_11", "4_1_3_8", "1_1_103_3", "1_2_103_2",
				"1_1_105_1", "2_1_107_5", "2_2_107_6", "3_1_107_4",
				"2_1_107_10" };
		AdvancedComparator tc = new AdvancedComparator();
		Arrays.sort(st, tc);
		for (int i = 0; i < st.length; i++) {
			System.out.println(st[i]);
		}
	}
}
