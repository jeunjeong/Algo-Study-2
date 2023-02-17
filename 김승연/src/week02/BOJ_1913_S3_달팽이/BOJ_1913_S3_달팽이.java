package week02.BOJ_1913_S3_달팽이;

import java.util.Scanner;

public class BOJ_1913_S3_달팽이 {
	static int[][] map;		//숫자를 넣을 이차원 배열
	static int num = 1;		//배열에 넣을 숫자
	
	static int target;		//입력받은 좌표의 target 값
	static int targetR = 0;	//입력받은 값 좌표 row
	static int targetC = 0;	//입력받은 값 좌표 col
	
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); //입력이 두 개이므로 그냥 scanner 이용
		
		//input
		int n = sc.nextInt();
		target = sc.nextInt();
		
		//logic
		map = new int[n][n];	//입력받은 크기로 배열 할당
		
		int r = n / 2;			//가운데부터 시작하여 1부터 n^2까지 채움
		int c = n / 2;
		boolean preR = false;	//이전에 row 방향으로 움직였는가?
		
		int val = 1;			//row 또는 col 방향으로 증감하는 정도
		int sign = -1;			//row 또는 col 방향으로 증가 또는 감소
		
		while(true) {
			//이전에 R방향으로 움직임 => 이번에 C방향으로 움직임
			if (preR) { 
				if (val == n) break;	//종료조건
				
				for (int i = val; i > 0; i--){	//총 val만큼 한 칸씩움직임
					setMap(r, c);				//map[r][c]에 값 대입
					c += sign;					//sign에 따라 증가or감소
				}
				val++;							//증감 정도 증가
				preR = false;					//col 방향 움직임
			}
			//이전에 C방향으로 움직임 => 이번에 R방향으로 움직임
			else {		
				for (int i = val; i > 0; i--){
					setMap(r, c);
					r += sign;
				}
				sign *= (-1);					//증감 방향 반대
				preR = true;					//row 방향 움직임
			}
		}
		
		print();
	}
	
	private static void setMap(int r, int c) {
		map[r][c] = num++;
		if (map[r][c] == target) {
			targetR = r;
			targetC = c;
		}
	}
	
	private static void print() { //sb 짱짱!! 시간초과 풀림
		for (int[] line : map) {
			for (int e : line) {
				sb.append(e).append(" ");
			}
			sb.append('\n');
		}
		sb.append((targetR+1) + " " + (targetC+1));
		System.out.println(sb);
	}
}
