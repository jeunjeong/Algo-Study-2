package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

// 분류는 이분탐색인데.....
// 그냥 앞에서부터 찾았도 풀렸다.

public class BOJ_2512_S3_예산 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Region 지방의 갯수
		int N = Integer.parseInt(br.readLine());
		// 요청 예산들을 담은 배열
		int request[] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		// 예산 변수
		int budget = Integer.parseInt(br.readLine());
		
		// 요청 예산들을 정렬해준다.
		Arrays.sort(request);
		
		// 예산 배열들의 누적합 배열을 만든다
		int sums[] = new int[N];
		sums[0] = request[0];
		for(int i =1; i< N; i++) {
			sums[i] = sums[i-1] + request[i];
		}
		
		// 만약 요청한대로 다 나눠줄만큼 예산이 있으면
		if(sums[N-1] <= budget) {
			//바로 요청 최고액 출력 , Return
			System.out.println(request[N-1]);
			return;
		}
		
		// 예산이 너무 적어서 전부 균등하게 나눠도  제일 적게 요청한 지방예산을 충족하지 못하면
		if( request[0] >= budget / N  ) {
			// 그냥 공평하게 나눠가지고 끝!
			System.out.println(budget/N);
			return;
		}
		
		// 예산을 적게 요청한 지방들부터 예산을 배정해준다.
		for(int i=0;i<N;i++) {
			// 예산을 아직 못받은 지방 수
			int left_num = N-i-1;
			// 남은 예산 금액
			int left_budget = budget - sums[i];
			// 못받은 지방들에게 남은 예산을 공평하게 나눠준다고 가정했을떄
			// 그 금액이 다음 지방의 요청액보다 크면
			// 다음 지방의 요청액을 할당해주어야 한다
			// 300원 요청한 지방에 500원을 할당할수는 없으니까.....
			if(request[i+1] < left_budget / left_num) {
				continue;
			}
			else {
				// 남은 예산을 공평하게 나눈 금액이 다음 지방의 요청액보다 작으면
				// 다음 지방에 돈을 나눠줬다간 나머지 지방들이 다음지방보다 적은 예산을 받게된다.
				// 따라서 남은 예산을 공평하게 나눠 가진다.
				System.out.println(left_budget / left_num);
				break;
			}
		}
		
	}
}
