package cn.cglib.staticproxy;

/**
 * @ClassName: TestCount
 * @Description: 静态代理：程序员创建或特定工具自动生成源代码，再对其编译。在程序运行前，代理类的.class文件就已经存在了。
 *               缺点：每一个代理类只能为一个接口服务
 *               ，这样一来程序开发中必然会产生过多的代理，而且，所有的代理操作除了调用的方法不一样之外，其他的操作都一样，则此时肯定是重复代码
 * @author XiMing.Fu
 * @date 2014-3-17 下午01:54:30
 * 
 */
public class TestCount {
	public static void main(String[] args) {
		CountImpl countImpl = new CountImpl();
		CountProxy countProxy = new CountProxy(countImpl);
		countProxy.updateCount();
		countProxy.queryCount();

	}
}