package ssafy.com.BOJ.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1913_S3_달팽이 {
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()); //생성할 배열 크기의 길이 N 입력
		int target = Integer.parseInt(in.readLine()); //찾고자하는 자연수 입력
		
		int map[][] = new int[N][N]; //NxN 2차원 배열 생성
		int targetPos[] = new int[2]; //target 위치 저장 배열
		
		//탐색을 위한 delta 배열 생성
		int[] dr = {-1,0,1,0}; //상 우 하 좌
		int[] dc = {0,1,0,-1};
		int dirIdx = 0; //방향 선택 인덱스
		
		//달팽이 배열 만들기 위한 변수 선언
		int curR = (int)N/2; //시작 행, 중앙
		int curC = (int)N/2; //시작 열, 중앙
		int len = 1; //숫자가 저장되는 최대 길이, 방향이 2번 바뀔때마다 1씩 증가.
		int cnt = 0; //숫자가 배열에 저장될 때 마다 cnt++ (숫자가 len과 같아지면 방향이 바뀌고, 다시 cnt를 0부터 카운팅)
		int dirChangeCnt = 0; //방향이 바뀌는 수 (2번 바뀌면 길이가 1씩 증가한다.)
		
		for(int i = 1;i<=N*N;i++) { //i가 N*N이 될 때 까지 반복
			if(i == target) { //i가 target이면 위치 저장
				targetPos[0] = curR+1;
				targetPos[1] = curC+1;
			}
			map[curR][curC] = i; //달팽이 배열에 i 저장
			cnt++; //배열에 i를 저장했으니 cnt값 증가
			
			curR += dr[dirIdx]; //방향에 따른 행 변화
			curC += dc[dirIdx]; //방향에 따른 열 변화

			if(cnt == len) { //만약 cnt가 숫자가 저장되는 최대 길이와 같다면
				dirIdx = (dirIdx+1)%4; //방향 변환
				cnt = 0; //cnt 0으로 갱신
				dirChangeCnt++; //방향 변화 횟수 증가
			}
			if(dirChangeCnt==2) { //방향이 2번 바뀌었다면
				len++; //숫자가 달팽이 배열에 저장되는 수 증가
				dirChangeCnt=0; //방향 변화 횟수를 0으로 갱신
			}
		}
		
		for(int i = 0;i<N;i++) { //달팽이 배열 출력
			for(int j = 0;j<N;j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		
		sb.append(targetPos[0]).append(" ").append(targetPos[1]); //찾고자 하는 자연수의 값 위치 출력
		System.out.println(sb);
	}
	
}
