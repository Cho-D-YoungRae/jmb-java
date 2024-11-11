package book.jmb.chapter08.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.max;

public class Code_8_12 {

    private static int n;
    private static int[] S;
    private static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            n = Integer.parseInt(br.readLine());
            S = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            cache = new int[n + 1];
            Arrays.fill(cache, -1);

            System.out.println(lis3(-1) - 1);
        }

    }

    // S[start] 에서 시작하는 증가 부분 수열 중 최대 길이를 반환한다.
    private static int lis3(int start) {
        int ret = cache[start + 1];
        if (ret != -1) {
            return ret;
        }

        // 항상 S[start]는 있기 때문에 길이는 최하 1
        ret = 1;
        for (int next = start + 1; next < S.length; next++) {
            if (start == -1 || S[start] < S[next]) {
                ret = max(ret, lis3(next) + 1);
            }
        }
        return cache[start + 1] = ret;
    }


}
