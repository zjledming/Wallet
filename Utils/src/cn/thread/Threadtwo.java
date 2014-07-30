package cn.thread;

public class Threadtwo extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("线程" + this.getId() + "已启动执行...");
	}
}