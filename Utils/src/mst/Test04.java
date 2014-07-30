package mst;

/**
 * 
 * @author 黄亚雄
 * @since 20113年3月7日
 * 
 */
public class Test04 {


	/**
	 * @author 黄亚雄 题目：打印出如下图案（菱形）将一个菱形分为上下两个部分 为别用两个循环控制空格和* 行数用一个参数来传入 * ***
	 *         ***** ******* ***** *** *
	 */
	public static void lingxing(int hangshu) {
		for (int i = 1; i <= hangshu; i++) {// 控制行数
			if (i <= (hangshu / 2) + 1) {
				for (int j = 1; j <= (hangshu / 2 + 1) - i; j++) {
					System.out.print(" ");
				}
				for (int k = 1; k <= 2 * i - 1; k++) {

					System.out.print("*");
				}
			} else {
				for (int m = 1; m <= i - hangshu / 2 - 1; m++) {
					System.out.print(" ");
				}
				for (int n = 1; n <= 2 * (hangshu - i) + 1; n++) {
					System.out.print("*");
				}
			}

			System.out.println();
		}
	}
	/**
	 * @author 黄亚雄
	 * @since 20113年3月7日
	 * 【程序20】 
		题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前20项之和。
		1.程序分析：请抓住分子与分母的变化规律。 
	 */
	 
	public static float getSum(){
		float sum = 0;//初始化和为0
		int fenzi = 2;//初始化分子为2
		int fenmu = 1;//初始化分母为1
		int temp = 0;
		for(int i=1;i<=20;i++){
			sum += fenzi/fenmu;
			temp = fenzi;
			fenzi += fenmu;//第二个数的分子为前一个数的分子和分母之和
			fenmu = temp;//第二个数的分母为前面一个数字的分子
		}
		return sum;
	}
	
	
	/**
	 * 【程序21】 
		题目：求1+2!+3!+...+20!的和
		1.程序分析：此程序只是把累加变成了累乘。 
	 */
	
	public static int getSumBy20(){
		int sum = 0;//初始化sum为0
		for(int i=1;i<=20;i++){//外层循环控制需要相加的数的个数
			int number = 1;
			for(int j = 1;j<=i;j++){//内层循环用来求每一个数的阶乘
				number *= j;
			}
			sum += number;
		}
		return sum;
	}
	
	/**
	 * @param number 表示需要求的n！
	 * 【程序22】 
		题目：利用递归方法求5!。
		1.程序分析：递归公式：fn=fn_1*4!
	 */
	
	public static int digui(int number){
		int result = 0;
		if(number==1){
			result =  1;
		}else if(number==2){
			result =  2;
		}else{
			result =  digui(number-1)*number;//递归调用自身
		}
		return result;
	}
	
	/**
	 * @param perpleNumber 表示第N个人 
	 * 【程序23】 
		题目：有5个人坐在一起，问第五个人多少岁？他说比第4个人大2岁。
		问第4个人岁数，他说比第3个人大2岁。问第三个人，又说比第2人大两岁。
		问第2个人，说比第一个人大两岁。最后问第一个人，他说是10岁。请问第五个人多大？
		1.程序分析：利用递归的方法，递归分为回推和递推两个阶段。要想知道第五个人岁数，
		需知道第四人的岁数，依次类推，推到第一人（10岁），再往回推。
	 */
	public static int getYear(int perpleNumber){
		int year = 10;//初始化第一个人的年龄为10岁
		year = 10+2*(perpleNumber-1);
		return year;
	}
	/**
 	 *  【程序24】 
		题目：给一个不多于5位的正整数，要求：一、求它是几位数，二、逆序打印出各位数字。
	 */
	public static int getWeishu(int number){
		String strNum = String.valueOf(number);//解析：此题只需要将number转换为String接下来的问题就很简单了
		int length = strNum.length();
		StringBuffer strNumValue = new StringBuffer(strNum);
		strNumValue = strNumValue.reverse();//因为在StringBuffer中有一个反置的reverse方法所以先将String转为StringBuffer
		System.out.println(strNumValue);
		return length;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		lingxing(5);
//		System.out.println(getSum());
//		System.out.println(getSumBy20());
		System.out.println(getWeishu(12345));
	}

}
