package cn.cglib.cglibdynamicproxy;

/**
 * @ClassName: TestCglib
 * @Description: cglib动态代理
 * @author XiMing.Fu
 * @date 2014-3-17 下午02:33:47
 * 
 */
public class TestCglib {

	public static void main(String[] args) {
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacadeImpl1 bookCglib = (BookFacadeImpl1) cglib
				.getInstance(new BookFacadeImpl1());
		bookCglib.addBook();
	}
}