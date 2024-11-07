package book.jmb.chapter08.numb3rs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Code_8_22 {
    
    private static int n, d, p, q;
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
                q = qArr[i];
                List<Integer> path = new ArrayList<>();
                path.add(p);
                results.add(search(path));
            }

            System.out.println(
                    results.stream()
                            .map(prob -> String.format("%.8f", prob))
                            .collect(Collectors.joining(" "))
            );
        }
    }

    private static double search(List<Integer> path) {
        // 기저 사례: d일이 지난 경우
        if (path.size() == d + 1) {
            // 이 시나리오가 q에서 끝나지 않는다면 무효
            if (path.get(path.size() - 1) != q) {
                return 0;
            }
            // path의 출현 확률을 계산한다.
            double ret = 1;
            for (int i = 0; i + 1 < path.size(); i++) {
                ret /= deg[path.get(i)];
            }

            return ret;
        }

        double ret = 0;
        // 경로의 다음 위치를 결정한다.
        for (int there = 0; there < n; there++) {
            if (connected[path.get(path.size() - 1)][there] == 1) {
                path.add(there);
                ret += search(path);
                path.remove(path.size() - 1);
            }
        }

        return ret;
    }

}
