package site.redstone.spider.util;

public class ThreadDemo implements Runnable{

	private A a = new A();
	@Override
	public void run() {
		a.test1(Thread.currentThread().getName());
	}

}
