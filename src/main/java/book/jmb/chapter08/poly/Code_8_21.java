package book.jmb.chapter08.poly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Code_8_21 {

    private static final int MOD = 10_000_000;

    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {

            int n = Integer.parseInt(br.readLine());
            cache = new int[n + 1][n + 1];
            for (int i = 0; i < n + 1; i++) {
                Arrays.fill(cache[i], -1);
            }

            int result = 0;
            for (int i = 1; i <= n; i++) {
                result = (result + poly(n, i)) % MOD;
            }

            System.out.println(result);
        }
    }

    // n개의 정사각형으로 이루어졌고, 맨 위 가로줄에 first개의
    // 정사각형을 포함하는 폴리오미노의 수를 반환한다.
    private static int poly(int n, int first) {
        // 기저 사례: n == first
        if (n == first) {
            return 1;
        }

        // 메모이제이션
        int ret = cache[n][first];
        if (ret != -1) {
            return ret;
        }

        ret = 0;
        for (int second = 1; second <= n - first; second++) {
            int add = second + first - 1;
            add *= poly(n - first, second);
            add %= MOD;
            ret += add;
            ret %= MOD;
        }

        return cache[n][first] = ret;
    }
}
