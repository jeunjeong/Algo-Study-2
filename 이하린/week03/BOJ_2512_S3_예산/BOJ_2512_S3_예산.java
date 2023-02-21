package harin.java.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2512_S3_예산 {
		
	private static StringTokenizer st;
	private static Integer[] budgetRequest; //요청한 예산을 담는 배열 
	private static int totalBudget; //국가 총 예산 
	private static List<Integer> budgetList = new ArrayList<>(); //최대 상한액이 될 수 있는 후보 저장 리스트 

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()); //지방의 수 
		budgetRequest = new Integer[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i=0;i<N;i++) {
			budgetRequest[i] = Integer.parseInt(st.nextToken());
		}
		
		totalBudget = Integer.parseInt(in.readLine());
		
		//내림차순으로 정렬
		Arrays.sort(budgetRequest);
		
		//start, end 정하기
		int start = 0;
		int end= budgetRequest[budgetRequest.length-1]; //요청 예산 중 제일 큰 값 
				
		binary_search(start, end); //이분 탐색 시작 
		
		Collections.sort(budgetList, Collections.reverseOrder()); //이분 탐색이 끝난 후 내림차순 정렬 
		System.out.println(budgetList.get(0)); //후보 값들 중 제일 큰 값 저장 
	}

	//이분 탐색
	private static void binary_search(int start, int end) {
		
		if(start <= end) {
			int sum = 0;
			int maxBudget = (start + end)/2; //최대 상한액의 후보 값 
			
			/**
			 * 만약 지방에서 요청한 예산 중 최대 상한액보다 작다면 원래 값을 sum에 더하고,
			 * 최대 상한액보다 크다면? 최대 상한액을 더해준다.
			 */
			for(int i=0;i<budgetRequest.length;i++) {
				if (maxBudget <= budgetRequest[i]) sum += maxBudget;
				else sum += budgetRequest[i];
			}
			
			/**
			 * sum이 국가 총 예산보다 적다면, 최대 상한액의 후보를 저장한 budgetList에 저장한 후
			 * 더 큰 상한액이 가능할 지 탐색하기 위해 binary_search(maxBudget+1, end); 를 실행. 
			 * 만약 sum이 국가 총 예산보다 크다면 최대 상한액이 될 수 없기 때문에 리스트에 따로 저장하지 않고
			 * 더 작은 상한액을 찾기 위해 binary_search(start, maxBudget-1) 을 해준다. 
			 */
			if(sum <= totalBudget) {
				budgetList.add(maxBudget);
				binary_search(maxBudget+1, end);
			}
			else {
				binary_search(start, maxBudget-1);
			}
		}
		//end가 start 보다 커졌다면 탐색 종료 
		else {
			return;
		}
		
		
	}
	
}