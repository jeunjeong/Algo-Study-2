package daily07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_21608_상어초등학교 {

	private static StringTokenizer st;
	private static int[][] classRoom; //교실 배열
	private static int N; //교실 한 변의 길이
	private static int likeFriends[][]; //학생들의 좋아하는 친구 저장
	private static int order[]; //자리 정하는 순서 저장
	private static int [] dr = {-1, 1, 0, 0}; //상하좌우
	private static int [] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine()); //N 입력

		order = new int[N*N]; //자리 정하는 순서 저장
		likeFriends = new int[N*N + 1][4]; //좋아하는 친구 저장

		for(int i = 0;i<N*N;i++) { //순서, 좋아하는 친구 리스트 입력
			st = new StringTokenizer(in.readLine());
			order[i] = Integer.parseInt(st.nextToken());

			for(int j=0;j<4;j++) {
				likeFriends[order[i]][j] = Integer.parseInt(st.nextToken());
			}
		}

		classRoom = new int[N*N][N*N];  //교실 2차원 배열 생성

		for(int i = 0; i<N*N; i++) { //N*N번 반복 (모든 학생의 자리 정하기)

			List<seatInfo> seatInfos = new ArrayList<seatInfo>(); //seatInfo 클래스를 담는 ArrayList 생성

			//1번 조건, 2번 조건 
			for(int j=0;j<N;j++) { //2차원 배열 탐색을 위한 2중 반복문, 조건에 맞는 자리를 찾는다.
				for(int k=0;k<N;k++) {
					if(classRoom[j][k] != 0) continue; //만약 빈자리가 아니라면 밑 코드를 실행하지 않고 건너뛴다.

					int likeFriendsCnt = 0; //해당 자리를 선택하게 된다면 인접한 자리에 좋아하는 친구가 몇 명인지 세는 변수
					int emptySeatCnt = 0; //해당 자리를 선택하게 된다면 인접한 자리에 빈자리가 몇 개인지 세는 변수

					for(int l = 0;l<4;l++) { //인접한 자리는 최대 상, 하, 좌, 우 즉 4개가 될 수 있으므로 4번 반복
						int nr = j + dr[l]; //delta 배열 사용하여 인접 자리 탐색
						int nc = k + dc[l];

						if((0<=nr && nr<N) && (0<= nc && nc<N)) { //nr, nc이 0보다 크거나 같고 N보다 작아야지 ArrayIndexOutof 예외가 발생하지 않는다.
							//인접한 칸에 좋아하는 학생 세기
							for(int likeFriend : likeFriends[order[i]]) { //만약 좋아하는 친구가 있다면?
								if(classRoom[nr][nc] == likeFriend) likeFriendsCnt++; //cnt++
							}
							//인접한 칸에 비어있는 칸 세기, 만약 빈 자리가 있다면?
							if(classRoom[nr][nc] == 0) emptySeatCnt++; //cnt ++
						}
					}
					seatInfos.add(new seatInfo(j, k, likeFriendsCnt, emptySeatCnt)); //자리 정보를 리스트에 추가해준다.
				}
			}
			
			Collections.sort(seatInfos); //좋아하는 친구 수와 빈자리 수는 내림차순으로, 행과 열은 오름차순으로 
			
			seatInfo seat = seatInfos.get(0); //제일 앞에 정렬된 seatInfo 가져오기
			classRoom[seat.r][seat.c] = order[i]; //제일 앞에 정렬된 seatInfo의 r, c를 해당 순서 학생의 자리로 선정한다.

		}
		
		// 만족도 계산
		int ans = 0;
		for(int i =0;i<N;i++) {
			for(int j=0;j<N;j++) {
				ans += getSatisfaction(i, j);
			}
		}
		
		System.out.println(ans);

	}
	
	//r과 c 자리의 만족도 계산 함수
	private static int getSatisfaction(int r, int c) { 
		
		int studentNumber = classRoom[r][c]; //해당 자리의 학생 번호 가져오기
		int likeFriendsCnt = 0;
		
		for(int l = 0;l<4;l++) { //탐색을 위한 for문
			int nr = r + dr[l];
			int nc = c + dc[l];

			if((0<=nr && nr<N) && (0<= nc && nc<N)) {
				//인접한 칸에 좋아하는 학생 세기
				for(int likeFriend : likeFriends[studentNumber]) { //studentNumber 학생의 좋아하는 친구 리스트 가져오기
					if(classRoom[nr][nc] == likeFriend) likeFriendsCnt++; //탐색한 학생과 좋아하는 친구의 번호가 같다면 cnt ++
				}
			}
		}
		
		switch(likeFriendsCnt) { //좋아하는 친구의 수에 따른 만족도 리턴
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 10;
		case 3:
			return 100;
		case 4:
			return 1000;
		}
		return 0;
		
	}

}

class seatInfo implements Comparable<seatInfo>{ //r,c 값을 편하기 사용하기 위해 클래스 생성
	int r; //행 번호
	int c; //열 번호
	int likeFriendsCnt; //좋아하는 친구 수 
	int emptySeatCnt; //빈 자리 수

	seatInfo(int r, int c, int likeFirendsCnt, int emptySeatCnt) { //생성자
		this.r = r;
		this.c = c;
		this.likeFriendsCnt = likeFirendsCnt;
		this.emptySeatCnt = emptySeatCnt;
	}

	@Override
	public int compareTo(seatInfo o) { //정렬을 위한 compareTo 함수
		if(o.likeFriendsCnt > this.likeFriendsCnt) { //내림차순
			return 1;
		} else if(o.likeFriendsCnt < this.likeFriendsCnt){
			return -1;
		} else {
			if(o.emptySeatCnt > this.emptySeatCnt) { //내림차순
				return 1;
			}
			else if(o.emptySeatCnt < this.emptySeatCnt) {
				return -1;
			}
			else {
				if(o.r > this.r) { //오름차순
					return -1;
				} else if(o.r < this.r) {
					return 1;
				} else {
					if(o.c > this.c) { //오름차순
						return -1;
					}
					else {
						return 1;
					}
				}
			}
		}
	}
}