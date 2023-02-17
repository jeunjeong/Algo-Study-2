package week02.BOJ_21921_S3_블로그;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_21921_S3_블로그 {

	public static void main(String[] args) throws IOException {
		//input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nx = in.readLine().split(" ");
		int N = Integer.parseInt(nx[0]);	//블로그 일수
		int X = Integer.parseInt(nx[1]);	//알고싶은 기간
		
		int[] visits = new int[N];			//N일동안 방문자수
		String[] visitArr = in.readLine().split(" ");
		for (int i = 0; i < visitArr.length; i++) {
			visits[i] = Integer.parseInt(visitArr[i]);
		}
		
		//logic
		int cnt = 0;		//최대 방문자수인 X 기간 수
		int maxSum = -1;	//X일동안 최대 방문자수
		int sum = 0;		//X일동안 방문자수(임시저장공간)
		
		for (int i = 0; i < N; i++) {
			sum += visits[i];
			if (i >= X) sum -= visits[i - X]; //기간 X가 넘지 않도록 조정
			
			if (maxSum < sum) {
				maxSum = sum;
				cnt = 1;		//최대 방문자수 바뀌면 기간 수 초기화
			}
			else if (maxSum == sum) cnt++;	//최대 방문자수 기간 카운트
		}
		
		//print
		if (maxSum == 0) {
			System.out.println("SAD");
		}
		else {
			System.out.println(maxSum);
			System.out.println(cnt);
		}
	}
}
