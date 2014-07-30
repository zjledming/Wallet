package mst;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author 黄亚雄
 * @since 2013年3月7日
 * @param args
 */
public class Test05 {
	/**
	 * 【程序25】 
		题目：一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。 
	 */
	public static boolean huiwenshu(int number){
		boolean result = false;
		String strNumber = String.valueOf(number);
		StringBuffer numberStr = new StringBuffer(strNumber);
		numberStr.reverse();//利用reserse方法反转数据 如果反转前和后相等，则是回文数，反之则不是
		if(strNumber.equals(numberStr.toString())){
			result = true;
		}
		return result;
	}
	
	/**
	 * Monday Tuesday Wednesday  Friday Saturday 
	 * 		  Thursday					Sunday
	 * 【程序26】
		题目：请输入星期几的第一个字母来判断一下是星期几，如果第一个字母一样，则继续 判断第二个字母。
		1.程序分析：用情况语句比较好，如果第一个字母一样，则判断用情况语句或if语句判断第二个字母。
	 * @param args
	 */
	public static void testWeek(){
		Scanner sca = new Scanner(System.in);
		System.out.println("请随意输入一个字符");
		char week = sca.next().toLowerCase().charAt(0);
		switch (week) {
		case 'm':
			System.out.println("星期一");
			break;
		case 'w':
			System.out.println("星期三");
			break;
		case 'f':
			System.out.println("星期五");
			break;
		case 't':
			System.out.println("请输入第二个字符");
			week = sca.next().toLowerCase().charAt(0);
			if(week == 'u'){
				System.out.println("星期二");
			}else if(week == 'h'){
				System.out.println("星期四");
			}else{
				System.out.println("输入不正确");
			}
			break;
		case 's':
			System.out.println("请输入第二个字符");
			week = sca.next().toLowerCase().charAt(0);
			if(week == 'u'){
				System.out.println("星期日");
			}else if(week == 'a'){
				System.out.println("星期六");
			}else{
				System.out.println("输入不正确");
			}
			break;
		default:
			System.out.println("输入不正确");
			break;
		}
	}
	
	/**
	 * @param number表示number以内的质数
	 * 【程序27】
		题目：求100之内的素数 
	 */
	public List zhisu(int number){
		List zhisuList =  new ArrayList();
		for(int i=2;i<=number;i++){
			boolean flag = true;//先断言i是质数
			for(int j=2;j<=number/2+1;j++){
				if(i%j==0){
					//如果能整出则不是质数跳出循环
					flag = false;
					break;
				}
			}
			if(flag == true){
				zhisuList.add(i);
			}
		}
		return zhisuList;
	}
	
	/**
	 * 【程序28】
		题目：对10个数进行排序
		1.程序分析：可以利用选择法，即从后9个比较过程中，选择一个最小的与第一个元素交换，
	 	下次类推，即用第二个元素与后8个进行比较，并进行交换。
	 * @param args
	 */
	public int[] sortNumber(int [] numbers){
		int temp = 0;
		for(int i=0;i<numbers.length;i++){
			for(int j=i+1;j<numbers.length;j++){
				if(numbers[i] > numbers[j]){
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
				}
			}
		}
		return numbers;
	}
	/**
	 * 【程序29】
	 *	题目：求一个3*3矩阵对角线元素之和 
	 *	1.程序分析：利用双重for循环控制输入二维数组，再将a[i][i]累加后输出。
	 *  1	2	3
	 *  4	5	6
	 *  7	8	9
	 * @return 对角线的元素之和
	 */
	public static int getSumByArray(int length){
		int sum =  0;
		int number = 0;
		int [][] numbers = new int [length][length];
		for(int i=0;i<numbers.length;i++){
			for(int j=0;j<numbers[0].length;j++){
				number++;
				numbers[i][j] = number;
				if(i==j||i+j==numbers.length-1){
					System.out.println(i+" "+j);
					sum += numbers[i][j];
				}
			}
		}
		sum += numbers[numbers.length/2][numbers.length/2];//中间那个元素要加两次
		return sum;
	}
//	【程序30】
//	题目：有一个已经排好序的数组。现输入一个数，要求按原来的规律将它插入数组中。
//	1. 程序分析：首先判断此数是否大于最后一个数，然后再考虑插入中间的数的情况，插入后此元素之后的数，依次后移一个位置。
	/***
	 *@return int[]返回一个已经排序的数组
	 *@param number：需要插入的数据 firsrArray[]表示一个已经排序好的数组 
	 */
	public static int[] chashuju(int number,int firstArray[]){
		int flag = 0;
		for (int i = firstArray.length-1; i >= 0; i--) {
			if(number>=firstArray[i]){
				flag = i;
				break;
			}
		}
		int [] newArray = new int [firstArray.length+1];
		System.out.println(flag+" 11111d");
		for (int i = 0; i < newArray.length; i++) {
			if(i <= flag){
				newArray[i] = firstArray[i];
			}else if(i == flag+1){
				newArray[i] = number;
			}else{
				newArray[i] = firstArray[i-1];
			}
		}
		
		return newArray;
	}
	//	【程序31】
	//	题目：将一个数组逆序输出。
	//	1.程序分析：用第一个与最后一个交换。
	/**
	 * @param obj[] 一个任意类型的数组
	 * @return Object[] 返回一个倒序的数组
	 */
	public static Object [] nixu(Object []obj){
		Object temp = null;
		for (int i = 0; i < obj.length/2; i++) {
			temp = obj[i];
			obj[i] = obj[obj.length-i-1];
			obj[obj.length-i-1] = temp;
		}
		return obj;
	}
	
//	【程序32】
//	题目：取一个整数a从右端开始的4～7位。
//	程序分析：可以这样考虑： 
//	(1)先使a右移4位。
//	(2)设置一个低4位全为1,其余全为0的数。可用~(~0<<4)
//	(3)将上面二者进行&运算。
	
	
//	【程序33】
//	题目：打印出杨辉三角形（要求打印出10行如下图） 
//	1.程序分析：
//	1
//	1 1
//	1 2 1
//	1 3 3 1
//	1 4 6 4 1
//	1 5 10 10 5 1 
	/**
	 * @param hangshu表示需要打印的行数
	 * 考虑到杨辉三角的结构，所以采用二维数组的数据结构。
	 */
	public static void yanghui(int hangshu){
		int number[][] = new int [hangshu][hangshu];
		for(int i = 0;i < number.length; i++){//外层循环用来控制行数
			for(int j = 0; j <= i;j++){//内层循环用来控制一行中的个数
				if(j==0||i==j){
					number[i][j] = 1;
				}else{
					number[i][j] = number[i-1][j-1]+number[i-1][j];//除了每一行的第一个和最后一个等于1以外，
																  //其他的每一个数据等于上一层前面两个数据之和
				}
				System.out.print(number[i][j]+" ");
			}
			System.out.println();
		}
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(huiwenshu(21323));
//		testWeek();
//		int [] a = {0,1,2,4};
//		a = chashuju(3,a);
//		for (int i = 0; i < a.length; i++) {
//			System.out.println(a[i]);
//		}
		yanghui(10);
	}

}
