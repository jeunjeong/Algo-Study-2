package week02.BOJ_17626_S3_FourSquares;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_17626_S3_FourSquares {
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();

		//dp
		dp = new int[num + 1];
		Arrays.fill(dp, Integer.MAX_VALUE - 1);
		dp[0] = 0; //초기화!
		
		for (int i = 1; i <= num; i++) {
			for (int j = 1; j <= Math.sqrt(i); j++) {
				int doubleJ = j * j;
				if (i - doubleJ < 0) break;
				dp[i] = Math.min(dp[i], dp[i - doubleJ] + 1);
			}
		}
		System.out.println(dp[num]);
	}
}
