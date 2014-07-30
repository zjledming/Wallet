package mst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test02 {

	/***
	 * 【程序7】 题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
	 * 1.程序分析：利用while语句,条件为输入的字符不为'\n'.
	 */
	public Map fenlei() {
		Map res = new HashMap();
		char charVar = 0;// 用来接收输入的字符
		int yingwen = 0;
		int kongge = 0;
		int shuzi = 0;
		int qita = 0;
		Scanner sca = new Scanner(System.in);
		System.out.println("请输入任意字符");
		charVar = sca.next().charAt(0);
		while (charVar != '\n') {
			if (charVar == ' ') {
				kongge++;
			} else if ((charVar >= 65 && charVar <= 90)
					|| (charVar >= 97 && charVar <= 122)) {
				yingwen++;
			} else if (charVar >= 48 && charVar <= 57) {
				shuzi++;
			} else {
				qita++;
			}
		}
		// 用map来存放 key表示为类型名称，value表示为值
		res.put("kongge", kongge);
		res.put("yingwen", yingwen);
		res.put("shuzi", shuzi);
		res.put("qita", qita);
		return res;
	}

	/**
	 * @param number表示一个数字
	 * @param weishu表示几个数相加
	 *            【程序8】 题目：求s=a+aa+aaa+aaaa+aa...a的值，
	 *            其中a是一个数字。例如2+22+222+2222+22222(此时共有5个数相加)， 几个数相加有键盘控制。
	 *            1.程序分析：关键是计算出每一项的值。
	 */
	public long getSum(int number, int weishu) {
		long sum = 0;
		for (int i = 1; i <= weishu; i++) {
			int temp = 0;// 初始化
			for (int j = 1; j <= i; j++) {
				temp += Math.pow(10, j - 1) * number;// 内存循环用来计算每一项的值
			}
			sum += temp;

		}

		return sum;
	}

	/**
	 * @return wanshu是一个集合，里面装有一千以内大的所有完数 【程序9】 题目：一个数如果恰好等于它的因子之和，这个数就称为"完数"。
	 *         例如6=1＋2＋3.编程 找出1000以内的所有完数。
	 */
	public List wanshu() {
		List wanshu = new ArrayList();
		int yushu = 0;
		for (int i = 1; i <= 1000; i++) {
			int sum = 1;
			for (int j = 2; j < i / 2 + 1; j++) {
				if (i % j == 0) {
					yushu = i / j;
					sum = sum + j;
				}
			}
			if (sum == i) {
				wanshu.add(i);
			}
		}
		return wanshu;
	}

	/**
	 * 【程序10】 题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半； 再落下，求它在 第10次落地时，共经过多少米？第10次反弹多高？
	 */
	public float[] testBall() {// 由于要返回两个float类型的数据所以返回一乐float类型的数组
		float[] balls = new float[2];
		float sumLenth = 0;
		float high = 100;
		for (int i = 1; i <= 10; i++) {
			sumLenth += high;
			high = high / 2;
		}
		balls[0] = sumLenth;
		balls[1] = high;
		return balls;
	}

	/**
	 * 【程序11】 题目：有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
	 * 1.程序分析：可填在百位、十位、个位的数字都是1、2、3、4。组成所有的排列后再去 掉不满足条件的排列。
	 */
	public List testTN() {
		List nums = new ArrayList();
		int n = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 4; k++) {
					if (i != j && j != k && i != k ) {
						n++;
						System.out.println("NO." + n + ":" + i + "" + j + ""
								+ k);
					}
				}
			}
		}
		System.out.println("共有:" + n + "种");
		return nums;
	}
	
	/**
	 * 【程序12】【程序12】
题目：企业发放的奖金根据利润提成。利润(I)低于或等于10万元时，奖金可提10%；
利润高于10万元，低于20万元时，低于10万元的部分按10%提成，高于10万元的部分，
可可提成7.5%；20万到40万之间时，高于20万元的部分，可提成5%；40万到60万之间时高于40万元的部分，
可提成1.5%，高于100万元时，超过100万元的部分按1%提成，从键盘输入当月利润I，求应发放奖金总数？
1.程序分析：请利用数轴来分界，定位。注意定义时需把奖金定义成长整型。
	 */
	public static long moneyAward(long lirun){{
		long jiangjin = 0;
		if(lirun <= 100000){
			jiangjin = (long) (lirun*0.1);
		}else if(lirun <= 20){
			jiangjin = (long) (100000*0.1+(lirun-10)*0.075);
		}else if(lirun <= 40 ){
			jiangjin = (long) (100000*0.1+100000*0.075+(lirun-400000)*0.05);
		}else if(lirun <= 60){
			jiangjin = (long) (100000*0.1+100000*0.075+200000*0.05+(lirun-400000)*0.015);
		}else if(lirun > 1000000){
			jiangjin = (long) (100000*0.1+100000*0.075+200000*0.05+600000*0.015+(lirun-1000000)*0.01);
		}
		return jiangjin;
	}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test02 t = new Test02();
		float[] balls = t.testBall();
		System.out.println(balls[0] + " " + balls[1]);
	}
}
