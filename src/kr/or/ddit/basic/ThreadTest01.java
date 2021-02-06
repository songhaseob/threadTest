package kr.or.ddit.basic;


public class ThreadTest01 {
	// 싱글 쓰레드
	public static void main(String[] args) {
		for(int i=1; i<=200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		System.out.println();
		
		for(int j=1; j<=200; j++) {
			System.out.print("$");
		}

	}

}
