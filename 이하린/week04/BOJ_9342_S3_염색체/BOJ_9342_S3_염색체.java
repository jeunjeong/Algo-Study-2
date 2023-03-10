package com.ssafy.daily14;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_9342_S3_염색체 {

	/**
	 * 문자열
	 * 메모리 : 14020KB 시간 : 120ms
	 * 문제를 다 풀고 다른 분들 코드 보니까 넘 꼬아서 생각한 것 같지만 ...  
	 * 일단 맞았습니다. 라고 떴다.
	 */
	
	private static StringBuilder sb = new StringBuilder();
	private static List<Character> deduplication; //중복 제거를 한 후 저장하는 arrayList
	private static boolean isAns; //Infected!를 출력할 지 Good을 출력할 지 결정 

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine()); 

		/**
		 * 문자열을 입력받은 후 문자들이 중복을 가진다면 중복 제거를 해준다.
		 * 중복 제거 후의 문자열의 length가 어떻게 될 지 모르니까 ArrayList 선언
		 * 첫글자가 ABCDEF 중에 있다면 T, 아니면 F
		 * T일때 만약 첫 글자가 A라면 뒤에 F -> C 인지 순차적으로 확인하고,
		 * 만약 첫 글자가 A가 아니라면 A -> F -> C 인지 순차적으로 확인한다.
		 * C까지 확인이 끝났을 때,
		 * C가 마지막 글자가 아니라면, 마지막 글자가 AB(C)DEF 중에 있는지에 따라 T F 출력 
		 */
		
		for(int i=0;i<T;i++) {
			isAns = true; //규칙에 맞는지 확인하는 변수 true로 설정
			deduplication = new ArrayList<Character>();

			String str = in.readLine();
			
			//중복 제거 확인
			deduplication.add(str.charAt(0));
			for(int j=1;j<str.length();j++) {
				if(str.charAt(j) != str.charAt(j-1)) {
					deduplication.add(str.charAt(j));
				}
			}

			
			int index = 0;
			char first = deduplication.get(index);
			
			//규칙 확인
			if(isABCDEF(first)) { //첫글자가 ABCDEF 중에 있다면
				if(first == 'A') { //첫글자가 A라면
					check(++index);
				}
				else { //첫글자가 A가 아니라면
					if(deduplication.get(++index) != 'A') {
						isAns = false;
					}
					else {
						check(++index);
					}
				}
			} //첫글자가 ABCDEF 중에 없다면
			else isAns = false;
			
			if(isAns) sb.append("Infected!").append("\n");
			else sb.append("Good").append("\n");
		}

		System.out.println(sb);
	}

	
	// 문자가 ABCDEF 중에 있는지 확인
	private static boolean isABCDEF(char ch) {
		if(ch == 'A' || ch == 'B' || ch == 'C'
				|| ch == 'D' || ch == 'E' || ch == 'F')
			return true;
		else return false;
	}

	
	// 규칙 검사 (F -> C-> 마지막 글자)
	// 중복된 코드이므로 함수로 선언해준다. 
	private static void check(int index) {
		//해당 index의 글자가 F가 아니라면
		if(deduplication.get(index) != 'F') {
			isAns = false;
		}
		else {
			//해당 index의 글자가 C가 아니라면
			if(deduplication.get(++index) != 'C') {
				isAns = false;
			}
			else { 
				//마지막 글자가 C가 아니라면
				if(index != deduplication.size()-1) {
					// 마지막 글자가 AB(C)DEF 중에 없다면
					if(!isABCDEF(deduplication.get(++index))) {
						isAns = false;
					}
				}
			}
		}
	}
}

