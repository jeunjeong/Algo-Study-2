package week03.BOJ_2512_S3_예산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2512_S3_예산 {
	static int areaN;
	static long[] money;
	static long M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		areaN = Integer.parseInt(in.readLine());
		money = new long[areaN];
		String[] line = in.readLine().split(" ");
		for (int i = 0; i < areaN; i++) {
			money[i] = Long.parseLong(line[i]);
		}	
		M = Integer.parseInt(in.readLine());
		
		//logic
		//오름차순 정렬
		Arrays.sort(money); 
		long saveMax = money[areaN-1];
		
		//예산 분배
		for (int i = 0; i < areaN; i++) {
			//예산으로 커버 가능
			if (money[i] <= M/(areaN-i)) {
				moneySub(i, money[i]);
			}
			//예산 부족하면 예산 n빵 가능한 만큼 일단 다 넣음. 예산 다 씀. 끝.
			else {
				moneySub(i, M/(areaN-i));
				break;
			}
		}
		
		
		//출력
		System.out.println(saveMax - money[areaN-1]);
	}

	private static void moneySub(int start, long val) {
		//money
		for (int i = start; i < areaN; i++) {
			money[i] -= val;
		}
		//남은예산
		M -= (val * (areaN-start));
	}

}
