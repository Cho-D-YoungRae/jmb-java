package book.jmb.chapter06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제 이름: 시계 맞추기
 * 문제 링크: https://www.algospot.com/judge/problem/read/CLOCKSYNC
 * 교재: 1권 168pg
 *
 * - 스위치를 누르는 순서는 전혀 중요하지 않음 -> 우리가 계산해야 할 것은 각 스위치를 몇 번이나 누를 것이냐
 * - 어떤 스위치건 간에 최대 세 번 이상 누를 일이 없음
 *      -> 어떤 스위치를 네 번 누르면 연결된 시계는 모두 12시간 씩 앞으로 이동하니 하나도 누르지 않은 것과 다름 없음
 */
public class ClockSync {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < C; c++) {
            int[] clocks = Arrays.stream(bf.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int result = solve(clocks, 0);
            result = result == Integer.MAX_VALUE ? -1 : result;
            sb.append(result);
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static final int[][] SWITCHES = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };

    // clocks: 현재 시계들의 상태
    // swtch: 이번에 누를 스위치 번호
    // 가 주어질 때, 남은 스위치들을 눌러서 clocks를 12시로 맞출 수 있는 최소 횟수를 반환
    // 만약 불가능 하다면 Integer.MAX_VALUE 반환
    private static int solve(int[] clocks, int swtch) {
        if (swtch == SWITCHES.length) {
            return areAligned(clocks) ? 0 : Integer.MAX_VALUE;
        }

        // 이 스위치를 0번 누르는 경우부터 3번 누르는 경우까지를 모두 시도
        int ret = Integer.MAX_VALUE;
        for (int cnt = 0; cnt < 4; cnt++) {
            int result = solve(clocks, swtch + 1);
            if (result != Integer.MAX_VALUE) {
                ret = Math.min(ret, cnt + result);
            }
            push(clocks, swtch);
        }

        // push(clocks, swtch)가 네 번 호출되었으니 clocks는 원래와 같은 상태가 된다.
        return ret;
    }

    // 모든 시계가 12시를 가리키고 있는지 확인한다.
    private static boolean areAligned(int[] clocks) {
        for (int clock : clocks) {
            if (clock != 12) {
                return false;
            }
        }
        return true;
    }

    // swtch 번 스위치를 누른다.
    private static void push(int[] clocks, int swtch) {
        for (int s : SWITCHES[swtch]) {
            clocks[s] += 3;
            if (clocks[s] == 15) {
                clocks[s] = 3;
            }
        }
    }

}

/*
2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6
 */