package week03;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_21608_g5_상어초등학교 {
	static int N;
	static int[][] area;
	static int[][] favorite;

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		String[] s;
		N = Integer.parseInt(in.readLine());

		// area는 교실, favorite은 좋아하는 학생의 목록
		area = new int[N][N];
		favorite = new int[N * N + 1][4];
		for (int i = 0; i < N * N; i++) {
			s = in.readLine().split(" ");
			int num = Integer.parseInt(s[0]);
			for (int j = 0; j < 4; j++)
				favorite[num][j] = Integer.parseInt(s[j + 1]);	// 학생의 번호를 인덱스로 활용
			findseat(num);	// 해당 학생의 자리를 배정
		}

		// 각 자리에 대해 만족도 계산
		int ans = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				ans += score(i, j);

		sb.append(ans);

		System.out.println(sb);
	}

	static void findseat(int num) {
		// 배정될 위치를 저장할 변수
		int r = -1;
		int c = -1;
		// 4방 탐색을 위한 배열
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		// 각각 조건 1, 조건 2에 대한 최대값
		int maxf = 0;
		int maxb = 0;
		
		// 모든 자리를 탐색하며 조건에 맞는 자리를 탐색
		// 좌상단부터 탐색하여 자동으로 조건 3을 만족함
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 이미 배정된 자리는 넘어감
				if (area[i][j] != 0)
					continue;
				// 현재 자리를 기준으로 상하좌우의 자리에 대해
				// 좋아하는 학생의 자리 수와 빈 자리의 수를 카운트
				int fcnt = 0;
				int bcnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					// 해당 자리가 빈 자리면 빈 자리 수 증가
					if (area[nx][ny] == 0)
						bcnt++;
					// 빈 자리가 아니라면 좋아하는 학생의 자리인지 검사
					else
						for (int f : favorite[num])
							if (area[nx][ny] == f) {
								fcnt++;
								break;
							}
				}
				// 조건 1에 부합하는 자리를 찾은 경우
				if (fcnt > maxf) {
					r = i;
					c = j;
					maxf = fcnt;
					maxb = bcnt;
				}
				// 조건 2에 부합하는 자리를 찾은 경우
				else if (fcnt == maxf && bcnt > maxb) {
					r = i;
					c = j;
					maxb = bcnt;
				}
				// 조건 3에 부합하는 자리를 찾은 경우
				else if (r == -1 && c == -1) {
					r = i;
					c = j;
				}
			}
		}
		// 자리 배정
		area[r][c] = num;
	}

	static int score(int x, int y) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		int fcnt = 0;
		// 주변 자리에 있는 좋아하는 학생의 수를 카운트
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			for (int f : favorite[area[x][y]])
				if (area[nx][ny] == f) {
					fcnt++;
					break;
				}
		}
		// 좋아하는 학생이 없으면 0
		if (fcnt == 0)
			return 0;
		// 좋아하는 학생이 있으면 1,10,100,1000 순서로 만족도가 커지므로 10의 n제곱으로 계산
		else
			return (int) Math.pow(10, fcnt - 1);
	}
}