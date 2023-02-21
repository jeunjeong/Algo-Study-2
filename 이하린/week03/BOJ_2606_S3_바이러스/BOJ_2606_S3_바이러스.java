package ssafy.com.BOJ.silver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2606_S3_바이러스 {
	
	private static StringTokenizer st;
	private static ArrayList<Integer>[] coms; //컴퓨터와 연결된 컴퓨터들을 저장하는 ArrayList 배열
	private static boolean []isVisited; //방문 체크
	private static int comCnt; //컴퓨터 수
	private static int Viruscnt; //바이러스에 감염된 컴퓨터 수
	
	public static void main(String[] args) throws Exception {
		
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		comCnt = Integer.parseInt(in.readLine());
		int cmdCnt = Integer.parseInt(in.readLine()); //명령어 수 
		
		coms = new ArrayList[comCnt+1];
		isVisited = new boolean[comCnt+1];
		
		for(int i=1;i<=comCnt;i++) { //편의를 위해 인덱스 1번부터 배열 생성
			coms[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0;i<cmdCnt;i++) { //연결된 컴퓨터 입력 받음
			st = new StringTokenizer(in.readLine());
			int com1 = Integer.parseInt(st.nextToken());
			int com2 = Integer.parseInt(st.nextToken());
			
			coms[com1].add(com2); //서로 양쪽으로 연결되어 있으므로 둘 다 저장
			coms[com2].add(com1);
		}
		
		Viruscnt = 0; //DFS 실행 전 감염횟수 0
		DFS(1); //1과 연결된 컴퓨터 탐색
		
		System.out.println(Viruscnt);
	}

	private static void DFS(int comNo) {
		
		if(isVisited[comNo]) return; //방문한 적 있다면 return
		
		isVisited[comNo] = true; //방문한 적 없다면 isVisited 배열 true
		for(int com : coms[comNo]) { //comNo와 연결된 모든 컴퓨터 찾기
			if(isVisited[com] == false) { //comNo와 연결된 com을 방문한 적 없다면?
				DFS(com); //방문해주기
				Viruscnt++; //바이러스 감염 증가
			}
		}
		
	}
	
}
