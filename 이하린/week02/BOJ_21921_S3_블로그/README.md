`투포인터`

<h3> 백준 21921번 실버3 블로그 문제 </h3>

💻 메모리 : `35108KB`</br>

⏲️ 시간 : `364ms`

<hr>

<h3> 풀이 과정</h3>

- N : 블로그를 시작한 지 지난 일수, X : 기간
- 찾고자 하는 것 : X일 동안 가장 많이 들어온 방문자 수

<br>

- 방문자 수들의 합을 누적으로 배열에 저장
- 예) accum[1] : 1일까지의 방문자 수 누적, accum[4] : 4일까지의 방문자 수 누적

<br>

- 만약 `accum[N] == 0`, 제일 마지막까지 방문자수를 누적한 결과가 0이라면 N일 전부 방문자 수가 0인 것
- `if (accum[N] == 0) sb.append("SAD");` SAD 출력


<br>

- 만약 X일이라는 기간동안 방문한 사용자 수를 구하고 싶다면, 
- accum[구하고자하는 기간의 마지막 날] - accum[구하고자하는 기간의 첫날 - 1] 을 계산하면 된다.
- 모든 날을 구하고, 그 중 최대를 찾아야하기 때문에 `for(int i = 1;i<=N-X+1;i++)` 반복문 실행
  - `int startIdx = i;` `int endIdx = i+X-1;` 로 선언
  - `int visitCnt = accum[endIdx] - accum[startIdx-1];` 로 방문자 수 저장

<br>

```
if(visitCnt>max) {
  max = visitCnt; //값 비교를 통해 최대 방문자 수 저장
  cnt=1; //최대 방문자 수가 바뀌었다면 다시 1로 갱신
 }
else if(max == visitCnt) cnt++;
```
- visitCnt(구한 X일 동안의 방문자 수)가 max보다 크다면?
  - max 값을 visitCnt로 변경하고, cnt(최대 방문자 수를 가지는 기간의 수)를 1로 갱신한다.
- max와 구한 X일 동안의 방문자 수가 같다면? cnt에 1을 더해준다.



