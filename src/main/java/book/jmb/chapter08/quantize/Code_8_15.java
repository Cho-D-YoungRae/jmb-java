package book.jmb.chapter08.quantize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.min;

public class Code_8_15 {

    private static final int INF = 987654321;

    // A[]: 양자화해야 할 수열, 정렬한 상태
    // pSum[]: A[]의 부분합을 저장한다. pSum[i]는 A[0]..A[i]의 합
    // pSqSum[]: A[]제곱의 부분합을 저장한다. pSqSum[i]는 A[0]^2..A[i]^2의 합
    private static int n, s;
    private static int[] A, pSum, pSqSum;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int[] ns = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            n = ns[0];
            s = ns[1];

            A = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            pSum = new int[n];
            pSqSum = new int[n];
            cache = new int[n + 1][s + 1];
            for (int i = 0; i < n; i++) {
                Arrays.fill(cache[i], -1);
            }
            precalc();
            System.out.println(quantize(0, s));
        }
    }

    // A를 정렬하고 각 부분합을 계산한다
    private static void precalc() {
        Arrays.sort(A);
        pSum[0] = A[0];
        pSqSum[0] = A[0] * A[0];
        for (int i = 1; i < n; i++) {
            pSum[i] = pSum[i - 1] + A[i];
            pSqSum[i] = pSqSum[i - 1] + A[i] * A[i];
        }
    }

    // A[lo]..A[hi]구간을 하나의 숫자로 표현할 때 최소 오차 합을 계산한다.
    private static int minError(int lo, int hi) {
        // 부분합을 이용해 A[lo] ~ A[hi]까지의 합을 구한다.
        int sum = pSum[hi] - (lo == 0 ? 0 : pSum[lo - 1]);
        int sqSum = pSqSum[hi] - (lo == 0 ? 0 : pSqSum[lo - 1]);

        // 평균을 반올림한 값으로 이 수들을 표현한다.
        int m = (int) (0.5 + ((double) sum) / (hi - lo + 1));

        // sum(A[i] - m)^2를 전개한 결과를 부분 합으로 표현
        int ret = sqSum - 2 * m * sum + m * m * (hi - lo + 1);
        return ret;
    }

    private static int quantize(int from, int parts) {
        // 기저 사례: 모든 숫자를 다 양자화했을 때
        if (from == n) {
            return 0;
        }
        // 기저 사례: 숫자는 아직 남았는데 더 묶을 수 없을 때 아주 큰 값을 반환한다.
        if (parts == 0) {
            return INF;
        }

        int ret = cache[from][parts];
        if (ret != -1) {
            return ret;
        }

        ret = INF;
        // 조각의 길이를 변화시켜 가며 최소치를 찾는다.
        for (int partSize = 1; partSize + from <= n; partSize++) {
            ret = min(ret,
                    minError(from, from + partSize - 1) + quantize(from + partSize, parts - 1));
        }

        return cache[from][parts] = ret;
    }
}
