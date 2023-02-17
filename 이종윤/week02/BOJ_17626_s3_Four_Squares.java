package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17626_s3_Four_Squares {
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		// 동적 프로그래밍으로 계산할 배열, squ[n]은 합이 n과 같게 되는 제곱수들의 최소 개수를 의미
		int[] squ = new int[N + 1];

		// 1~N까지 합이 i와 같게 되는 제곱수들의 최소 개수를 구해 나감
		for (int i = 1; i <= N; i++) {
			// 초기값 세팅
			int num = 1;
			squ[i] = Integer.MAX_VALUE;

			// num의 제곱이 i보다 작은 모든 num에 대해서 제곱수들의 최소 개수를 구함
			while (num * num <= i) {
				// 정수 i는 (i-num^2) + num^2이므로
				// 제곱수의 개수는 합이 i-num^2인 제곱수의 개수 +1
				squ[i] = Math.min(squ[i - num * num] + 1, squ[i]);
				num++;
			}
		}

		// squ[N]은 합이 N과 같게 되는 제곱수들의 최소 개수
		int ans = squ[N];
		sb.append(ans);
		System.out.println(sb);
	}
}