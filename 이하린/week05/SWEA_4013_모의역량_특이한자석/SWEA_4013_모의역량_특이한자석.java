package ssafy.com.역량대비;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4013_특이한자석 {
	
	//완전 유사한 백준 문제 https://www.acmicpc.net/problem/14891
	/*
	 * 메모리 : 19132kb 실행시간 : 110ms
	 * 풀다가.. 넘 복잡해져서 회전 방향 세팅하는 부분만(로직..?) 인터넷 참고했습니다 ... 
	 */

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int magnet[][]; //자석 배열
	static int dir[]; //회전 방향 세팅
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine()); //TC 입력
		
		for(int tc = 1;tc<=T;tc++) {
			sb.append('#').append(tc).append(' ');
			
			int roCnt = Integer.parseInt(in.readLine()); //회전 정보 개수
			
			magnet = new int[5][8]; //자석 배열 초기화
			
			for(int i=1;i<=4;i++) { //자석 정보 입력
				st = new StringTokenizer(in.readLine());
				for(int j=0;j<8;j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			//회전 정보 입력
			for(int i=0;i<roCnt;i++) {
				st = new StringTokenizer(in.readLine());
				dir = new int[5]; //0 : 회전, -1 : 반시계 1: 시계
				int magNo = Integer.parseInt(st.nextToken());
				int roDir = Integer.parseInt(st.nextToken());
				
				dir[magNo] = roDir;
				setDirLeft(magNo-1); //magNo 기준 왼쪽 회전방향 세팅
				setDirRight(magNo+1); //magNo 기준 오른쪽 회전방향 세팅
				
				//회전 시작
				for(int j=1;j<=4;j++) {
					switch(dir[j]) {
					case 0: //만약 회전 방향이 0 이면 회전X
						break;
					case 1: //만약 회전 방향이 1이면 시계 방향 회전
						Rotation(j, 1);
						break;
					case -1: //만약 회전 방향이 -1이면 반시계 방향 회전
						Rotation(j, -1);
						break;
					}
				}
				
			}
			
			//점수 계산
			int score = 0;
			for(int i=1;i<=4;i++) {
				if(magnet[i][0] == 1) {
					score += Math.pow(2, i-1);
				}
			}
			
			sb.append(score).append('\n');

		}
		
		//정답 출력
		System.out.println(sb);
	}
	
	//magNo 기준 왼쪽 자석 회전 방향 세팅
	private static void setDirLeft(int magNo) {
		if(magNo == 0) return;
		
		if(dir[magNo+1] == 0) dir[magNo] = 0; //전 자석이 회전이 없다면 얘도 회전X
		//인접한 두 날의 자성이 같다면 회전X
		else if(magnet[magNo+1][6] == magnet[magNo][2]) dir[magNo] = 0;
		//아니라면 해당 magNo 자석의 회전방향을 이전 자석과 다른 회전 방향으로 지정
		else dir[magNo] = (dir[magNo+1] == 1)? -1 : 1;
		
		setDirLeft(magNo-1);
		
	}
	
	//magNo 기준 오른쪽 자석 회전 방향 세팅
	private static void setDirRight(int magNo) {
		if(magNo == 5) return;
		
		if(dir[magNo-1] == 0) dir[magNo] = 0;
		else if(magnet[magNo-1][2] == magnet[magNo][6]) dir[magNo] = 0;
		else dir[magNo] = (dir[magNo-1] == 1)? -1 : 1;
		
		setDirRight(magNo+1);		
	}

	
	//회전 함수
	private static void Rotation(int magNo, int roDir) {
		
		int temps[] = new int[8];
		
		switch(roDir) { //1이면 시계방향 회전, -1이면 반시계방향 회전
		case 1:
			int temp= magnet[magNo][7];
			
			for(int i=1;i<8;i++) {
				temps[i] = magnet[magNo][i-1];
			}
			temps[0] = temp;
			break;
		case -1:
			int temp2 = magnet[magNo][0];
			
			for(int i=6;i>=0;i--) {
				temps[i] = magnet[magNo][i+1];
			}
			temps[7] = temp2;
			break;
		}
		
		for(int i=0;i<8;i++) {
			magnet[magNo][i] = temps[i];
		}
	}

}
