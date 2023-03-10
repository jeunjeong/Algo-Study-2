import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class BOJ_16234_G5_인구이동 {
	static int N , L , R;
	static boolean visited[][];
	static int board[][];
	static List<ArrayList<Cordinate>> blist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		N = line[0];
		L = line[1];
		R = line[2];
		
		board = new int[N][];
		
		
		for(int i=0;i<N;i++) {
			board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int count = 0;
		while(true) {
			
			visited = new boolean[N][N];
			blist = new ArrayList<ArrayList<Cordinate>>();
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j] ) {
						visited[i][j] = true;
						ArrayList<Cordinate> temp = new ArrayList<Cordinate>();
						// 연합
						bfs(i,j,temp);
						blist.add(temp);
						
	
					}
				}
			}
			
			if(blist.size() == N*N) break;
			count++;
			for(ArrayList<Cordinate> cordList : blist) {
				int new_population = 0;
				for(Cordinate cord : cordList) {
					new_population += board[cord.row][cord.col];
				}
				new_population /= cordList.size();
				for(Cordinate cord : cordList) {
					board[cord.row][cord.col] = new_population;
				}
			}
		}
		System.out.println(count);
		
	}
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	
	private static void bfs(int row, int col, List<Cordinate> result_list) {
		Queue<Cordinate> bq = new LinkedList<>();
		bq.add(new Cordinate(row, col));
		result_list.add(new Cordinate(row, col));
		while(!bq.isEmpty()) {
			Cordinate now = bq.poll();
			
			for(int i=0;i<4;i++) {
				//System.out.println(now);
				int next_row = now.row + dy[i];
				int next_col = now.col + dx[i];
				//System.out.println(" " + next_row + " " + next_col);
				if(next_row >= 0 && next_row < N && next_col >= 0 && next_col < N) {
					
					if(!visited[next_row][next_col]) {
						int chai = Math.abs(board[next_row][next_col] - board[now.row][now.col]);
						if(chai >= L && chai <= R) {
							visited[next_row][next_col] = true;
							result_list.add(new Cordinate(next_row , next_col));
							bq.add(new Cordinate(next_row , next_col));
						}
					}
				}
			}
		}
		
	}
}
class Cordinate {
	int row;
	int col;
	
	public Cordinate(int next_row, int next_col) {
		row = next_row;
		col = next_col;
	}
	@Override
	public String toString() {
		return row + " " + col;
	}
	
}
