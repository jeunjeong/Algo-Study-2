package week03.BOJ_2606_S3_바이러스;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2606_S3_바이러스 {

	/*
	 * 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 구하라
	 * 
	 * => 네크워크망을 입력받고 map 써서 bfs 하면 될듯
	 * 
	 */
	static int N;
	static Map<Integer, List<Integer>> net = new HashMap<>();
	
	public static void main(String[] args) {
		//input
		//한 줄당 최대 두개라서 그냥 스캐너 씀
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int pairN = sc.nextInt();
		
		while(pairN-- > 0) {
			int com1 = sc.nextInt();
			int com2 = sc.nextInt();
			putNet(com1, com2);
			putNet(com2, com1);
		}
		
		//logic
		visited = new boolean[N+1]; //1~N -- index error
		System.out.println(bfs(1));
		
	}
	
	static boolean[] visited;
	private static int bfs(int curNode) {
		int cnt = 0;
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(curNode);
		visited[curNode] = true;
		
		while(!q.isEmpty()) {
			int node = q.poll();
			cnt++;
			
			for (int adj : net.get(node)) {
				if (visited[adj]) continue;
				
				visited[adj] = true;
				q.offer(adj);
			}
		}
		return cnt-1;
	}

	private static void putNet(int key, int val) {
		List<Integer> valLs;
		if (net.containsKey(key)) 
			valLs = net.get(key);
		else 
			valLs = new ArrayList<>();
		valLs.add(val);
		net.put(key, valLs);
	}
}
