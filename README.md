# jmb-java

> 알고리즘 문제해결전략(종만북) 코드 Javarization

## Chapter 06. 무식하게 풀기

- [소풍](src/main/java/book/jmb/chapter06/picnic/Main.java)
- [게임판 덮기](src/main/java/book/jmb/chapter06/boardcover/Main.java)

## Chapter 07. 분할 정복

| 문제        | 교재    | 알고스팟                                                       | 코드                                                            |
|-----------|-------|------------------------------------------------------------|---------------------------------------------------------------|
| 쿼드 트리 뒤집기 | 191pg | [링크](https://www.algospot.com/judge/problem/read/QUADTREE) | [링크](./src/main/java/book/jmb/chapter07/quadtree/Main.java)   |
| 울타리 잘라내기  | 195pg | [링크](https://algospot.com/judge/problem/read/FENCE)        | [링크](./src/main/java/book/jmb/chapter07/fence/Main.java)      |
| ~~팬미팅~~   | 201pg | [링크](https://algospot.com/judge/problem/read/FANMEETING)   | [링크](./src/main/java/book/jmb/chapter07/fanmeeting/Main.java) |

## Chapter 08. 동적 계획법

| 예제                   | 교재           | 코드                                                   |
|----------------------|--------------|------------------------------------------------------|
| 재귀 호출을 이용한 이항 계수의 계산 | 209pg 코드 8.1 | [링크](src/main/java/book/jmb/chapter08/Code_8_1.java) |
| 메모제이션을 이용한 이항 계수의 계산 | 211pg 코드 8.2 | [링크](src/main/java/book/jmb/chapter08/Code_8_2.java) |

| 문제                                       | 교재    | 알고스팟                                                           | 코드                                                                                                                                                                                                                                                                          |
|------------------------------------------|-------|----------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 외발 뛰기                                    | 215pg | [문제](https://www.algospot.com/judge/problem/read/JUMPGAME)     | [코드 8.4](./src/main/java/book/jmb/chapter08/jumpgame/Code_8_4.java), [코드 8.5](./src/main/java/book/jmb/chapter08/jumpgame/Code_8_5.java)                                                                                                                                    |
| 와일드카드                                    | 220pg | [문제](https://www.algospot.com/judge/problem/read/WILDCARD)     | [코드 8.6](src/main/java/book/jmb/chapter08/wildcard/Code_8_6.java), [코드 8.7](src/main/java/book/jmb/chapter08/wildcard/Code_8_7.java), [다른 분해 방법](./src/main/java/book/jmb/chapter08/wildcard/OtherSolution.java)                                                            |
| 삼각형 위의 최대 경로                             | 226pg | [문제](https://www.algospot.com/judge/problem/read/TRIANGLEPATH) | [코드 8.9](./src/main/java/book/jmb/chapter08/trianglepath/Code_8_9.java)                                                                                                                                                                                                     |
| 최대 증가 부분 수열(Longest Increasing Sequence) | 230pg | [문제](https://algospot.com/judge/problem/read/LIS)              | [코드 8.10](./src/main/java/book/jmb/chapter08/lis/Code_8_10.java), [코드 8.11](./src/main/java/book/jmb/chapter08/lis/Code_8_11.java), [코드 8.12](./src/main/java/book/jmb/chapter08/lis/Code_8_12.java), [더 빠른 해법](./src/main/java/book/jmb/chapter08/lis/FasterSolution.java) |
| 합친 LIS                                   | 237pg | [문제](https://algospot.com/judge/problem/read/JLIS)             | [코드 8.13](./src/main/java/book/jmb/chapter08/jlis/Code_8_13.java)                                                                                                                                                                                                           |
| 원주율 외우기                                  | 241pg | [문제](https://algospot.com/judge/problem/read/PI)               | [코드 8.14](./src/main/java/book/jmb/chapter08/pi/Code_8_14.java)                                                                                                                                                                                                             |
| Quantization                             | 244pg | [문제](https://algospot.com/judge/problem/read/QUANTIZE)         | [코드 8.15](./src/main/java/book/jmb/chapter08/quantize/Code_8_15.java)                                                                                                                                                                                                       |
| 타일링                                      | 252pg | [문제](https://algospot.com/judge/problem/read/TILING2)          | [코드 8.16](./src/main/java/book/jmb/chapter08/tiling2/Code_8_16.java)                                                                                                                                                                                                        |
| 삼각형 위의 최대 경로 수 세기                        | 252pg | [문제](https://www.algospot.com/judge/problem/read/TRIPATHCNT)   | [코드 8.17](./src/main/java/book/jmb/chapter08/tripathcnt/Code_8_17.java)                                                                                                                                                                                                     |
| 달팽이                                      | 258pg | [문제](https://www.algospot.com/judge/problem/read/SNAIL)        | [정답](./src/main/java/book/jmb/chapter08/snail/Solution.java)                                                                                                                                                                                                                |
| 비대칭 타일링                                  | 259pg | [문제](https://www.algospot.com/judge/problem/read/ASYMTILING)   | [코드 8.19](./src/main/java/book/jmb/chapter08/asymtiling/Code_8_19.java), [코드 8.20](./src/main/java/book/jmb/chapter08/asymtiling/Code_8_20.java)                                                                                                                            |
| 폴리오미노                                    | 264pg | [문제](https://www.algospot.com/judge/problem/read/POLY)         | [코드 8.21](./src/main/java/book/jmb/chapter08/poly/Code_8_21.java)                                                                                                                                                                                                           |
| 두니발 박사의 탈옥                               | 269pg | [문제](https://www.algospot.com/judge/problem/read/NUMB3RS)      | [코드 8.22](./src/main/java/book/jmb/chapter08/numb3rs/Code_8_22.java), [코드 8.23](./src/main/java/book/jmb/chapter08/numb3rs/Code_8_23.java), [코드 8.24](./src/main/java/book/jmb/chapter08/numb3rs/Code_8_24.java)                                                            |

## Chapter 16. 비트마스크

| 문제    | 교재    | 알고스팟                                                         | 코드                                                            |
|-------|-------|--------------------------------------------------------------|---------------------------------------------------------------|
| 졸업 학기 | 590pg | [링크](https://www.algospot.com/judge/problem/read/GRADUATION) | [링크](./src/main/java/book/jmb/chapter16/graduation/Main.java) |

## Chapter 18. 선형 자료 구조

| 문제      | 교재    | 알고스팟                                                       | 코드                                                                    |
|---------|-------|------------------------------------------------------------|-----------------------------------------------------------------------|
| 조세푸스 문제 | 620pg | [문제](https://www.algospot.com/judge/problem/read/JOSEPHUS) | [코드 18.2](./src/main/java/book/jmb/chapter18/josephus/Code_18_2.java) |

## Chapter 19. 큐와 스택, 데크

| 문제          | 교재    | 알고스팟                                                       | 코드                                                                                       |
|-------------|-------|------------------------------------------------------------|------------------------------------------------------------------------------------------|
| 조세푸스 문제     | 628pg | [문제](https://www.algospot.com/judge/problem/read/JOSEPHUS) | [큐를 이용한 조세푸스 문제의 해법](./src/main/java/book/jmb/chapter19/josephus/SolutionWithQueue.java) |
| 울타리 잘라내기    | 628pg | [문제](https://algospot.com/judge/problem/read/FENCE)        | [코드 19.1](./src/main/java/book/jmb/chapter19/fence/Code_19_1.java)                       |
| 짝이 맞지 않는 괄호 | 634pg | [문제](https://algospot.com/judge/problem/read/BRACKETS2)    | [코드 19.2](./src/main/java/book/jmb/chapter19/brackets2/Code_19_2.java)                   |
| 외계 신호 분석    | 635pg | [문제](https://www.algospot.com/judge/problem/read/ITES)     | [코드 19.5](./src/main/java/book/jmb/chapter19/ites/Code_19_5.java)                        |

## Chapter 21. 트리의 구현과 순회

| 문제          | 교재    | 알고스팟                                                        | 코드                                                                                   |
|-------------|-------|-------------------------------------------------------------|--------------------------------------------------------------------------------------|
| 트리 순회 순서 변경 | 686pg | [문제](https://www.algospot.com/judge/problem/read/TRAVERSAL) | [코드 21.4](./src/main/java/book/jmb/chapter21/traversal/Code_21_4.java)               |
| 요새          | 689pg | [문제](https://www.algospot.com/judge/problem/read/FORTRESS)  | [풀이(코드 21.5, 21.6, 21.7)](./src/main/java/book/jmb/chapter21/fortress/Solution.java) |

## Chapter 22. 이진 검색 트리

| 문제               | 교재    | 알고스팟                                                        | 코드                                                                                  |
|------------------|-------|-------------------------------------------------------------|-------------------------------------------------------------------------------------|
| 너드인가, 너드가 아닌가? 2 | 702pg | [문제](https://www.algospot.com/judge/problem/read/NERD2)     | [풀이(코드 22.1, 코드 22.2)](./src/main/java/book/jmb/chapter22/nerd2/Solution.java)      |
| 삽입 정렬 뒤집기        | 721pg | [문제](https://www.algospot.com/judge/problem/read/INSERTION) | [풀이(코드 22.3 ~ 코드 22.8)](./src/main/java/book/jmb/chapter22/insertion/Solution.java) |

## Chapter 23. 우선순위 큐와 힙

| 문제       | 교재    | 알고스팟                                                            | 코드                                                                                                                                                     |
|----------|-------|-----------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| 변화하는 중간값 | 731pg | [문제](https://www.algospot.com/judge/problem/read/RUNNINGMEDIAN) | [코드 23.3](./src/main/java/book/jmb/chapter23/runningmedian/Code_23_3.java), [코드 23.3](./src/main/java/book/jmb/chapter23/runningmedian/Code_23_4.java) |

## Chapter 28. 그래프의 깊이 우선 탐색

| 문제         | 교재    | 알고스팟                                                         | 코드                                                                                  |
|------------|-------|--------------------------------------------------------------|-------------------------------------------------------------------------------------|
| 고대어 사전     | 831pg | [문제](https://www.algospot.com/judge/problem/read/DICTIONARY) | [풀이(코드 28.2, 코드 28.3)](./src/main/java/book/jmb/chapter28/dictionary/Solution.java) |
| 단어 제한 끝말잇기 | 842pg | [문제](https://www.algospot.com/judge/problem/read/WORDCHAIN)  | [풀이(코드 28.5 ~ 코드 28.7)](./src/main/java/book/jmb/chapter28/wordchain/Solution.java) |
| 감시 카메라 설치  | 864pg | [문제](https://www.algospot.com/judge/problem/read/GALLERY)    | [코드 28.11](./src/main/java/book/jmb/chapter28/gallery/Code_28_11.java)              |

## Chapter 29. 그래프의 너비 우선 탐색

| 문제           | 교재    | 알고스팟                                                       | 코드                                                                                                                                           |
|--------------|-------|------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| Sorting Game | 886pg | [문제](https://www.algospot.com/judge/problem/read/SORTGAME) | [코드 29.3](./src/main/java/book/jmb/chapter29/sortgame/Code_29_3.java), [코드 29.4](./src/main/java/book/jmb/chapter29/sortgame/Code_29_4.java) |
