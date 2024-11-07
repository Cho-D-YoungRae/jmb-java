package book.jmb.chapter08.asymtiling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Code_8_19 {

    private static final int MOD = 1_000_000_007;

    private static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(br.readLine());
            cache = new int[n + 1];
            Arrays.fill(cache, -1);

            System.out.println(asymtiling(n));
        }
    }

    // 2 * width 크기의 각형을 채우는 비대칭 방법의 수
    private static int asymtiling(int width) {
        if (width % 2 == 1) {
            return (tiling(width) - tiling(width / 2) + MOD) % MOD;
        }

        int ret = tiling(width);
        ret = (ret - tiling(width / 2) + MOD) % MOD;
        ret = (ret - tiling(width / 2 - 1) + MOD) % MOD;
        return ret;
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
