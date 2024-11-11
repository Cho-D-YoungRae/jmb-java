package book.jmb.chapter08.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class FasterSolution {

    private static final int INF = 987654321;

    private static int n;
    private static int[] S;
    // C[i] = 지금까지 만든 부분 배열이 갖는 길이 i 인 증가 부분 수열 중 최소의 마지막 값
    private static int[] C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            n = Integer.parseInt(br.readLine());
            S = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            C = new int[S.length + 1];
            Arrays.fill(C, INF);

            C[0] = 0;
            int result = 0;
            for (int i = 0; i < S.length; i++) {
                int len = binarySearch(S[i]);
                C[len + 1] = Math.min(C[len + 1], S[i]);
                result = Math.max(result, len + 1);
            }

            System.out.println(result);
        }

    }

    private static int binarySearch(int target) {
        int left = 0;
        int right = C.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (C[mid] <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
