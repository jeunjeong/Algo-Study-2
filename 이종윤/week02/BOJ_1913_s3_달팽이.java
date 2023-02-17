package week02;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1913_s3_달팽이 {
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		// N, M 입력
		int N, M;

		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());

		int[][] snail = new int[N][N];

		// 배열에서 M의 좌표를 저장할 변수
		int M_x = -1;
		int M_y = -1;

		// x, y의 시작점, 배열의 중앙
		int x = N / 2;
		int y = N / 2;

		// 이동할 방향에 대한 배열
		int[] xs = { -1, 0, 1, 0 };
		int[] ys = { 0, 1, 0, -1 };

		int num = 1;	// 배열에 채울 값
		int dir = 0;	// 이동할 방향
		int count = 2;	// 직선 이동 거리
		
		// num이 1씩 증가하며 N*N 크기의 배열을 채워나감
		while (num <= N * N) {
			// 배열을 달팽이 모양으로 채워나갈 때 이동 거리는 2번 회전할 때마다 1씩 증가함
			// 현재 방향으로 일정 거리만큼 직선으로 이동하면서 값을 채움
			for (int i = 0; i < count/2; i++) {
				// 채울 값이 M이면 해당 위치를 저장
				if (num == M) {
					M_x = x+1;
					M_y = y+1;
				}
				// 배열을 채우고 다음 위치로 이동
				snail[x][y] = num++;
				x += xs[dir];
				y += ys[dir];
			}
			// 직선으로 count/2만큼 움직이며 값을 채우기 때문에
			// 이동 방향은 직선 이동을 한 번할 때마다 바뀌지만 이동 거리는 두 번할 때마다 1씩 증가
			count += 1;
			dir = (dir + 1) % 4;
		}
		
		// 완성된 배열 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sb.append(snail[i][j] + " ");
			sb.append("\n");
		}
		// M의 좌표 출력
		sb.append(M_x + " " + M_y);

		System.out.println(sb);
	}
}