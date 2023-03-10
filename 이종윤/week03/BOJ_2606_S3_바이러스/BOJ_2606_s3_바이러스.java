package week03;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2606_s3_바이러스 {
	static int N, M;
	static boolean[][] connect;
	static boolean[] v;

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		String[] s;
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());

		// 각 컴퓨터의 연결 상태를 boolean형 2차원 배열로 표현
		// 감염 여부를 저장하기 위한 배열 v
		connect = new boolean[N][N];
		v = new boolean[N];
		for (int i = 0; i < M; i++) {
			s = in.readLine().split(" ");
			// 컴퓨터의 번호가 1번부터 입력되기 때문에 0번부터 시작되도록 1을 줄여서 받음 
			int com1 = Integer.parseInt(s[0]) - 1;
			int com2 = Integer.parseInt(s[1]) - 1;
			connect[com1][com2] = true;
			connect[com2][com1] = true;
		}

		// BFS를 통해 1번 컴퓨터와 연결된 모든 컴퓨터를 탐색
		BFS();

		// 탐색이 종료된 후 감염된 컴퓨터의 수를 계산
		int ans = 0;
		for (int i = 1; i < N; i++)
			if (v[i])
				ans++;

		sb.append(ans);

		System.out.println(sb);
	}

	static void BFS() {
		// BFS를 위한 큐 생성
		Queue<Integer> q = new ArrayDeque<>();
		// 큐에 0번(=1번 컴퓨터)을 넣음
		q.offer(0);
		v[0] = true;
		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			int com = q.poll();
			// 반복문을 돌면서 현재 컴퓨터와 연결된 모든 컴퓨터를 큐에 넣음
			for (int i = 0; i < N; i++) {
				if (i == com || v[i])
					continue;
				if (connect[com][i]) {
					v[i] = true;
					q.offer(i);
				}
			}
		}
	}
}