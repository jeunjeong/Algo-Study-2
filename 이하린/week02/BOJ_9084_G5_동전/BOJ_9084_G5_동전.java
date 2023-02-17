package harin.java.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9084_G5_동전 {
	
	
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine()); //테스트 케이스의 수 입력 받
		
		for(int tc = 1; tc<=T; tc++) {
			
			int N = Integer.parseInt(in.readLine());//동전의 가짓수 입력
			int coins[] = new int[N]; //동전 개수 만큼 동전 입력받음 
			
			st = new StringTokenizer(in.readLine()); //동전 종류 입력 받음 
			
			for(int i = 0;i<N;i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(in.readLine()); //N가지 동전으로 만들어야 할 금액 입력 
			
			int dp[] = new int[M+1]; //M+1 크기의 dp 베열 생성
			dp[0] = 1; //dp[0] 1로 초기화, 만약 2원 동전으로 2원을 만든다면 가짓수가 1개이니까, 이 조건을 만족시키기 위해서
			
			for(int i=0;i<N;i++) {//동전 개수 만큼 반복 
				//만약 5원을 사용해서 금액을 만들려고 할때, 5원보다 작은 1,2,3,4원 같은 경우는 만들 수가 없음.
				//그렇기 때문에 dp를 계산할 반복문은 M금액을 만드려고 도전하고 있는 현재 동전부터 시작한다.
				for(int j=coins[i]; j<=M;j++) { 
					dp[j] += dp[j-coins[i]]; //점화식
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		System.out.println(sb);
		
	}
	
}
