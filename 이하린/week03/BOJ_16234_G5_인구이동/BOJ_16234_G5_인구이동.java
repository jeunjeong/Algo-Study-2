package harin.java.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


//위치를 담는 클래스 생성 
class pos {
	int r;
	int c;

	pos(int r, int c){
		this.r = r;
		this.c = c;
	}
}

public class BOJ_16234_G5_인구이동 {

	private static StringTokenizer st;
	
	private static int N; //땅의 크기 
	private static int L; //두 나라의 인구 차이 제한 
	private static int R;
	
	private static int[][] arr; //나라 배열 
	private static boolean isSelected[][]; //해당 나라를 탐색했는지 여부 확인 
	private static int[] dr = {-1,1,0,0}; //상 하 좌 우
	private static int[] dc = {0,0,-1,1}; 
	private static List<pos> list; //국경선을 통해 이동할 수 있는 나라 위치 저장 

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken()); //땅 크기 
		L = Integer.parseInt(st.nextToken()); //인구 수 제한 (l <= R) 
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		isSelected = new boolean[N][N];

		//각 나라의 인구 수 배열에 저장 
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		
		while(true) {
			boolean isPossible = false;
			isSelected = new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!isSelected[i][j]) { //선택되지 않은 곳만 탐색 
						/**
						 * BFS를 통해 국경선을 공유하는 두 나라의 인구 차이가
						 * L명 이상 R명 이하인 곳을 탐색하고
						 * 연합을 이루고 있는 각 칸의 인구 수의 합을 더해 return 한다.
						 * 만약 연합을 이루는 나라가 1개보다 많다면
						 * 연합을 이루고 있는 각 칸의 인구 수를 (연합의 인구 수) / 연합을 이루고 있는 칸의 개수로 만든다.
						 * 만약 인구 이동이 일어날 수 있는 조건을 만족하지 않는다면 (isPossible == false)
						 * break를 통해 해당 반복문을 종료한다. 
						 */
						int populationSum = bfs(new pos(i,j)); 
						if(list.size() > 1) {
							changePopulation(populationSum);
							isPossible = true;
						}
					}
				}
			}
			if(!isPossible) break;
			else ans++; //인구 이동을 했으면 ans 값 1씩 증가 
		}
		
		System.out.println(ans);


	}
	
	/**
	 * 연합을 이루고 있는 각 칸의 인구수를
	 * 연합의 인구 수 / 연합을 이루고 있는 칸의 개수로 변경. 
	 */
	private static void changePopulation(int population) {
		int avg = population / list.size();
		for(pos po : list) {
			arr[po.r][po.c] = avg;
		}
		
	}
	
	
	/**
	 * 인구 이동이 가능한 나라들을 탐색하기 위한 bfs 
	 */
	private static int bfs(pos pos) {
		//bfs를 위한 Queue 생성
		Queue<pos> queue = new ArrayDeque<>();
		//국경선을 통해 이동할 수 있는 나라의 pos 를 저장하는 list 
		list = new ArrayList<>();
		
		queue.offer(new pos(pos.r, pos.c));
		list.add(new pos(pos.r, pos.c));
		isSelected[pos.r][pos.c] = true;
		
		int populationSum = arr[pos.r][pos.c];
		
		while(!queue.isEmpty()) {
			pos temp = queue.poll();
			
			//상하좌우 탐색 
			for(int i=0;i<4;i++) {
				int nr = temp.r + dr[i];
				int nc = temp.c + dc[i];
				
				//nr, nc가 배열 범위에 있고, 탐색된 적이 없다면 
				if((0 <= nr && nr < N) && (0<= nc && nc <N)
						&& (!isSelected[nr][nc])) {
					//국경선을 공유하는 두 나라의 차이 구함 
					int diff = Math.abs(arr[nr][nc] - arr[temp.r][temp.c]);
					//두 나라의 차이가 L이상 R이하라면 
					if((L <= diff) && (diff <= R)){
						isSelected[nr][nc] = true; 
						populationSum += arr[nr][nc]; //인구 수 더해줌
						queue.offer(new pos(nr, nc));
						list.add(new pos(nr, nc));
					}
				}
			}
		}
		return populationSum;
	}

}
