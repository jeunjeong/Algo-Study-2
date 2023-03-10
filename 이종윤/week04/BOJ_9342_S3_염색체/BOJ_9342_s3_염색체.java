package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9342_s3_염색체 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[] c;

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		for (int t = 0; t < N; t++) {
			c = in.readLine().toCharArray();
			sb.append((check() ? "Infected!" : "Good") + "\n");
		}
		System.out.println(sb);
	}

	static boolean check() {
		int idx = 0;

		// 첫 문자는 A~F 사이의 문자 중 하나로 시작한다.
		// 그 다음은 A가 하나 이상 연속으로 나온다. 첫 문자가 A인 경우에는 하나 이상 나온 것으로 취급한다.
		if (c[idx] >= 'A' && c[idx] <= 'F') {
			idx++;
			// 첫 번째 문자나 두 번째 문자부터 A가 연속으로 나오므로 연속으로 나온 만큼 인덱스 이동
			// 첫 번째 문자나 두 번째 문자가 A가 아니라면 조건을 만족하지 않으므로 정지
			if (c[idx] == 'A' || c[idx - 1] == 'A')
				while (c[idx] == 'A')
					idx++;
			else
				return false;
		} else
			return false;

		// A가 연속으로 나온 이후에는 F가 연속으로 나온다.
		if (c[idx] == 'F') {
			idx++;
			while (c[idx] == 'F')
				idx++;
		} else
			return false;

		// F가 연속으로 나온 이후에는 C가 연속으로 나온다.
		if (c[idx] == 'C') {
			idx++;
			while (idx < c.length && c[idx] == 'C')
				idx++;
		} else
			return false;

		// C로 문자열이 마무리 되거나
		// 마지막 앞까지 C로 채워지고 마지막 문자가 A~F 사이의 문자로 마무리되면 모든 규칙을 만족한다.
		if (idx == c.length || (idx == c.length - 1 && c[idx] <= 'F' && c[idx] >= 'A'))
			return true;
		else
			return false;

	}
}