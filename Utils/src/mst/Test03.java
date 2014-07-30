package mst;

import java.util.ArrayList;
import java.util.List;

public class Test03 {

	
	/**
	 * 【程序13】
		题目：一个整数，它加上100后是一个完全平方数，再加上168又是一个完全平方数，请问该数是多少？
		1.程序分析：在10万以内判断，先将该数加上100后再开方，再将该数加上268后再开方，如果开方后的结果满足如下条件，即是结果。
	 */
	public List findNumber(){
		List number = new ArrayList();
		for(int i = 1;i<=10000;i++){
			if(Math.sqrt(i+100)%1==0){
				if((Math.sqrt(i+268))%1==0){
					number.add(i);
				}
			}
		}
		return number;
	}
	/**
	 * 【程序14】
		题目：输入某年某月某日，判断这一天是这一年的第几天？
		1.程序分析：以3月5日为例，应该先把前两个月的加起来，然后再加上5天即本年的第几天，
		特殊情况，闰年且输入月份大于3时需考虑多加一天。
	 */
	public int getDays(int year,int month,int day){
		int days = 0;
		switch (month) {
		case 1:
			days = day;
			break;
		case 2:
			days = 31+day;
			break;
		case 3:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 29 + 31;
			 }
			 days = day + 28 + 31;
			break;
		case 4:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 29 + 31 + 31;
			 }
			 days = day + 28 + 31*2;
			break;
		case 5:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 29 + 31*2 + 30;
			 }
			 days = day + 28 + 31*2 +30;
			break;
		case 6:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 29 + 31*3 +30;
			 }
			 days = day + 28 + 31*3 + 30;
			break;
		case 7:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 29 + 31*3 + 30*2;
			 }
			 days = day + 28 + 31*3 + 30*2;
			break;
		case 8:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 31*4 + 30*2;
			 }
			 days = day + 28 + 31*4 + 30*2;
			break;
		case 9:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 29 + 31*5 + 30*2;
			 }
			 days = day + 28 + 31*5 + 30*2;
			break;
		case 10:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 29 + 31*5 + 30*3;
			 }
			 days = day + 28 + 31*5 + 30*3;
			break;
		case 11:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 29 + 31*6 + 30*2;
			 }
			 days = day + 28 + 31*6 + 30*2;
			break;
		case 12:
			 if((year%4==0&&year%100!=0)||year%400==0){
				 days = day + 29 + 31*6 + 30*3;
			 }
			 days = day + 28 + 31*6 + 30*3;
			break;

		default:
			break;
		}
		return days;
	}
	
	/**
	 * 【程序15】
	题目：输入三个整数x,y,z，请把这三个数由小到大输出。
	1.程序分析：我们想办法把最小的数放到x上，先将x与y进行比较，
	如果x>y则将x与y的值进行交换，然后再用x与z进行比较，如果x>z则将x与z的值进行交换，这样能使x最小。
	 */
	public static void paixu(int number1,int number2,int number3){
		int min = number1;
		int mid = 0;
		int max = 1;
		if(number2<min){
			
			if(number3<min){
				min = number3;
				max = number2;
				mid = number1;
				if(max < number1){
					max = number1;
					mid = number2;
				}
			}else{
				min = number2;
				max = number1;
				mid = number3;
				if(max < number3){
					max = number3;
					mid = number1;
				}
			}
		}else if(number3<min){
			min = number3;
			max = number2;
			mid = number1;
			if(max < number1){
				max = number1;
				mid = number2;
			}
		}
		
	}
	/**
	 * 【程序16】
		题目：输出9*9口诀。
		1.程序分析：分行与列考虑，共9行9列，i控制行，j控制列。
	 */
	public static void nine(){
		for(int i=1;i<=9;i++){
			for(int j=1;j<=i;j++){
				System.out.print(i+"*"+j+"="+i*j+"\t\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * 【程序17】
		题目：猴子吃桃问题：猴子第一天摘下若干个桃子，
		当即吃了一半，还不瘾，又多吃了一个 第二天早上又将剩下的桃子吃掉一半，又多吃了一个。
		以后每天早上都吃了前一天剩下 的一半零一个。到第10天早上想再吃时，见只剩下一个桃子了。求第一天共摘了多少。
		1.程序分析：采取逆向思维的方法，从后往前推断。
	 */
	public int monkeyEatPeach(){
		int sum = 1;
		for(int i=1;i<=10;i++){
			sum = (sum+1)*2;
		}
		return sum;
	}
	/**
	 * 【程序18】
		题目：两个乒乓球队进行比赛，各出三人。甲队为a,b,c三人，乙队为x,y,z三人。
		已抽签决定比赛名单。有人向队员打听比赛的名单。a说他不和x比，c说他不和x,z比，
		请编程序找出三队赛手的名单。 
		1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除， 则表明此数不是素数，反之是素数。
	 */
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test03 t3 = new Test03();
		System.out.println(t3.monkeyEatPeach());
//		nine();
	}

}
