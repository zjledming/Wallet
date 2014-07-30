package mst;

import java.util.ArrayList;
import java.util.List;

public class Test011 {

	
	public static void main(String[] args) {
		Test011 test = new Test011();
//		int result = test.getRabitSum(5);
//		System.out.println(result);
//		List l = test.getSuShu();
//		System.out.println(l.size());
//		getAllFlowerNumber();
//		System.out.println(test.fenjieNumber(25));
		System.out.println(test.getGrade(25));
	}
	/**
	 * @黄亚雄
	 * @return month个月的兔子总数和
	 * @param  需要求的月数
	 * 
	 * 题目1：古典问题：有一对兔子，
	 * 从出生后第3个月起每个月都生一对兔子，
	 * 小兔子长到第三个月后每个月又生一对兔子，
	 * 假如兔子都不死，问每个月的兔子总数为多少？
	 * 1.程序分析： 兔子的规律为数列1,1,2,3,5,8,13,21....
	 */
	public int getRabitSum(int month){
		int lastRabit = 1; //设置初始的前月的兔子数
		int currRabit = 1;//设置初始的当月兔子总数
		int sumRabits = 2;//设置初始的兔子总数
		int temp = 0;
		//当月份小于1时。不合法。
		if(month<=0){
			return 0;
		}
		//当月份为1时只有一个兔子
		else if(month<=1){
			return 1;
		}
		//当月份为2时
		else if(month<=2){
			return sumRabits;
		}
		//当月份大于等于3时
		//1.第一个循环先设置当月的兔子数目为前面两个月的兔子数目只和
		//2.总兔子数则为sumRabits加上当月的兔子数目
		for(int i=3;i<=month;i++){
			temp = currRabit;
			currRabit += lastRabit;
			lastRabit = temp;
			sumRabits +=  currRabit;
		}
		return sumRabits;
	}

	/**
	 * @author 黄亚雄
	 * @return 装有101到200所有素数的集合
	 * @serialData 2012年03月06日
	 * 题目02：判断101-200之间有多少个素数，并输出所有素数。
	 * 1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，
	 * 则表明此数不是素数，反之是素数。 
	 */
	public List getSuShu(){
		List sushu = new ArrayList();
		for(int i = 101;i<=200;i++){
			boolean flag = true;//定义一个判定是不是素数的标记
			for(int j = 2; j<=Math.sqrt(i);j++){
				if(i%j == 0){
					flag = false;
					break; //如果能够被整除，则证明该数不是素数。跳出此次循环
				}
			}
			if(flag){
				sushu.add(i);//如果是素数就添加到sushe集合中去
			}
		}
		return sushu;
	}
	
	/**
	 * @author 黄亚雄
	 * @return 装有100到999所有水仙花数-的集合
	 * @serialData 2012年03月06日
	 * 题目：打印出所有的"水仙花数"，
	 * 所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。
	 * 例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。
	 * 1.程序分析：利用for循环控制100-999个数，每个数分解出个位，十位，百位。
	 */
	public  List getAllFlowerNumber(){
		List flowerNumbers = new ArrayList();
		int gewei = 0;
		int shiwei = 0;
		int baiwei = 0;
		for(int i = 100;i<=999;i++){
			baiwei = i/100;
			shiwei = (i-100*baiwei)/10;
			gewei = i%10;
			if((i==baiwei*baiwei*baiwei+shiwei*shiwei*shiwei+gewei*gewei*gewei)){
				flowerNumbers.add(i);
			}
		}
		return flowerNumbers;
	}
	/**
	 * @author 黄亚雄
	 * @return 参数number因式分解的一个String
	 * @serialData 2012年03月06日
	 * 【程序4】
		题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
		程序分析：对n进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成： 
		(1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
		(2)如果n<>k，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的正整数你n,重复执行第一步。
		(3)如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
	 */
	public String fenjieNumber(int number){
		String result = String.valueOf(number) + "=";
		boolean flag = true;
		int chushu = 2;//定义一个从二开始的除数
		int yushu = 0;//初始化余数为0
		while(flag){
			yushu = number%chushu;
			if(yushu == 0){
				result += (chushu+"*");
				if(chushu == number){
					flag = false;
				}else{
					number = number/chushu;
				}
			}else{
				chushu += 1;
			}
		}
		result = result.substring(0, result.length()-1);//去掉末尾的那个*
		return result;
	}
	
	/**
	 * @author 黄亚雄
	 * @return 参数fenshu的成绩等级
	 * @serialData 2012年03月06日
	 * 【程序5】
		题目：利用条件运算符的嵌套来完成此题：学习成绩>=90分的同学用A表示，
		60-89分之间的用B表示，60分以下的用C表示。
		1.程序分析：(a>b)?a:b这是条件运算符的基本例子。
	 */
	public String getGrade(int fenshu){
		String result = "";
		result = ((fenshu >= 90) ? "A" : ((fenshu>=60) ? "B" : "C"));
		return result;
	}
	/**
	 * 【程序6】
		题目：输入两个正整数m和n，求其最大公约数和最小公倍数。
		1.程序分析：利用辗除法。
		
	 */
	public int[] getGongyueshuAndGongyueshu(int number1,int number2){
		int [] res = new int [2];
		
		return res;
	}
}
