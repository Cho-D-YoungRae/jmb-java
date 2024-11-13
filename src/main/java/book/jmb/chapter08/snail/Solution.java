package book.jmb.chapter08.snail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    private static int n;
    private static int m;

    private static double[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            cache = new double[m][n * 2 + 1];
            for (int i = 0; i < m; i++) {
                Arrays.fill(cache[i], -1);
            }

            System.out.println(climb2(0, 0));
        }
    }

    // 달팽이가 지금까지 days일 동안 climbed미터를 기어올라 왔을 때
    // m일 전까지 n미터 이상 기어올라갈 수 있을 확률
    private static double climb2(int days, int climbed) {
        // 기저 사례: m 일이 모두 지난 경우
        if (days == m) {
            return climbed >= n ? 1 : 0;
        }

        // 메모이제이션
        double ret = cache[days][climbed];
        if (ret != -1) {
            return ret;
        }

        ret = 0.25 * climb2(days + 1, climbed + 1) + 0.75 * climb2(days + 1, climbed + 2);
        return cache[days][climbed] = ret;
    }
}
