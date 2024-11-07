package book.jmb.chapter08.asymtiling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Code_8_20 {

    private static final int MOD = 1_000_000_007;

    private static int[] cache;
    private static int[] cache2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(br.readLine());
            cache = new int[n + 1];
            Arrays.fill(cache, -1);
            cache2 = new int[n + 1];
            Arrays.fill(cache2, -1);

            System.out.println(asymtiling2(n));
        }
    }

    // 2 * width 크기의 각형을 채우는 비대칭 방법의 수를 반환한다.
    private static int asymtiling2(int width) {
        // 기저 사례: 너비가 2 이하인 경우
        if (width <= 2) {
            return 0;
        }
        // 메모이제이션
        int ret = cache2[width];
        if (ret != -1) {
            return ret;
        }

        ret = asymtiling2(width - 2) % MOD; // (a)
        ret = (ret + asymtiling2(width - 4)) % MOD; // (b)
        ret = (ret + tiling(width - 3)) % MOD;  // (c)
        ret = (ret + tiling(width - 3)) % MOD;  // (d)

        return cache2[width] = ret;
    }

    private static int tiling(int width) {
        if (width <= 1) {
            return 1;
        }

        int ret = cache[width];
        if (ret != -1) {
            return ret;
        }

        ret = (tiling(width - 1) + tiling(width - 2)) % MOD;
        return cache[width] = ret;
    }

}
