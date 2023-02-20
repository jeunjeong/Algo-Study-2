#include <iostream>
#include <vector>
using namespace std;


int max_visit = 0; // 제일 많이 들어온 방문자 수 
int count = 0; // max가 몆개 나오는가? 

// 중앙 좌표 
int row_center;
int col_center;
vector<vector<int>> board;


void sol(int cnt , int row , int col);
int N , M;
int main() {
	cin >> N >> M;
	
	// 배열선언 
	board.assign(N,vector<int>(N,0));
	
	// 중앙값은 1 
	row_center = N/2;
	col_center = N/2;
	board[row_center][col_center] = 1;
	
	// SOLUTION 재귀함수 시작! 
	sol(1 , row_center , col_center);
	
	// 배열 전체 출력하기
	// M을 찾으면 result_cord에 저장했다가 나중에 출력해준다. 
	pair<int,int> result_cord;
	for(int i=0;i<N;i++){
		for(int j=0;j<N;j++){
			cout << board[i][j] << " ";
			if(board[i][j] == M){
				result_cord = make_pair(i,j);
			}
		}
		cout << '\n';
	}
	cout << result_cord.first+1 << " " << result_cord.second+1 << '\n';
	return 0;
}

void sol( int cnt , int row , int col){
	
	if(col == 0){
		return;
	}
	
	// 한칸위로  
	row--;
	board[row][col] = board[row+1][col] + 1;
	
	// 오른쪽 
	for(int i=0;i<2*cnt-1;i++) {
		col++;
		board[row][col] = board[row][-1 + col] + 1;
	}
	// 아래 
	for(int i=1;i<= 2*cnt;i++) {
		row++;
		board[row][col] = board[row-1][col] + 1;
	} 
	// 왼쪽 
	for(int i=1;i<= 2*cnt;i++) {
		col--;
		board[row][col] = board[row][col+1] + 1;
	} 
	// 위 
	for(int i=1;i<= 2*cnt;i++) {
		row--;
		board[row][col] = board[row+1][col] + 1;
	} 
	
	// 재귀하기 
	sol(cnt+1, row , col);
}
