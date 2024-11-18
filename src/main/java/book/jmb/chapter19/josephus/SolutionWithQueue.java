package book.jmb.chapter19.josephus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class SolutionWithQueue {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            josephus(n, k);
        }
    }

    private static void josephus(int n, int k) {
        Queue<Integer> survivors = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            survivors.offer(i);
        }

        while (survivors.size() > 2) {
            survivors.poll();
            for (int i = 0; i < k - 1; i++) {
                survivors.offer(survivors.poll());
            }
        }

        List<Integer> result = survivors.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(
                result.get(0) + " " + result.get(1)
        );

    }
}
