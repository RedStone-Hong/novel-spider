package site.redstone.spider.util;

public class Demo {
	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadDemo());
		Thread t2 = new Thread(new ThreadDemo());
		Thread t3 = new Thread(new ThreadDemo());
		Thread t4 = new Thread(new ThreadDemo());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
