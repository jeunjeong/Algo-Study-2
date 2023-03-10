package week04;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2447_g5_별찍기10 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static boolean[][] pattern;

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		// N*N 행렬에 미리 별을 찍어야 할 자리를 저장
		pattern = new boolean[N][N];

		// 배열 채우기
		part(0, 0, N);
		
		// 배열을 순회하면서 공백과 별을 구분하여 출력
		for (boolean[] b : pattern) {
			for (boolean a : b) {
				if (a)
					sb.append("*");
				else
					sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void part(int x, int y, int size) {
		// 가장 작은 사이즈일 때 가운데만 남기고 별로 저장
		if (size == 3) {
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++) {
					if (i == 1 && j == 1)
						continue;
					pattern[x + i][y + j] = true;
				}
			return;
		}

		// 더 작은 사이즈로 나누어 각각을 가운데가 비어있는 모양으로 만듦
		// 큰 사이즈에서 공백에 해당하는 영역들은 작은 사이즈로 나누지 않으므로 해당 영역 전체가 공백으로 남음
		int next = size / 3;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1)
					continue;
				part(x + i * next, y + j * next, next);
			}
	}
}