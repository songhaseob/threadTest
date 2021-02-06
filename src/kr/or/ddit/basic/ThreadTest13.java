package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
	문제) 10마리의 말들이 경주하는 경마 프로그램 작성하기
	
		경주마는 Horse라는 이름의 클래스로 구성하고
		이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
		그리고, 이 클래스에는 등수를 오름차순으로 처리하는 내부 정렬 기준이 있다.
		       (Comparable인터페이스 구현하기)
		- 이 Horse클래스는 쓰레드로 작성한다.
		
		- 경기 구간은 1 ~ 50구간으로 되어 있다.
		
		- 경기 중 중간 중간에 각 말들의 위치를 나타내시오.
		예)
		01번말 : ----->---------------------------------
		02번말 : --->-----------------------------------
		...
		10번말 : ------>--------------------------------
		
		- 경기가 끝나면 등수 순으로 경기 결과를 출력한다.

*/
public class ThreadTest13 {

	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
			new Horse("01번말"), new Horse("02번말"), new Horse("03번말"),	
			new Horse("04번말"), new Horse("05번말"), new Horse("06번말"),	
			new Horse("07번말"), new Horse("08번말"), new Horse("09번말"),	
			new Horse("10번말")	
		};

		GameSate gs = new GameSate(horses);
		
		System.out.println("경기 시작...");
		
		for(Horse h : horses) {
			h.start();
		}
		
		gs.start();
		
		for(Horse h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}
		
		try {
			gs.join();
		} catch (InterruptedException e) {
		}
		
		
		System.out.println("경기 끝...");
		/*
		// 방법1) 배열을 직접 정렬하고 출력하기
		// 정렬
		Arrays.sort(horses);
		
		// 출력
		for(Horse h : horses) {
			System.out.println(h);
		}
		*/
		// 방법2) 배열의 데이터를 List에 담고 List를 정렬하여 출력하기
		ArrayList<Horse> horseList = new ArrayList<>();
		for(Horse h : horses) {
			horseList.add(h);
		}
		
		Collections.sort(horseList);
		
		for(Horse h : horseList) {
			System.out.println(h);
		}
		
	}

}

/*
	경주마는 Horse라는 이름의 클래스로 구성하고
		이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
		그리고, 이 클래스에는 등수를 오름차순으로 처리하는 내부 정렬 기준이 있다.
		       (Comparable인터페이스 구현하기)
		- 이 Horse클래스는 쓰레드로 작성한다.
	- 경기 구간은 1 ~ 50구간으로 되어 있다.
*/
class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank = 0;	// 말들의 등수 계산에 사용되는 변수 선언
	
	private String horseName;	// 말이름
	private int rank;			// 등수
	private int position;		// 현재 위치
	
	// 생성자
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "의 등수는 " + rank + "등 입니다.";
	}
	
	// 등수를 오름차순
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(rank, horse.getRank());
	}
	
	// 말이 달리는 부분을 쓰레드로 처리한다.
	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			position = i;		// 말의 현재 위치를 저장한다.
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		} // for문
		
		// 한 마리의 말이 경주가 끝나면 등수를 구해서 rank에 설정한다.
		currentRank++;
		rank = currentRank;
	}
	
}

/*
	- 경기 중 중간 중간에 각 말들의 위치를 나타내시오.
		예)
		01번말 : ----->---------------------------------
		02번말 : --->-----------------------------------
		...
		10번말 : ------>--------------------------------
*/
// 경기중 말의 현재 위치를 출력하는 쓰레드
class GameSate extends Thread{
	private Horse[] ho;
	
	public GameSate(Horse[] ho) {
		this.ho = ho;
	}
	
	@Override
	public void run() {
		while(true) {
			
			// 모든 말들의 경주가 끝났는지 여부를 검사한다.
			if(Horse.currentRank==ho.length) {
				break;
			}
			
			// 빈 줄 출력하기
			for(int i=1; i<=15; i++) {
				System.out.println();
			}
			
			
			for(int i=0; i<ho.length; i++) {
				System.out.print(ho[i].getHorseName() + " : ");
				
				for(int j=1; j<=50; j++) {
					if(ho[i].getPosition()==j) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}






