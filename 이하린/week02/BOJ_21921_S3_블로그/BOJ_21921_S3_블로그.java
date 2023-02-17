package ssafy.com.BOJ.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21921_S3_블로그 {
	
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//입력 N, X 받음
		//N : 블로그를 시작한 지 지난 일수, X: 기간
		st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		//합 배열 입력, 인덱스 1부터 자신까지 구간 합을 자신의 인덱스에 저장
		int accum[] = new int[N+1];
		st = new StringTokenizer(in.readLine());
		for(int i=1;i<=N;i++) {
			accum[i] = accum[i-1] + Integer.parseInt(st.nextToken());
		}
		
		//accum[N]==0, 즉 최대 방문자수가 0명이라면 SAD 출력
		if(accum[N]==0) {
			sb.append("SAD");
		}
		else {//아니라면
			int max = Integer.MIN_VALUE; //최대 방문자수를 구하기 위해서 max 변수 사용
			int cnt = 1;
			
			for(int i = 1;i<=N-X+1;i++) { //i는 1부터 N-X+1까지 반복
				int startIdx = i;
				int endIdx = i+X-1;
				
				int visitCnt = accum[endIdx] - accum[startIdx-1];
				if(visitCnt>max) {
					max = visitCnt; //값 비교를 통해 최대 방문자 수 저장
					cnt=1; //최대 방문자 수가 바뀌었다면 다시 1로 갱신
				}
				else if(max == visitCnt) cnt++; //만약 최대 방문자 수가 또 나왔다면 cnt++
			}
			
			sb.append(max).append("\n").append(cnt);
		}
		
		System.out.println(sb);
		
	}
	
}
