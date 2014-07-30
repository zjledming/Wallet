package cn.cglib.dynamicproxy;

/**
 * @ClassName: TestProxy
 * @Description: jdk动态代理： 只需要将委托对象target传入代理类，
 *               bookProxy.addBook();执行的时候，会自动执行代理类中invoke方法的前后业务
 * @author XiMing.Fu
 * @date 2014-3-17 下午02:20:56
 * 
 */
public class TestProxy {

	public static void main(String[] args) {
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookProxy.addBook();
	}

}