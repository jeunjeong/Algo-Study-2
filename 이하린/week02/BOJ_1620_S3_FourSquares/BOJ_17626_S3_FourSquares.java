package ssafy.com.study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_17626_S3_FourSquares {
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine()); //n입력
		int dp[] = new int[n+1]; //dp 배열 생성
		List<Integer> squares = new ArrayList<Integer>(); //제곱수를 저장하는 arrayList

		/**
		 * 점화식 min(dp[i - 제곱수] + 1)은
		 * 그냥 수를 1부터 적어보며 규칙을 찾으려고 하다가 찾게되었습니다!
		 */
		
		for(int i = 1; i<= n;i++) {//n까지 반복
			if(Math.sqrt(i) == Math.floor(Math.sqrt(i))) { //만약 i가 제곱수라면
				squares.add(i); //square 리스트에 추가해주기
				dp[i] = 1; //제곱수라면 dp[i] = 1이다.
			}
			else { //제곱수가 아니라면
				int min = Integer.MAX_VALUE; //작은 수를 구하기 위한 min 변수
				for(int num: squares) {
					if(min > dp[i - num] + 1) //만약 min이 dp[i-num]+1 보다 크다면 min 변수에 해당 값 할당
						min = dp[i-num]+1;
				}
				dp[i] = min; //dp[i]에 min 값 할당
			}
		}
		
		System.out.println(dp[n]);
		
	}
	
}
