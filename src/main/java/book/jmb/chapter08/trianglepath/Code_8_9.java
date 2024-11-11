package book.jmb.chapter08.trianglepath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Code_8_9 {

    private static int n;
    private static int[][] triangle;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            n = Integer.parseInt(br.readLine());
            triangle = new int[n][];
            for (int i = 0; i < n; i++) {
                triangle[i] = Arrays.stream(br.readLine().split(" +"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            cache = new int[n][];
            for (int i = 0; i < n; i++) {
                cache[i] = new int[triangle[i].length];
                Arrays.fill(cache[i], -1);
            }

            System.out.println(path2(0, 0));
        }

    }

    private static int path2(int y, int x) {
        // 기저 사례
        int n = triangle.length;
        if (y == n - 1) {
            return triangle[y][x];
        }

        // 메모이제이션
        if (cache[y][x] != -1) {
            return cache[y][x];
        }


        int prev = path2(y + 1, x);

        if (x + 1 < triangle[y + 1].length) {
            prev = Math.max(prev, path2(y + 1, x + 1));
        }

        cache[y][x] = triangle[y][x] + prev;
        return cache[y][x];
    }

}
