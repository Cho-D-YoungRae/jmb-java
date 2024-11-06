package book.jmb.chapter08.tripathcnt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.max;

public class Code_8_17 {

    private static int n;
    private static int[][] triangle;
    private static int[][] pathSumMaxCache;
    private static int[][] pathCountCache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            n = Integer.parseInt(br.readLine());
            triangle = new int[n][];
            pathSumMaxCache = new int[n][];
            pathCountCache = new int[n][];

            for (int i = 0; i < n; i++) {
                triangle[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                pathSumMaxCache[i] = new int[triangle[i].length];
                Arrays.fill(pathSumMaxCache[i], -1);
                pathCountCache[i] = new int[triangle[i].length];
                Arrays.fill(pathCountCache[i], -1);
            }

            System.out.println(count(0, 0));
        }
    }

    // (y, x)에서 시작해서 맨 아래줄까지 내려가는 경로 중 최대 경로의 개수를 반환한다
    private static int count(int y, int x) {
        // 기저 사례: 맨 아래줄에 도달한 경우
        if (y == n - 1) {
            return 1;
        }
        // 메모이제이션
        int ret = pathCountCache[y][x];
        if (ret != -1) {
            return ret;
        }

        ret = 0;
        if (path2(y + 1, x + 1) >= path2(y + 1, x)) {
            ret += count(y + 1, x + 1);
        }
        if (path2(y + 1, x + 1) <= path2(y + 1, x)) {
            ret += count(y + 1, x);
        }

        return pathCountCache[y][x] = ret;
    }

    private static int path2(int y, int x) {
        if (y == n - 1) {
            return triangle[y][x];
        }

        int ret = pathSumMaxCache[y][x];
        if (ret != -1) {
            return ret;
        }

        ret = max(path2(y + 1, x), path2(y + 1, x + 1)) + triangle[y][x];
        return pathSumMaxCache[y][x] = ret;
    }
}
