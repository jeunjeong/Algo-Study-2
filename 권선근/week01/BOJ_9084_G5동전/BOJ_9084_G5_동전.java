import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 동전을 종류별로 하나씩 DP 돌리면 끝! 
public class Main {
	
	//DP 배열 
	static int[] dp; 
	// 코인 종류 배열 
	static int[] coins;
	// 코인들 종류 갯수 
	static int num_coins;
	// 나타내야할 숫자 
	static int num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스 수 
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 0; test_case < T ; test_case++) {
			//코인 종류 입력  
			num_coins = Integer.parseInt(br.readLine()); 
			// 코인 값 입력 
			String coins_str[] = br.readLine().split(" ");
			coins = new int[num_coins];
			for(int i=0; i< num_coins; i++) {
				coins[i] = Integer.parseInt(coins_str[i]);
			}
			
			// 나타낼 숫자 입력 
			num = Integer.parseInt(br.readLine());
			
			// dp 배열 초기화 
			dp = new int[num+coins[num_coins-1] + 1];
			//**********************INPUT***********************
			
			// 0원은 모든 동전 0개이므로 단 하나! 
			dp[0] = 1;
			// 동전 종류만큼 DP 돌리기 
			for(int i=0;i<num_coins;i++) {
				sol(coins[i]);
			}
			// 결과값 출력 
			System.out.println(dp[num]);
			
		}
	}
	// DP 구현 
	private static void sol(int idx) {
		// 만약 idx = 동전 값이 3원이라면
		// 경우의수 : 0원 = 1 이면 
		// 경우의수 : 3원 = 1이다.
		// 경우의수 : 6원 = 1이다. 
		
		// 두번째 DP 에서 idx = 동전 값이 2원 이라면
		// 경우의수 : 0원 = 1 이면 
		// 경우의수 : 2원 = 1이다.
		// 경우의수 : 4원 = 1이다. 
		// 경우의수 : 6원 = 2이다. => dp[6] = dp[6] + dp[4] 이기 떄문에!!!!!! 
		for(int i = idx; i <= num ; i++ ) {
			dp[i] += dp[i - idx];
		}
	}
	
}
