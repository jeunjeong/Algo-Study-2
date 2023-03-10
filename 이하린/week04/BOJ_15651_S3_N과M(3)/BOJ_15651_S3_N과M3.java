package ssafy.com.study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651_S3_N과M3 {
	
	/**
	 * 백트래킹 문제
	 * 메모리 : 68476 KB 시간 : 484 ms
	 * 중복 순열 코드 수업시간에 배웠던 것처럼 짜니까 바로 통과되었다 ... 
	 */
	
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	
	private static int numbers[];
	
	public static void main(String[] args) throws Exception{
		
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		
		/**
		 * 1부터 N까지 자연수 중에서 M개를 고른 수열
		 * 같은 수를 여러 번 골라도 된다.
		 */
		backtracking(0);
		
		System.out.println(sb);
	}

	private static void backtracking(int cnt) {
		//기저 조건
		if(cnt == M) { //M개 다 고름
			for(int num : numbers) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		//유도조건
		for(int i=1;i<=N;i++) {
			/*
			 * 1부터 N까지 자연수를 고르는데 같은 수를 여러번 골라도 되니까
			 * 방문 체크 따로 해주지 않아도 된다.
			 */
			numbers[cnt] = i;
			backtracking(cnt+1);
		}
		
	}
	
}
