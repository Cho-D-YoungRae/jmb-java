package book.jmb.chapter08.numb3rs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Code_8_24 {
    
    private static int n, d, p, q;
    private static double[][] cache;
    // connected[i][j] = 마을 i, j가 연결되어 있나 여부
    private static int[][] connected;
    // deg[i] = 마을 i와 연결된 마을의 개수
    private static int[] deg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            connected = new int[n][];
            deg = new int[n];
            for (int i = 0; i < n; i++) {
                connected[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < n; j++) {
                    if (connected[i][j] == 1) {
                        deg[i] += 1;
                    }
                }
            }

            int t = Integer.parseInt(br.readLine());
            List<Double> results = new ArrayList<>();
            int[] qArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            cache = new double[n][d + 1];
            for (int j = 0; j < n; j++) {
                Arrays.fill(cache[j], -1);
            }
            for (int i = 0; i < t; i++) {
                q = qArr[i];
                results.add(search3(q, d));
            }

            System.out.println(
                    results.stream()
                            .map(prob -> String.format("%.8f", prob))
                            .collect(Collectors.joining(" "))
            );
        }
    }

    private static double search3(int here, int days) {
        // 기저 사례: 0일 째
        if (days == 0) {
            return here == p ? 1 : 0;
        }

        // 메모이제이션
        double ret = cache[here][days];
        if (ret != -1) {
            return ret;
        }

        ret = 0;
        for (int there = 0; there < n; there++) {
            if (connected[here][there] == 1) {
                ret += search3(there, days - 1) / deg[there];
            }
        }

        return cache[here][days] = ret;
    }

}
