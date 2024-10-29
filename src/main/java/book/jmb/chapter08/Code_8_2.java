package book.jmb.chapter08;

import java.util.Arrays;

public class Code_8_2 {

    static int[][] cache = new int[30][30];

    static {
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
    }

    static int bino2(int n, int r) {
        // 기저 사례
        if (r == 0 || n == r) {
            return 1;
        }

        // -1이 아니라면 한 번 계산 했던 값이니 곧장 반환
        if (cache[n][r] != -1) {
            return cache[n][r];
        }

        // 직접 계산한 뒤 배열에 저장
        cache[n][r] = bino2(n - 1, r - 1) + bino2(n - 1, r);
        return cache[n][r];
    }
}
