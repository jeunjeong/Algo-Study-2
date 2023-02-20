#include <iostream>

using namespace std;


int max_visit = 0; // 제일 많이 들어온 방문자 수 
int count = 0; // max가 몆개 나오는가? 
int main() {
	int X , N;
	
	// 입력받기 
	cin >> N >> X;
	
	// 입력값 저장 배열 선언 
	int visits[N] = {0,};
	for(int i=0; i<N;i++){
		cin >> visits[i];
	}
	
	// 초기 설정값 : 왼쪽부터 X개 합 구해서 max , count 설정 
	int temp_sum = 0;
	for(int i=0;i<X;i++){
		temp_sum += visits[i];
	}
	max_visit = temp_sum;
	count = 1;
	
	
	// 왼쪽부터 슬라이딩 윈도우
	// 값 == max 이면 count++
	// 값 > max 이면 max = 값 , count = 1;
	// 값 < max 이면 continue
	
	for(int i=X;i<N;i++){
		temp_sum -= visits[i-X];
		temp_sum += visits[i];
		
		if(max_visit > temp_sum){
			continue;
		}
		else if(max_visit == temp_sum){
			count++;
		}
		else if(max_visit < temp_sum){
			max_visit = temp_sum;
			count = 1;
		}
	}
	
	
	// 최대 방문자 수가 0이면 SAD
	// 아니면 최대방문자수 , 횟수 출 
	if(max_visit == 0){
		cout << "SAD" << '\n';
	}
	else {
		cout << max_visit << '\n' << count << '\n';
	}
	return 0;
}
