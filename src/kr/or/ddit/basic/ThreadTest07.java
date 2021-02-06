package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
	문제) 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
		- 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
		- 사용자는 showInputDialog()메서드을 이용해서 입력 받는다.
		
		- 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
		- 5초안에 입력이 없으면 게임에 진것으로 처리하고 끝낸다.
		
		- 5초안에 입력이 완료되면 승패를 구해서 출력한다.
	
	5초안에 입력이 완료되었을 때 결과 예시)
		-- 결과 --
		컴퓨터 : 가위
		사용자 : 바위
		결  과 : 당신이 이겼습니다.
		
    5초안에 입력이 없었을때 결과 예시)
    	시간이 초과되어 당신이 졌습니다.
    	
*/
public class ThreadTest07 {
	public static boolean inputCheck;
	
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		// 난수를 이용해서 컴퓨터의 가위 바위 보를 정한다.
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * 3 );  // 0~2사이의 난수 만들기
		String com = data[index];
		
		// 사용자로부터 가위 바위 보 입력 받기
		gt.start();
		String man;
		do {
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		//}while( !(man.equals("가위") || man.equals("바위") || man.equals("보")) );
		}while( !man.equals("가위") && !man.equals("바위") && !man.equals("보") );
		
		inputCheck = true;
		
		// 결과를 판정하기
		String result = "";  // 결과가 저장될 변수 선언
		if(man.equals(com)) {
			result = "비겼습니다.";
		}else if( man.equals("가위") && com.equals("보") || 
				man.equals("바위") && com.equals("가위") ||
				man.equals("보") && com.equals("바위")) {
			result = "당신이 이겼습니다.";
		}else {
			result = "당신이 졌습니다.";
		}
		
		// 결과 출력
		System.out.println();
		System.out.println("--- 결   과  ---");
		System.out.println(" 컴퓨터 : " + com);
		System.out.println(" 당  신 : " + man);
		System.out.println(" 결  과 : " + result);

	}
}


class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		for(int i=5; i>=1; i--) {
			
			if(ThreadTest07.inputCheck==true) {  // 입력 여부 검사
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("시간이 초과되어 당신이 졌습니다.");
		System.exit(0);
	}
}











