package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄亚雄
 * @since 2013年3月8日
 */
public class Test06 {

	// 【程序34】
	// 题目：输入3个数a,b,c，按大小顺序输出。
	// 1.程序分析：利用指针方法。
	public String paixu(int num1, int num2, int num3) {
		int temp = 0;
		int[] shu = new int[] { num1, num2, num3 };
		for (int i = 0; i < 2; i++)
			for (int j = i + 1; j < 3; j++) {
				if (shu[i] < shu[j]) {
					temp = shu[i];
					shu[i] = shu[j];
					shu[j] = shu[i];
				}
			}
		return shu[0] + ">" + shu[1] + ">" + shu[2] ;
	}

	// 【程序35】
	// 题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。
	/**
	 * @param arrays
	 *            一个整形的目标数组
	 * @return int[] 返回一个第一位为最大数最后一位位最小数的数组
	 */
	public static int[] shuchuArray(int[] arrays) {
		int max = 0;
		int min = 1;
		int[] newArray = new int[arrays.length];
		for (int i = 0; i < arrays.length; i++) {
			newArray[i] = arrays[i];// 生成一个和目标数组完全相同的数组
		}
		Arrays.sort(newArray);// 对新数组进行排序
		System.out.println(newArray[0] + " " + newArray[5]);
		for (int i = 0; i < arrays.length; i++) {// 遍历目标数组
			if (arrays[i] == 7) {// newArray[arrays.length-1]如果下标为i数据等于新数组最后一个则将下标为i的数据与下标为0的数据交换
				max = i;
			} else if (arrays[i] == 1) {// newArray[0]如果下标为i数据等于新数组最后一个则将下标为i的数据与下标为0的数据交换
				min = i;
			}
		}
		int temp = arrays[max];
		arrays[max] = arrays[0];
		arrays[0] = temp;

		temp = arrays[min];
		arrays[min] = arrays[arrays.length - 1];
		arrays[arrays.length - 1] = temp;
		System.out.println();
		for (int i = 0; i < newArray.length; i++) {
			System.out.println(arrays[i] + "  " + newArray[i]);
		}
		return arrays;
	}

	// 【程序36】
	// 题目：有n个整数，使其前面各数顺序向后移m个位置，最后m个数变成最前面的m个数
	/**
	 * @param array
	 *            一个目标数组其中他的length为n m为需要向后移动的位数
	 * @return int[] 返回的是重新排序的数组，数组里面带的数据符合要求
	 */
	public static int[] huanwei(int[] array, int m) {// 根据题目要求采用数组的1上数据结构
		int len = array.length;
		int newArray[] = new int[len];
		for (int i = 0; i < newArray.length; i++) {
			if (i < m) {// 最前面的m个数据
				newArray[i] = array[len - m + i];// newArray的前m数据位array的后m为数据
			} else {
				newArray[i] = array[i - m];// newArray的后面len-m的数据位array的钱len-m为数据
			}
		}
		return newArray;
	}

	// 【程序37】
	// 题目：有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），
	// 凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
	/**
	 * @param peopleNum
	 *            表示需要报数的总人数
	 * @return int 返回最后留下的那个人的编号
	 */
	public static int zhaoren(int peopleNum) {
		int res = 0;
		int flag = 1;
		List<Integer> lists = new ArrayList<Integer>();// 因为经常涉及到删除操作，所以才采用List数据结构
		for (int j = 1; j <= peopleNum; j++) {
			lists.add(j);// 給集合的每一個元素赋值
		}
		int temp = 1;// 每一圈第一个人报的数字，默认为1

		while (lists.size() >= 3) {// 当剩下的人数少于三个人是跳出循环
			System.out.println(lists.size());
			for (int i = 0; i < lists.size(); i++) {
				if (temp % 3 == 0) {
					System.out.println(temp + "temp");
					lists.remove(temp - 1);
					i--;// ***因为在删除一个元素以后lists的size相应的减了1所以在这里必须把i自减一次
				}
				temp++;// temp用来控制每一个人所报大的数
				if (temp > 3) {
					temp = temp % 3;
				}
			}
		}
		if (temp == 1) {
			lists.remove(0);
		} else {
			lists.remove(1);
		}
		int are = lists.get(0);
		return are;
	}

	// 【程序38】
	// 题目：写一个函数，求一个字符串的长度，在main函数中输入字符串，并输出其长度。
	/**
	 * @param strValue
	 *            表示一个String类型的数据
	 * @return int 返回一个int类型的数据表示这个String类型数据的长度
	 */
	public int getLengthByString(String strValue) {
		int length = 0;
		char[] strToChar = strValue.toCharArray();
		length = strToChar.length;
		return length;
	}

	// 【程序39】
	// 题目：编写一个函数，输入n为偶数时，
	// 调用函数求1/2+1/4+...+1/n,当输入n为奇数时，
	// 调用函数1/1+1/3+...+1/n(利用指针函数)
	/**
	 *@param number用户输入的一个整数
	 *@return int为所求和
	 */
	public int getSumByNumber(int number) {
		int sum = 0;
		boolean flag = (number % 2 == 0);// 判断输入数据是不是偶数
		int fenmu = 1; // 设置默认的初始分母为1
		if (flag) { // 当numbner为偶数时默认的初始分母为2
			fenmu = 2;
		}
		for (int i = fenmu; i <= number; i += 2) {
			sum += 1 / i;
		}
		return sum;
	}

	// 【程序40】
	// 题目：字符串排序。
	/**
	 * @param strValue一个需要排序的字符串
	 * @return 返回一个排序好的字符串
	 */
	public static String paixun(String strValue) {
		char[] stringArray = strValue.toCharArray();
		Arrays.sort(stringArray);
		strValue = String.copyValueOf(stringArray);
		return strValue;
	}

	public static void main(String[] args) {
		int[] a = { 3, 2, 4, 1, 5, 7 };
		// shuchuArray(a);
		// huanwei(a, 3);
		// System.out.println(zhaoren(4));
		System.out.println(paixun("124a2Q1."));
	}

}
