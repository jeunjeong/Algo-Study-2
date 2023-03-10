package com.ssafy.daily13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21318_S1_피아노체조 {
	
	/**
	 * 누적합
	 * 메모리 : 61560KB, 시간 : 728ms
	 * 처음에 누적합으로 안 풀고 그냥 앞 뒤 비교해서 mistakeCnt++ 해주니까 시간초과가 났다.
	 * 그래서 실수의 수를 누적하는 mistake 배열을 생성해서 값을 구함. 
	 */
	
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine()); //악보의 개수

		//편의를 위해서 인덱스 1부터 시작
		int level[] = new int[N+1];
		int mistake[] = new int[N+1];
		
		st = new StringTokenizer(in.readLine());
		for(int i=1;i<=N;i++) {
			level[i] = Integer.parseInt(st.nextToken());
		}
		
		/**
		 * 실수의 수를 누적한다.
		 * 만약 전 악보의 난이도가 현재 악보의 난이도보다 높다면? +1
		 * 낮거나 같다면 전 인덱스의 실수 누적을 계속 가져간다.
		 */
		for(int j=2;j<=N;j++) {
			if(level[j-1] > level[j]) {
				mistake[j] = mistake[j-1] + 1;
			}else {
				mistake[j] = mistake[j-1];
			}
		}


		int Q = Integer.parseInt(in.readLine()); //질문 개수


		for(int i=0;i<Q;i++) {
			st = new StringTokenizer(in.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			//y번째 실수 누적에서 x번째 실수 누적을 빼면 답이 나온다.
			int ans = mistake[y] - mistake[x];
			
			sb.append(ans).append("\n");
		}
	System.out.println(sb);
	}
}
