package week02.BOJ_9084_G5_동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9084_G5_동전 {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(in.readLine());

		while(T-- > 0) {
			//input
			int typeCnt = Integer.parseInt(in.readLine());	//coin 종류
			int[] coins = new int[typeCnt];	 				//coin 담을 배열
			String[] coinArr = in.readLine().split(" ");
			for (int i = 0; i <coinArr.length; i++) 
				coins[i] = Integer.parseInt(coinArr[i]);

			int money = Integer.parseInt(in.readLine());	//원하는 값
			int[] dp = new int[money + 1];					//dp[i] : 코인을 가지고 i를 만들 수 있는 경우의 수
			
			//logic
			dp[0] = 1; 	//초기값은 0
			for (int coin : coins) {	//코인을 하나씩 가져오면서 일차원 배열에 값을 누적해줌
				for (int i = coin; i < money + 1; i++) {
					dp[i] += dp[i - coin];
				}
			}
			
			//print
			System.out.println(dp[money]); //테케 10가개 최대라서 그냥 sout 함
		}
	}
	
}