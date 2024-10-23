# Chapter 07. 분할 정복

## 7.1 도입

분할 정복이 일반적인 재귀 호출과 다른 점은 문제를 한 조각과 나머지 전체로 나누는 대신 거의 같은 크기의 부분 문제로 나누는 것입니다.

분할 정복을 사용하는 알고리즘들은 대개 세 가지의 구성 요소를 갖고 있습니다.

- 문제를 더 작은 문제로 분할하는 과정(divide)
- 각 문제에 대해 구한 답을 원래 문제에 대한 답으로 병합하는 과정(merge)
- 더 이상 답을 분할하지 않고 곧장 풀 수 있는 매우 작은 문제(base case)

분할 정복을 적용해 문제를 해결하기 위해서는 문제에 몇 가지 특성이 성립해야 합니다.

- 문제를 둘 이상의 부분 문제로 나누는 자연스러운 방법이 있어야 하며,
- 부분 문제의 답을 조합해 원래 문제의 답을 계산하는 효율적인 방법이 있어야 합니다.

분할 정복의 장점은 많은 경우 같은 작업을 더 빠르게 처리해줍니다.

**나누어 떨어지지 않을 때의 분할과 시간 복잡도**

1 + (m - 1) / 2 + (m - 1) / 2

같은 문제라도 어떻게 분할하느냐에 따라 시간 복잡도 차이가 커진다.