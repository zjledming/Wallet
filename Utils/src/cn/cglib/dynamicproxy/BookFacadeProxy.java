package cn.cglib.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理代理类
 * 
 * @author student
 * 
 */
public class BookFacadeProxy implements InvocationHandler {
	private Object target;

	/**
	 * 绑定委托对象并返回一个代理类
	 * 
	 * @param target
	 * @return
	 */
	public Object bind(Object target) {
		this.target = target;
		// 取得代理对象
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this); // 要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
	}

	/**
	 * 调用方法 Object 参数说明：proxy：指被代理的对象。 Method method：要调用的方法 Object[]
	 * args：方法调用时所需要的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		System.out.println("事物开始");
		// 执行方法：代理类的对象本身并不真正实现服务，而是通过调用委托类的对象的相关方法，来提供特定的服务
		result = method.invoke(target, args);
		System.out.println("事物结束");
		return result;
	}

}