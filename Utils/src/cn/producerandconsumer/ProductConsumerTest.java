package cn.producerandconsumer;

/**
 * 
 * 
 * @version 1.0 2013-7-24 下午02:18:36
 */
public class ProductConsumerTest {
	public static void main(String[] args) {
		// 下面的消费者类对象和生产者类对象所操作的是同一个同步堆栈对象
		SynchronizedStack stack = new SynchronizedStack(5);
		Runnable source = new Producer(stack);
		Runnable sink = new Consumer(stack);

		Thread t1 = new Thread(source);
		Thread t2 = new Thread(sink);
		t1.start();
		t2.start();
	}
}