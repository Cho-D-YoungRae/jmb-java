package book.jmb.chapter08.jlis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class Code_8_13 {

    // 입력이 32비트 부호있는 정수의 모든 값을 가질 수 있으므로
    // 인위적인 최소치는 64비트여야 한다.
    private static final long NEGINF = Long.MIN_VALUE;

    private static int n, m;
    private static int[] A, B;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            A = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            B = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            cache = new int[A.length + 1][B.length + 1];
            for (int i = 0; i < cache.length; i++) {
                Arrays.fill(cache[i], -1);
            }

            System.out.println(jlis(-1, -1) - 2);
        }

    }

    // min(A[indexA], B[indexB]), max(A[indexA], B[indexB]) 로 시작하는
    // 합친 증가 부분 수열의 최대 길이를 반환한다.
    // 단 indexA == indexB == -1 혹은 A[indexA] != B[indexB] 라고 가정한다.
    private static int jlis(int indexA, int indexB) {
        // 메모이제이션
        int ret = cache[indexA + 1][indexB + 1];
        if (ret != -1) {
            return ret;
        }

        // A[indexA], B[indexB] 가 이미 존재하므로 2개는 항상 있다.
        ret = 2;
        long a = indexA == -1 ? NEGINF : A[indexA];
        long b = indexB == -1 ? NEGINF : B[indexB];
        long maxElement = max(a, b);

        // 다음 원소를 찾는다.
        for (int nextA = indexA + 1; nextA < n; nextA++) {
            if (maxElement < A[nextA]) {
                ret = max(ret, jlis(nextA, indexB) + 1);
            }
        }
        for (int nextB = indexB + 1; nextB < m; nextB++) {
            if (maxElement < B[nextB]) {
                ret = max(ret, jlis(indexA, nextB) + 1);
            }
        }

        return cache[indexA + 1][indexB + 1] = ret;
    }

}
