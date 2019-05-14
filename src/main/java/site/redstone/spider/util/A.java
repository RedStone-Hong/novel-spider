package site.redstone.spider.util;

public class A {
	public  void test1(String name) {
		synchronized(A.class) {
		for (int i = 0; i < 10; i++) {
			System.out.println("name:" + i);
		}}
	}
	
	public static void main(String[] args) {
		
	}
}
