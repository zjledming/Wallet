package cn.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @ClassName: CglibProxy
 * @Description: 将CGLIB做一个简单的包装
 * 
 *               包装以后的调用代码如下，主要是快速的实现获取被代理类：
 * 
 *               Person p = CglibProxy.proxyTarget(new Person());
 * 
 *               p.sayHi("HJello");
 * 
 *               IAnimal dog = CglibProxy.proxyTarget(new Dog());
 * 
 *               dog.eat();
 * @author XiMing.Fu
 * @date 2014-3-17 下午02:59:23
 * 
 */
public class CglibProxy implements MethodInterceptor {

	private Object srcTarget;

	private CglibProxy(Object o) {

		this.srcTarget = o;

	}

	@SuppressWarnings("unchecked")
	public static <T> T proxyTarget(T t) {

		Enhancer en = new Enhancer();

		en.setSuperclass(t.getClass());

		en.setCallback(new CglibProxy(t));

		T tt = (T) en.create();

		return tt;

	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args,

	MethodProxy proxy) throws Throwable {

		System.err.println("拦截前...");

		Object o = method.invoke(srcTarget, args);

		System.err.println("拦截后....");

		return o;

	}

}