package mst;

/**
 * 
 * @author 黄亚雄
 * @since 2013年3月8日
 */

public class Test07 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(getPeach());
		// getLiangWeiShu();
		System.out.println(testSuShuHe(40));
		// System.out.println(isSuShu(4));
	}

	// 【程序41】
	// 题目：海滩上有一堆桃子，五只猴子来分。
	// 第一只猴子把这堆桃子凭据分为五份，多了一个，
	// 这只猴子把多的一个扔入海中，拿走了一份。
	// 第二只猴子把剩下的桃子又平均分成五份，又多了一个，
	// 它同样把多的一个扔入海中，拿走了一份，
	// 第三、第四、第五只猴子都是这样做的，问海滩上原来最少有多少个桃子？
	/**
	 * @return int 为最开始最少的桃子数目
	 */
	public static int getPeach() {
		// 求最少的桃子数目则最后分为每一组只留下一个桃子
		int sumByGroup = 1;
		// 采用逆推的方法可知每一组的桃子数目=5*下一组的桃子数目 + 1
		for (int i = 5; i >= 1; i--) {
			sumByGroup = (sumByGroup * 5 + 1);// 则最开始及第0组就是第五次循环后的桃子数目
		}
		return sumByGroup;
	}

	// 【程序42】
	// 题目：809*??=800*??+9*??+1 其中??代表的两位数,8*??的结果为两位数，
	// 9*??的结果为3位数。求??代表的两位数，及809*??后的结果。

	public static void getLiangWeiShu() {
		int liangweishu = 10;
		for (int i = 10; i <= 99 / 8; i++) {
			if (9 * i >= 100) {
				if (809 * i == 800 * i + 9 * i + 1) {
					liangweishu = i;
				}
				break;
			}
		}
		System.out.println("该两位数为:" + liangweishu + ",809*" + liangweishu + "="
				+ (800 * liangweishu + 9 * liangweishu + 1));
	}

	// 【程序43】
	// 题目：求0—7所能组成的奇数个数。
	public int sumOfJiShu() {
		int count = 0;
		int n = 8;
		// 一位数
		count = n / 2;
		// 两位数
		count += (n - 1) * n / 2;
		// 三位数
		count += (n - 1) * n * n / 2;
		// 四位数
		count += (n - 1) * n * n * n / 2;
		// 五位数
		count += (n - 1) * n * n * n * n / 2;
		// 六位数
		count += (n - 1) * n * n * n * n * n / 2;
		// 七位数
		count += (n - 1) * n * n * n * n * n * n / 2;
		return count;
	}

	//	

	// 【程序44】
	// 题目：一个偶数总能表示为两个素数之和。
	/**
	 * @param oushu为用户自己随意输入的一个偶数
	 * @return 返回一个boolean类型 判断是不是可以分解成两个素数只和
	 */
	public static boolean testSuShuHe(int oushu) {
		boolean flag = false;
		int number1 = 0;
		int number2 = 0;
		for (int i = 1; i < oushu; i++) {
			number1 = i;
			number2 = oushu - i;
			if (isSuShu(number1) && isSuShu(number2)) {// 判断是不是两个数都为素数
				flag = true;
				System.out.println(number1 + "+" + number2 + "="
						+ (number1 + number2));
				break;
			}
		}
		return flag;
	}

	// 为了方便调用，私有定义一个判断素数的方法
	private static boolean isSuShu(int number) {
		boolean flag = true;
		if (number == 2) {
			return flag;
		}
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// 【程序45】
	// 题目：判断一个素数能被几个9整除

	// 解：之书不能被9整除
}
