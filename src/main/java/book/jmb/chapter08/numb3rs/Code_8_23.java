package book.jmb.chapter08.numb3rs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Code_8_23 {
    
    private static int n, d, p, q;
    // cache[][]는 -1로 초기화해 둔다.
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
            for (int i = 0; i < t; i++) {
                cache = new double[n][d + 1];
                for (int j = 0; j < n; j++) {
                    Arrays.fill(cache[j], -1);
                }
                q = qArr[i];
                results.add(search2(p, 0));
            }

            System.out.println(
                    results.stream()
                            .map(prob -> String.format("%.8f", prob))
                            .collect(Collectors.joining(" "))
            );
        }
    }

    // days일째에 here번 마을에 숨어 있다고 가정하고,
    // 마지막 날에 q번 마을에 숨어 있을 조건부 확률을 반환한다.
    private static double search2(int here, int days) {
        if (days == d) {
            return here == q ? 1 : 0;
        }

        // 메모이제이션
        double ret = cache[here][days];
        if (ret > -1) {
            return ret;
        }

        ret = 0;
        for (int there = 0; there < n; there++) {
            if (connected[here][there] == 1) {
                ret += search2(there, days + 1) / deg[here];
            }
        }

        return cache[here][days] = ret;
    }

}
