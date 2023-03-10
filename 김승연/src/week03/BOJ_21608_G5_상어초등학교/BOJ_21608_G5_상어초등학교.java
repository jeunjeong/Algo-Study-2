package week03.BOJ_21608_G5_상어초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_21608_G5_상어초등학교 {
	static class Position{
		int r;
		int c;
		int emptyCnt;
		public Position(int r, int c, int emptyCnt) {
			super();
			this.r = r;
			this.c = c;
			this.emptyCnt = emptyCnt;
		}
		public Position() {
			// TODO Auto-generated constructor stub
		}
		
	}
	static int N;
	static int[][] myClass;
	static int[][] myLike;
	static List<Position> result = new ArrayList<>();
	static Map<Integer, List<Integer>> likeInfo = new HashMap<>();
	static int satisfaction = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		N = Integer.parseInt(in.readLine());
		myClass = new int[N][N]; //반 자리 배치
		myLike = new int[N][N];	//인근에 좋아하는 학생이 있다면 ++ 해주는 배열
		
		//input & logic
		for (int i = 0; i < N*N; i++) {
			//input Person
			String[] line = in.readLine().split(" ");
			int me = Integer.parseInt(line[0]); //아 여기서 0 쓰고 밑에서 또 씀...
			List<Integer> likeLs = new ArrayList<>();
			for (int j = 1; j <= 4; j++) {
				likeLs.add(Integer.parseInt(line[j]));
			}
			likeInfo.put(me, likeLs);
			
			//logic------ 자리를 배치해보자!
			
			//##조건1. 좋아하는 학생이 인접한 칸에 가장 많은 칸으로!
			
			//좋아하는 학생이 앉은 곳 인접한 칸에 myLike+1
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (likeLs.contains(myClass[r][c])) likeAdd(r, c);
				}
			}

			//maxLike , 즉 좋아하는 학생이 인접한 곳에 많이 앉은 자리를 구하자. 만족하는 칸은 전부 result list에 넣는다
			//##조건2. 1을 만족하는 칸이 여러개이면, 인접한 칸 중에 비어있는 칸이 가장 많은 칸으로!!
			//넣으면서 다음 조건인 비어있는 칸도 계산해서 넣는다.
			int maxLike = -1;
			for(int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (myClass[r][c] != 0) continue;
					
					int like = myLike[r][c];
					if (maxLike < like) { //새로운 max
						result.clear(); //리스트 비우고 다시 채움
						maxLike = like;
						result.add(new Position(r, c, getEmpty(r, c)));
					}
					else if (maxLike == like) { //기존 max. 리스트에 추가.
						result.add(new Position(r, c, getEmpty(r, c)));
					}
				}
			}
			
			//좋아하는 애가 안앉ㅇ아있을수도 잇지. 그러면 걍 비어있는 칸 다..
			if (result.isEmpty()) {
				for(int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (myClass[r][c] != 0) continue;
						result.add(new Position(r, c, getEmpty(r, c)));
					}
				}
			}
			
			//##조건3. 2를 만족하는 칸이 여러개인 경우 행의 번호가 가장 작은 칸, 그것도 여러개면 열의 번호가 가장 작은 칸
			//result에는 이제 like가 최대인 자리가 비어있는 자리 수와 함께 입력되어 있음
			//=>뒤부터 돌면서 비어있는 자리가 최대인 자리!!
			int maxEmptyCnt = -1;
			Position maxPos = new Position();
			for (int k = result.size()-1; k >= 0; k--) {
				int cnt = result.get(k).emptyCnt;
				if (maxEmptyCnt <= cnt) { //같아도.. 왜냐면 row col 값떔에(3, 4번 조건)
					maxEmptyCnt = cnt;
					maxPos = result.get(k);
				}
			}
			
			//자리에 앉음
			myClass[maxPos.r][maxPos.c] = me;
			
			//init
			//2차원배열 초기화
			for (int k = 0; k < N; k++) {
				Arrays.fill(myLike[k], 0);
			}
			result.clear();
		}
		
		//배치된 자리에 대한 만족도 구하기
		findSatisfact();
		System.out.println(satisfaction);
	}

	//위, 아래, 좌, 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	private static void findSatisfact() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int me = myClass[r][c];
				int cnt = 0;
				for (int di = 0; di < dr.length; di++) {
					int nr = r + dr[di];
					int nc = c + dc[di];
					//인덱스 체크
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if (likeInfo.get(me).contains(myClass[nr][nc])) cnt++;
				}
				satisfaction += ((cnt == 0)? 0 : Math.pow(10, cnt-1));
			}
		}
	}

	private static int getEmpty(int r, int c) {
		int cnt = 0;
		for (int di = 0; di < dr.length; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			//인덱스 체크
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (myClass[nr][nc] == 0) cnt++;
		}
		return cnt;
	}

	private static void likeAdd(int r, int c) {
		for (int di = 0; di < dr.length; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			//인덱스 체크
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			myLike[nr][nc]++;
		}
	}
	
}
