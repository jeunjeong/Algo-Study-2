package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2512_s3_예산 {
	static int N, budget;
	static int[] requests;
	static int[] sums;

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());

		// 예산요청을 입력 받음
		requests = new int[N];
		String[] s = in.readLine().split(" ");
		for (int i = 0; i < N; i++)
			requests[i] = Integer.parseInt(s[i]);
		// 입력받은 예산요청을 오름차순으로 정렬
		Arrays.sort(requests);

		budget = Integer.parseInt(in.readLine());

		// i번째 예산요청보다 큰 금액에 대해서는 i번째 예산요청만큼만 예산을 배정해서 국가예산을 초과하는지 검사
		// 몇번째 예산부터 예산 상한을 적용해야 하는지 탐색
		int idx = -1;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < N; j++)
				sum += requests[j > i ? i : j];
			if (sum > budget) {
				idx = i;
				break;
			}
		}
		// idx가 -1이면 모든 예산을 요청만큼 배정해도 국가예산을 초과하지 않았기 때문에 가장 큰 예산 요청을 출력
		if (idx == -1)
			sb.append(requests[N - 1]);
		// 상한을 적용해야할 예산들을 상한을 1씩 줄여가며 국가예산 이내가 될 때까지 상한을 감소시킴
		else {
			// 예산 상한보다 작은 예산요청들의 합
			int sum = 0;
			for (int i = 0; i < idx; i++)
				sum += requests[i];
			// 예산 상한
			int limit = requests[idx] - 1;
			// 총 예산요청이 국가예산 이내가 될 때까지 상한을 감소
			while (sum + limit * (N - idx) > budget)
				limit--;
			sb.append(limit);
		}

		System.out.println(sb);
	}
}