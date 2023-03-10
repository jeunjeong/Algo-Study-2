package week04;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_15651_s3_N과M3 {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] num;

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] s = in.readLine().split(" ");

		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		num = new int[M];

		perm(0);

		System.out.println(sb);
	}

	// 중복이 있는 순열, 수업시간에 배운 것과 같음
	static void perm(int cnt) {
		if (cnt == M) {
			for (int a : num)
				sb.append(a + " ");
			sb.append("\n");
			return;
		}
		for (int i = 0; i < N; i++) {
			num[cnt] = i + 1;
			perm(cnt + 1);
		}
	}
}