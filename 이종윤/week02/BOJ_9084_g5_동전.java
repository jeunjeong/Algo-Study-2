package week02;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9084_g5_동전 {
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		// 테스트케이스 수 입력
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {

			// 동전의 개수, 값 입력
			int N = Integer.parseInt(in.readLine());
			int[] coins = new int[N];
			String[] s = in.readLine().split(" ");
			for (int i = 0; i < N; i++)
				coins[i] = Integer.parseInt(s[i]);

			// 동적 프로그래밍으로 계산할 배열, DP[n][m]은 n종류의 동전으로 m원을 만드는 조합의 수를 의미
			// 이 때 DP[n][m]은 n번째 동전을 하나 이상 포함함
			int M = Integer.parseInt(in.readLine());
			long[][] DP = new long[N][M + 1];

			// 1~M까지 동전으로 금액을 맞추는 경우의 수
			for (int i = 1; i <= M; i++)
				// 사용할 동전의 종류를 늘려가며 금액 i를 맞춤
				for (int j = 0; j < N; j++) {
					// 동전이 오름차순으로 입력되므로 i보다 동전값이 커지면 금액에 맞출 수 없으니 중단 
					if (coins[j] > i)
						break;
					// i가 동전값과 같으면 동전 하나로 금액을 맞출 수 있음
					if (coins[j] == i)
						DP[j][i]++;
					// k번째 동전을 포함해서 금액 i를 맞추려면 (i-k번째 동전값)원에서 k번째 동전 값을 더한 것과 같음
					for (int k = 0; k <= j; k++)
						DP[j][i] += DP[k][i - coins[j]];
				}

			// 최종 결과는 금액 M을 만드는 모든 경우의 수의 합
			long ans = 0;
			for (int i = 0; i < N; i++)
				ans += DP[i][M];
			sb.append(ans + "\n");

		}
		System.out.println(sb);
	}
}