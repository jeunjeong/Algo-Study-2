package week03.BOJ_16234_G5_인구이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_16234_G5_인구이동 {
	
	static class Position{
		int r;
		int c;
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int N, L, R;
	static int[][] population;
	static int cnt = 0;
	static int[][] visited;
	static boolean isMove;
	static int part = 1; //지역마다 visited 값 다르게 해줘야 인구 제대로 넣을 수 있음
	
	public static void main(String[] args) throws IOException {
		
		int[] line = getLine();
		N = line[0];
		L = line[1];
		R = line[2];
		
		population = new int[N][N];
		for (int i = 0; i < N; i++) {
			population[i] = getLine();
		}
		
		//logic
		/*
		 * for문을 통해 각각의 칸을 보면서
		 * bfs를 통해 인접하면서 인구차가 범위 이내인 것 다 구하고, 칸 수 구하고, 합도 같이 구함.
		 * bfs 끝나면 합을 칸 수로 나눠서 넣어줌.(평균값. =인구이동)
		 * 단, 이미 방문한 곳이면 가지 말 것(visited 이용)
		 * 
		 * 이러한 과정이 몇 번 수행되는지 cnt++;
		 * 해서 이 값을 출력하면 정답!
		 */

		visited = new int[N][N];
		while(true) {
			isMove = false;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					//인구이동이 없었던 지역
					if(visited[r][c] == 0) {
						bfs(new Position(r, c));
						part++;
					}
				}
			}
			if (!isMove) break; //움직임이 없었으면 break;
			
			cnt++;
			
			
			//init
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], 0);
			}
		}
		System.out.println(cnt);
	}

	private static void bfs(Position start) {
		Queue<Position> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.r][start.c] = part; //몇 차례의 인구이동인지..
		
		int adjCount = 1; //start 자신
		int populSum = population[start.r][start.c];
		
		while(!q.isEmpty()) {
			Position pos = q.poll();
			
			//위, 아래, 왼, 오
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			for (int di = 0; di < 4; di++) {
				int nr = pos.r + dr[di];
				int nc = pos.c + dc[di];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)  continue;
				if (visited[nr][nc] == 0 && inRange(pos.r, pos.c, nr, nc)) {
					//방문하지 않았고, 인구 차가 범위 이내면 큐에 추가. visited 설정.
					q.offer(new Position(nr, nc));
					visited[nr][nc] = part;
					
					adjCount++;
					populSum += population[nr][nc];
				}
			}
		}
		
		//bfs 탐색이 끝나면 평균 인구 수로 다 저장. 인구 이동
		if (adjCount > 1) isMove = true;
		move(populSum/adjCount);
	}

	private static void move(int popul) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c] == part) population[r][c] = popul;
			}
		}
	}

	private static boolean inRange(int r, int c, int nr, int nc) {
		int diff = Math.abs(population[r][c] - population[nr][nc]);
		return (diff >= L && diff <= R);
	}

	private static int[] getLine() throws IOException {
		String[] line = in.readLine().split(" ");
		int[] lineInt = new int[line.length];
		for (int i = 0; i < line.length; i++) {
			lineInt[i] = Integer.parseInt(line[i]);
		}
		return lineInt;
	}
}
