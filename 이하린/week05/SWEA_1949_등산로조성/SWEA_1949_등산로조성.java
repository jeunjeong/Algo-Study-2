package ssafy.com.역량대비;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1949_등산로조성 {
	
	 static class Land {
	        int r; //행 위치
	        int c; //열 위치
	        int height; //지형 높이
	         
	        public Land(int r, int c, int height) {
	            super();
	            this.r = r;
	            this.c = c;
	            this.height = height;
	        }
	 
	        @Override
	        public String toString() {
	            return "Land [r=" + r + ", c=" + c + ", height=" + height + "]";
	        }
	         
	         
	    }
	     
	    // 결과를 한 번에 출력하기 위한 StringBuilder
	    private static StringBuilder sb = new StringBuilder();
	    static StringTokenizer st;
	     
	    static int N; //등산로 길이
	    static int K; //팔 수 있는 최대 공사 가능 깊이
	    static int peakHeight; //봉우리 높이
	    static int ans; //등산로 길이
	     
	    static int map[][]; //등산로 지형 정보
	    static int copy[][]; //map 원상 복구를 위한 copy 배열
	    static boolean visited[][]; //방문 확인 배열
	 
	    static int dr[] = {-1, 1, 0, 0}; //상 하 좌 우
	    static int dc[] = {0, 0, -1, 1}; 
	 
	    public static void main(String[] args) throws Exception {
	 
	        /**
	         * 1. 입력파일 읽어들이기
	         */
	        System.setIn(new FileInputStream("input.txt"));
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	 
	        /**
	         * 2. 입력파일 객체화
	         */
	        int T; 
	        T = Integer.parseInt(in.readLine()); //테케 입력
	        for (int test_case = 1; test_case <= T; test_case++) {
	            sb.append("#" + test_case + " ");
	             
	            st = new StringTokenizer(in.readLine());
	             
	            N = Integer.parseInt(st.nextToken()); // 등산로 길이
	            K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이
	             
	            map = new int[N][N];
	            copy = new int[N][N];
	            peakHeight = Integer.MIN_VALUE; //봉우리 높이
	             
	            //등산로 지형 정보 입력 받기
	            for(int i=0;i<N;i++) {
	                st = new StringTokenizer(in.readLine());
	                for(int j=0;j<N;j++) {
	                    map[i][j] = Integer.parseInt(st.nextToken());
	                    copy[i][j] = map[i][j];
	                    if(peakHeight < map[i][j]) peakHeight = map[i][j];
	                }
	            }
	 
	            /**
	             * 3. 알고리즘 풀기
	             */
	             
	            Queue<Land> peaks = new ArrayDeque<Land>(); //봉우리 정보를 담는 큐
	             
	            //봉우리 정보 큐에 넣기
	            for(int i=0;i<N;i++) {
	                for(int j=0;j<N;j++) {
	                    if(map[i][j] == peakHeight) {
	                        peaks.offer(new Land(i, j, map[i][j]));
	                    }
	                }
	            }
	             
	            int peakSize = peaks.size(); //봉우리 몇 개인지
	            ans = Integer.MIN_VALUE; //답 변수 (가장 긴 등산로의 길이)
	             
	            for(int p=0;p<peakSize;p++) {
	                Land peak = peaks.poll();
	                 
	                //map 배열 중 한 곳을 최대 K만큼 감소
	                for(int i=0;i<N;i++) {
	                    for(int j=0;j<N;j++) {
	                        for(int k=1;k<=K;k++) {
	                            map[i][j] -= k;
	                            visited = new boolean[N][N];
	                            dfs(peak, 1);
	                            mapCopy();
	                        }
	                    }
	                }
	                  
	            }
	             
	             
	            sb.append(ans).append('\n');
	 
	        }
	 
	        /**
	         * 4. 정답 출력
	         */
	 
	        System.out.println(sb);
	    }
	     
	     
	    private static void mapCopy() {
	        for(int i=0;i<N;i++) {
	            for(int j=0;j<N;j++) map[i][j] = copy[i][j];
	        }
	    }
	 
	 
	    //dfs로 탐색
	    private static void dfs(Land peak, int len) {
	        visited[peak.r][peak.c] = true;
	         
	        for(int i=0;i<4;i++) {
	            int nr = peak.r + dr[i];
	            int nc = peak.c + dc[i];
	             
	            if((0<= nr && nr < N) && (0<=nc && nc < N)
	                    && !visited[nr][nc]
	                    && map[peak.r][peak.c] > map[nr][nc]) {
	                dfs(new Land(nr, nc, map[nr][nc]), len+1);
	                visited[nr][nc] = false;
	            }
	            else {
	                ans = Math.max(ans, len); //최대 등산로 길이를 구하기 위함
	            }
	        }
	         
	    }
}
