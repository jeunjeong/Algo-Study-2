package week04;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_21318_s1_피아노체조 {
	static StringBuilder sb = new StringBuilder();
	static int N, Q;
	static int[] diff, sum;

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		// 난이도
		diff = new int[N];
		// 난이도가 쉬워져 실수가 발생하는 부분의 수에 대한 누적합 배열
		sum = new int[N];

		String[] s = in.readLine().split(" ");
		diff[0] = Integer.parseInt(s[0]);
		sum[0] = 0;
		for (int i = 1; i < N; i++) {
			diff[i] = Integer.parseInt(s[i]);
			
			// 이전에 계산한 누적합을 가져오고 이번에 실수가 발생했다면 +1
			sum[i] = sum[i - 1];  
			if (diff[i] < diff[i - 1])
				sum[i]++;
		}
		Q = Integer.parseInt(in.readLine());

		for (int t = 0; t < Q; t++) {
			s = in.readLine().split(" ");
			int x = Integer.parseInt(s[0]) - 1;
			int y = Integer.parseInt(s[1]) - 1;

			// 실수가 발생하는 범위를 누적합들의 차로 구함
			sb.append((sum[y] - sum[x]) + "\n");
		}
		System.out.println(sb);
	}
}