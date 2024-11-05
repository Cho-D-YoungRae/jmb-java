package book.jmb.chapter08.tiling2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Code_8_16 {

    private static final int MOD = 1000000007;

    private static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(br.readLine());
            cache = new int[n + 1];
            Arrays.fill(cache, -1);
            System.out.println(tiling(n));
        }
    }

    // 2*width 크기의 사각형을 채우는 방법의 수를 MOD로 나눈 나머지를 반환한다.
    private static int tiling(int width) {
        if (width <= 1) {
            return 1;
        }
        // 메모이제이션
        int ret = cache[width];
        if (ret != -1) {
            return ret;
        }

        ret = (tiling(width - 2) + tiling(width - 1)) % MOD;
        return cache[width] = ret;
    }
}
