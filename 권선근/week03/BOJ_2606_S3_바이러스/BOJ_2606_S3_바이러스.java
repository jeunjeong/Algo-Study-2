package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2606_S3_바이러스 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		// 컴퓨터수 , 연결 간선수 입력
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// Connects[i] 는 i번쨰 컴퓨터와 연결된 컴퓨터 리스트를 가진다.
		ArrayList<Integer>[] Connects = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			Connects[i] = new ArrayList<Integer>();
		}
		for(int i=0;i<M;i++) {
			int inputs[] = {sc.nextInt() , sc.nextInt()};
			Connects[inputs[0]].add(inputs[1]);
			Connects[inputs[1]].add(inputs[0]);
		}
		
		// 감염된 컴퓨터 확인용 배열
		boolean infected[] = new boolean[N+1];
		
		// 1번 컴퓨터부터 감염 확산
		infected[1] = true;
		
		// 큐에 새로 감염된 컴퓨터를 삽입한다.
		Queue<Integer> mq = new LinkedList<Integer>();
		mq.add(1);
		
		while(!mq.isEmpty()) {
			int top = mq.poll();
			
			// 연결된 컴퓨터들은 감염된다!!!!!!!
			for(int conn : Connects[top]) {
				// 이미 감염된 컴퓨터는 큐에 넣을 필요가 없다.... => 새로 감염된 감염자만 입력
				if(infected[conn] == false) {
					mq.add(conn);
					infected[conn] = true;
				}
			}
		}
		
		int result = 0;
		
		// 최초 숙주 1번을 제외한 감염된 컴퓨터 수 세기
		for(int i=2;i<=N;i++) {
			if(infected[i]) result++;
		}
		
		System.out.println(result);
		
		
		
		
	}

}
