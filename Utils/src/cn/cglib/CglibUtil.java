package cn.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.junit.Test;

public class CglibUtil {

	/**
	 * @Title: testProxy1
	 * @Description: 测试代理一个没有实现任何接口的Person类
	 * @throws Exception
	 * @return void
	 * @throws
	 * @author XiMing.Fu
	 */
	@Test
	public void testProxy1() throws Exception {

		final Person p1 = new Person(); // Person类没有实现任何接口

		Enhancer en = new Enhancer(); // 声明增加类实例

		en.setSuperclass(Person.class); // 设置被代理类字节码，CGLIB根据字节码生成被代理类的子类

		en.setCallback(new MethodInterceptor() {// 设置回调函数，即一个方法拦截

			public Object intercept(Object target, Method method,

			Object[] args, MethodProxy proxy) throws Throwable {

				Object o = method.invoke(p1, args); // 注意参数p1,仍然为外部声明的源对象，且Method为JDK的Method反射

				System.err.println("After...");

				return o;

			}

		});

		Person p = (Person) en.create(); // 通过create方法返回Person类的代理

		System.err.println(p.getClass());// 被代理的对象

		p.sayHi("Hello");

	}

	@Test
	public void testProxy2() throws Exception {

		final Dog dog = new Dog(); // 声明被代理对象

		Enhancer en = new Enhancer(); // 声明CGLIB增强类

		en.setSuperclass(IAnimal.class); // 设置接口类，也可以设置成dog实现类，会影响create返回的对象

		en.setCallback(new MethodInterceptor() {

			public Object intercept(Object target, Method method,

			Object[] args, MethodProxy proxy) throws Throwable {

				System.err.println("Before...");

				Object o = method.invoke(dog, args);

				System.err.println("After...");

				return o;

			}

		});

		// Dog dog2 = (Dog) en.create();//必须转型为接口,否则抛出ClassCastException

		IAnimal dog2 = (IAnimal) en.create();

		dog2.eat();

	}

	/**
	 * @Title: main
	 * @Description: 使用静态方法代理一个没有接口的对象
	 * @param args
	 * @return void
	 * @throws
	 * @author XiMing.Fu
	 */
	public static void main(String[] args) {
		final Person src = new Person();

		// 直接使用静态方法代理一个对象

		Person p = (Person) Enhancer.create(Person.class,
				new MethodInterceptor() {

					public Object intercept(Object proxyedObj, Method method,
							Object[] args,

							MethodProxy proxy) throws Throwable {

						System.err.println("Hello");

						// 使用原生的方法调用，注意里面的src

						// Object oo = method.invoke(src, args);

						// 使用MethodProxy调用父类的代码，同样有效

						Object oo = proxy.invokeSuper(proxyedObj, args);

						return oo;

					}

				});

		System.err.println(p.getClass());

		p.abc();
	}
}
