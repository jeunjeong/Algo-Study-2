package com.ssafy.daily13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_2447_G5_별찍기10 {
	
	/**
	 * 분할정복
	 * 메모리 : 48000KB 시간 : 328ms
	 * 수업시간에 배운 분할 정복 문제랑 비슷해서 로직을 생각해내는게 어렵진 않았지만
	 * 계속 틀렸습니다 .. 라고 떠서 시간이 많이 걸렸다 
	 */
	
	private static StringBuilder sb = new StringBuilder();

	private static char[][] arr;

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//크기 N, N은 3의 거듭제곱 
		int N = Integer.parseInt(in.readLine()); 
		
		//N 크기의 배열 생성
		arr = new char[N][N];
		
		/*
		 * 분할정복 재귀함수 호출
		 * r:0, c:0 부터 시작, size는 N, blank는 false
		 * blank는 해당 자리가 공백인지 아닌지를 판단 
		 */
		divide(0, 0, N, false);

		//출력
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}


		System.out.println(sb);

	}
	
	//분할정복 함수
	private static void divide(int r, int c, int size, boolean blank) {
		
		//blank 처리와 if(size==1) 순서 중요!!
		//만약 둘의 순서가 바뀐다면
		//size가 1일 때 5번째 공간에도 별이 찍혀서 나온다 .. 
		
		//blank가 true일 때 해당 부분은 모두 공백 처리
		if(blank) {
			for(int i=r;i<r+size;i++) {
				for(int j=c;j<c+size;j++) {
					arr[i][j] = ' ';
				}
			}
			return;
		}
		
		// 기저 조건 size가 1이면 별 찍고 return
		if(size == 1) {
			arr[r][c] = '*';
			return;
		}

		// 유도 조건
		int third = size/3; //size 3으로 나눔
		
		/**
		 * count 변수
		 * 3^N X 3^N을 3^N/3으로 분할하면 9개의 조각이 생기고
		 * 9개의 조각 중에서 항상 5번째 공간이 공백 공간이다.
		 * 그렇기 떄문에 count가 5일 때 blank 변수를 true로 설정하고 보낸다. 
		 * 나머지 경우에는 false 
		 */
		int count = 0;

		for(int i=r;i<r+size;i+=third) {
			for(int j=c;j<c+size;j+=third) {
				count++;
				if(count == 5) {
					divide(i, j, third, true);
				}
				else {
					divide(i, j, third, false);
				}
			}
		}
	}

}

