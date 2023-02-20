#include<vector>
#include<iostream>
#include<algorithm>
#include<cmath>
using namespace std;

int main() {
	int n = 0 , sum = 0;
	
	cin >> n; // 입력값 받기
	
	vector<int> dp(n+1,0);
	// 1은 1^2 하나만 존재한다. 
	dp[1] = 1;
	
	// DP 논리 설명
	// 알다시피 i = 제곱수들의 합 이어야 한다.
	// DP[20] 을 찾는다고 가정하자.
	// 그렇다면 20 = X(이전 제곱수들의 합) + Y^2 이어야만 한다.
	// 따라서 우리는 X = 20 - Y^2 임을 유추하고
	// Y=1 부터 Y^2  이 20보다 커져서 음수가 되지 않는 선까지 대입하여 X를 구한다..
	// 존재 가능한 DP[X] 중 (가장 작은 수 + 1) 을 DP[20]에 투입한다. 
	for(int i = 2; i <= n ; i++) {
		dp[i] = 999999999;
		for(int j = 1; j*j <= i ; j++){
			int chai = i - j*j;
			dp[i] = min(dp[chai] + 1 , dp[i]);
		}
		//cout << i << " " << dp[i] << '\n';
	}
	cout << dp[n] << '\n';
	return 0;
}
