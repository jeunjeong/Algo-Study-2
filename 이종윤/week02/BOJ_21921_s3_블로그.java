package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_21921_s3_블로그 {
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		// N, X 입력
		int N, X;

		String[] s = in.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		X = Integer.parseInt(s[1]);

		// 하루 방문자 수 입력
		int[] visit = new int[N];
		s = in.readLine().split(" ");
		for (int i = 0; i < N; i++)
			visit[i] = Integer.parseInt(s[i]);

		int count = 0;
		int max_visit = 0;
		int cur_visit = 0;

		// 초기값, 1일부터 X일동안의 방문자 수
		for (int i = 0; i < X; i++)
			cur_visit += visit[i];
		max_visit = cur_visit;
		count = 1;

		// 1일 이후 X일 동안의 방문자 수 비교
		// 기존에 구했던 초기값에서 가장 빠른 날짜를 빼고 가장 마지막 날짜의 다음날 방문자 수를 더함
		for (int i = 0; i < N - X; i++) {
			cur_visit -= visit[i];
			cur_visit += visit[i + X];
			// 누적 방문자 수가 최대 방문자 수와 같으면 기간을 증가
			if (max_visit == cur_visit)
				count++;
			// 최대 방문자 수가 새로 갱신되면 기간을 1로 초기화
			else if (max_visit < cur_visit) {
				max_visit = cur_visit;
				count = 1;
			}
		}

		// 최대 방문자 수가 0보다 크면 방문자 수와 기간 출력
		if (max_visit > 0)
			sb.append(max_visit + "\n" + count);
		// 최대 방문자 수가 0이면 SAD 출력
		else
			sb.append("SAD");

		System.out.println(sb);
	}
}