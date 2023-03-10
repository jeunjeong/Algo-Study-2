package ssafy.com.역량대비;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {

	/**
	 * BFS로 연결된 파이프 타고 이동
	 * 경과된 시간이 주어질 때 탈주범이 위치할 수 있는 장소 개수 계산
	 * 이게 터널이 있다고 다 이동할 수 있는게 아니라 연결이 되야 이동이 가능하다.
	 */

	static class Pos {
        int r;
        int c;
 
        public Pos(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
 
        @Override
        public String toString() {
            return "Pos [r=" + r + ", c=" + c + "]";
        }
 
    }
 
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
 
    static int N; //지도 세로 크기
    static int M; //지도 가로 크기
    static int R; //맨홀 뚜껑이 위치한 행(처음 탈주범 위치 행)
    static int C; //맨홀 뚜껑이 위치한 열
    static int L; //경과 시간
 
    static int map[][];
    static boolean visited[][];
 
    static int dr[] = {-1, 1, 0, 0}; //상 하 좌 우
    static int dc[] = {0, 0, -1, 1}; 
    static int[][] mapDir = {
            {},
            {0, 1, 2, 3}, //1
            {0, 1}, //2 상하
            {2,3}, //3 좌 우
            {0,3}, //4 상 우
            {1, 3}, //5 하 우
            {1, 2}, //6 하 좌
            {0, 2} //7 상 좌
    };
    static int nextTun [][] = {
            {1,2,5,6}, //위
            {1,2,4,7}, //아래
            {1,3,4,5}, //왼쪽
            {1,3,6,7} //오른쪽
    };
 
    static int ans;
 
    public static void main(String[] args) throws Exception {
 
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(in.readLine()); //테케 입력
        for(int tc = 1 ;tc<=T; tc++) {
            sb.append("#").append(tc).append(' ');
 
            //지도 세로 크기 N, 가로 크기 M, 맨홀 뚜껑이 위치한 장소 세로 위치 R, 가로 위치 C, 탈출 후 소요시간 R
            st = new StringTokenizer(in.readLine());
 
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
 
            map = new int[N][M]; //map 초기화
            visited = new boolean[N][M]; //방문 확인 배열
 
            //map 값 입력 받음
            for(int i=0; i<N;i++) {
                st = new StringTokenizer(in.readLine());
                for(int j=0;j<M;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            //시작 위치
            Pos start = new Pos(R, C);
            ans = 1; //초기 답 1로 세팅
            bfs(start); //BFS로 인접한 곳 이동 가능한지 확인
 
            sb.append(ans).append('\n');
 
        }
 
        System.out.println(sb);
 
    }
 
    //BFS로 인접한 자식들 이동 가능한 지 확인
    //L로 경과시간 제한 두는 것 잊지말기!
    private static void bfs(Pos start) {
        int hours = 1;
        Queue<Pos> queue = new ArrayDeque<Pos>();
        queue.offer(start);
        visited[start.r][start.c] = true;
         
        while(!queue.isEmpty()) {
         
            if(hours == L) break; //시간이 L과 같아지면 종료
            //current의 자식들 (갈 수 있는 애들 체크)
            int size = queue.size();
            for(int i=0; i<size;i++) {
 
                Pos current = queue.poll();
                int tunnelNo = map[current.r][current.c];
                 
                for(int j=0; j<mapDir[tunnelNo].length; j++) {
                    int dir = mapDir[tunnelNo][j];
                    //dir에 따라 갈 수 있는 위치 달라짐
                    // 위 : 1 2 5 6
                    // 아래 : 1 2 4 7
                    // 왼쪽 : 1 3 4 5
                    // 오른쪽 : 1 3 6 7
                     
                    int nr = current.r + dr[dir];
                    int nc = current.c + dc[dir];
                     
                    if(isCheck(nr,nc)) {
                        for(int k=0;k<4;k++) {
                            if(nextTun[dir][k] == map[nr][nc]) {
                                visited[nr][nc] = true;
                                queue.offer(new Pos(nr, nc));
                                ans++;
                            }
                        }
                    }
                }
            }
            hours++;
 
        }
    }
 
    //조건 확인
    private static boolean isCheck(int nr, int nc) {
        if((0<= nr && nr < N) && (0<=nc && nc <M)
                && !visited[nr][nc]
                && map[nr][nc] != 0) return true;
        else return false;
    }


}
