package mst;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Test08 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getGrade();
	}

	// 【程序46】
	// 题目：两个字符串连接程序

	public static String getString(String str1, String str2) {
		return str1 + str2;
	}

	// 【程序47】
	// 题目：读取7个数（1—50）的整数值，每读取一个值，程序打印出该值个数的＊。
	
	public static void printxing(){
		Scanner sca = new Scanner(System.in);
		int time = 1;
		int number = 0;
		while(time <= 7){
			System.out.println("请输入一个1-50的整数");
			number = sca.nextInt();
			if(number>50||number<1){
				System.out.println("输入不合法，请重新输入");
				continue;
			}
			for(int i=1;i<=number;i++){
				System.out.print("*");
			}
			time++;
			System.out.println();
		}
	}

	// 【程序48】
//	 题目：某个公司采用公用电话传递数据，数据是四位的整数，
//	 在传递过程中是加密的，加密规则如下：每位数字都加上5,
//	 然后用和除以10的余数代替该数字，再将第一位和第四位交换，第二位和第三位交换。

	public static int getMiMa(int number){
		int num = 0;
		int qianwei = number/1000;
		int baiwei = (number-qianwei*1000)/100;
		int shiwei = (number-qianwei*1000-baiwei*100)/10;
		int gewei = number%10;
		int [] weishu = {gewei,shiwei,baiwei,qianwei};
		for (int i = 0; i < weishu.length; i++) {
			weishu[i] += 5;
			weishu[i] %= 10;
		}
		int temp = weishu[0];
		weishu[0] = weishu[3];
		weishu[3] = temp;
		
		temp = weishu[1];
		weishu[1] = weishu[2];
		weishu[2] = temp;
		
		String numValue = String.valueOf(weishu);
		num = Integer.parseInt(numValue);
		return num;
	}
	// 【程序49】
	// 题目：计算字符串中子串出现的次数
	/**
	 * @param str 较长的字符串 zichuan 子串
	 */
	public static void getZiChuang(String str, String zichuan) {
		int count = 0;
		int startIndex = 0;
		int index = -1;
		while ((str.indexOf(zichuan) >= 0)) {
			count++;
			startIndex += zichuan.length();
		}
		System.out.println(count);
	}

	// 【程序50】
	// 题目：有五个学生，每个学生有3门课的成绩，
	// 从键盘输入以上数据（包括学生号，姓名，三门课成绩），
	// 计算出平均成绩，况原有的数据和计算出的平均分数存放在磁盘文件"stud"中。
	public static void getGrade() {
		File flle = new File("D:stud.txt");
		if (!flle.exists()) {
			try {
				File.createTempFile("stud", "txt");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}
	}

}
