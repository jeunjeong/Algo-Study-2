package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class BOJ_21608_G5_상어초등학교 {
	static int N;
	static int[][] room;
	static Node[][] exnodes;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//학생 넘버가 저장될 배열
		room = new int[N][N];
		
		// 마지막 점수 환산용으로 급하게 추가한 노드 배열...
		// 그냥 위의 int배열 room 을  대체할수 있지만
		// 이대로도 풀리기에 넘어간다....
		exnodes = new Node[N][N];
		
		// 입력되는 학생번호 , 좋아하는 학생의 번호들 을 저장할 노드들의 배열을 선언
		Node[] nodes = new Node[N*N];
		
		// 노드들 입력받기
		for(int i=0;i<N*N;i++) {
			int line[] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			nodes[i] = new Node(line[0] , Arrays.copyOfRange(line, 1, 5));
			//System.out.println(line[0] + " " + Arrays.toString(nodes[i].loved));
		}
		
		
		// SOLOUTIN 부분
		// 노드 N*N개에 대에서 room 배열을 완전탐색한다.
		
		for(int i=0; i<N*N;i++) {
			// 아래 변수들을 노드로 하나 받을걸 그랬다...
			//현재 후보의 인전칸에 좋아하는 학생수
			int max_point = -1;
			//현재 후보의 row , col , 인접빈칸숫자
			int row = -1 , col = -1 , empty_num = -1;
			
			// 이 노드의 학생을 room 배열에 넣어야 한다!!!!!
			Node node = nodes[i];
			
			// room 배열을 돌면서 탐색한다.
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					// 빈칸이 있을떄
					if(room[j][k] == 0) {
						// 인접한 좋아하는 학생의 수를 구한다.
						int close_num = get_close(j,k,node);
						// 인접한 비어있는 칸의 수를 구한다.
						int empty_this = get_empty(j,k);
						
						// 현재후보보다 좋아하는 인접학생이 많으면 대체한다.
						if(close_num > max_point) {
							max_point = close_num;
							row = j;
							col = k;
							empty_num = empty_this;
						}
						else if( close_num == max_point) {
							// 좋아하는 인접학생수가 같을떄
							// 비어있는 인접칸이 많으면 후보지가 된다.
							if(empty_this > empty_num) {
								max_point = close_num;
								row = j;
								col = k;
								empty_num = empty_this;
							}
							else if(empty_this == empty_num) {
								//인접빈칸의 개수도 같으면
								// row 가 작은 곳이 후보지가 된다.
								if(row > j) {
									max_point = close_num;
									row = j;
									col = k;
									empty_num = empty_this;
								}
								else if(row == j) {
									// row 까지 같으면
									// col이 작은 곳이 후보지가 된다.
									if(col > k) {
										max_point = close_num;
										row = j;
										col = k;
										empty_num = empty_this;
									}
								}
							}
						}
					}
				}
			}
			//최종 후보지에 노드가 저장된다.
			room[row][col] = node.number;
			exnodes[row][col] = node;
			
		}
		int result = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int temp = 0;
				// 배열의 각 칸에서 인접한 좋아하는 학생 수를 구한다.
				temp = get_close(i,j,exnodes[i][j]);
				// 인접한 좋아하는 학생수에 따라 만족도를 더해준다.
				switch(temp) {
					case 0 :
						temp = 0;
						break;
					case 1 :
						temp = 1;
						break;
					case 2 :
						temp = 10;
						break;
					case 3 :
						temp = 100;
						break;
					case 4 :
						temp = 1000;
						break;
				}
				result += temp;
			}
		}
		System.out.println(result);
		
		
		

	}
	// 상하좌우를 위한 배열 두개
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	
	// 주변의 빈칸의 수를 구한다.
	private static int get_empty(int row, int col) {
		int result = 0;
		// row , col 칸의 상하좌우값을 보고 결정한다.
		for(int i=0;i<4;i++) {
			if(row+dy[i] >= 0 && row+dy[i] < N && col + dx[i] >= 0 && col + dx[i] < N) {
				// 빈칸이면 결과++
				if(room[row+dy[i]][col + dx[i]] == 0) {
					result++;
				}
			}
		}
		return result;
	}
	
	
	// 인접한 좋아하는 학생수를 찾아주는 메소드
	private static int get_close(int row, int col, Node node) {
		int result = 0;
		// row col 칸의 상하좌우에 대해서
		// nodㄷ.loved 안에 있으면 결과값++
		for(int i=0;i<4;i++) {
			if(row+dy[i] >= 0 && row+dy[i] < N && col + dx[i] >= 0 && col + dx[i] < N) {
				if(isloveThere(room[row+dy[i]][col + dx[i]] , node.loved )) {
					result++;
				}
			}
		}
		// 상하좌우에 잇는 좋아하는 학생들의 수를 반환
		return result;
	}

	// 학생번호 x 가 loved 안에 있는지 검사한다.
	private static boolean isloveThere(int x, int[] loved) {
		for(int i=0;i<4;i++) {
			if(x == loved[i]) {
				return true;
			}
		}
		return false;
		
	}

}

class Node {
	int number;
	int loved[];
	
	Node(int a , int b[]){
		number = a;
		loved = b.clone();
	}


}
