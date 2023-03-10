package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ_16234_g5_인구이동 {
	static int N, L, R;
	static Country[][] map;
	static boolean[][] v;

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		String[] s = in.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		L = Integer.parseInt(s[1]);
		R = Integer.parseInt(s[2]);

		map = new Country[N][N];
		for (int i = 0; i < N; i++) {
			s = in.readLine().split(" ");
			for (int j = 0; j < N; j++)
				map[i][j] = new Country(i, j, Integer.parseInt(s[j]));
		}

		// 인구의 이동이 없을 때까지 반복
		int day = 0;
		while (true) {
			int moved = 0; // 하루 인구 이동량
			v = new boolean[N][N]; // 각 국가의 이동 완료 여부

			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					// 이동이 완료된 국가는 넘어감
					if (v[i][j])
						continue;

					// i, j 좌표에 위치한 국가가 속한 연합의 목록을 BFS를 이용해 탐색
					List<Country> union = BFS(map[i][j]);
					int avg = 0;

					// 연합 내 인구 평균을 계산
					for (Country c : union)
						avg += c.people;
					avg /= union.size();

					// 인구 이동
					for (Country c : union) {
						moved += Math.abs(c.people - avg); // 이동량을 moved에 누적
						c.people = avg;
					}
				}
			// 이동량이 없으면 중단
			if (moved == 0)
				break;
			day++;
		}

		int ans = day;
		sb.append(ans);

		System.out.println(sb);
	}

	static List<Country> BFS(Country country) {
		List<Country> union = new ArrayList<>(); // 연합의 목록을 저장할 리스트
		Queue<Country> q = new ArrayDeque<>(); // BFS를 위한 큐

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		q.offer(country);
		v[country.x][country.y] = true;

		// BFS를 통해 연합의 조건에 맞는 주변 국가들을 탐색하여 연합 리스트에 추가
		while (!q.isEmpty()) {
			Country c = q.poll();
			union.add(map[c.x][c.y]);

			for (int i = 0; i < 4; i++) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || v[nx][ny])
					continue;

				int diff = Math.abs(map[c.x][c.y].people - map[nx][ny].people);
				if (diff >= L && diff <= R) {
					q.offer(map[nx][ny]);
					v[nx][ny] = true;
				}
			}
		}
		return union;
	}
}

class Country {
	int x;
	int y;
	int people;

	public Country(int x, int y, int people) {
		this.x = x;
		this.y = y;
		this.people = people;
	}
}