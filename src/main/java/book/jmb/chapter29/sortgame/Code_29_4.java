package book.jmb.chapter29.sortgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Code_29_4 {

    private static Map<List<Integer>, Integer> toSort = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 8; i++) {
            precalc(i);
        }

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(br.readLine());
            List<Integer> perm = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            System.out.println(solve(perm));
        }
    }

    // [0, ..., n-1]의 모든 순열에 대해 toSort[]를 계산한다.
    private static void precalc(int n) {
        List<Integer> perm = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            perm.add(i);
        }
        Queue<List<Integer>> q = new ArrayDeque<>();
        q.offer(perm);
        toSort.put(perm, 0);

        while (!q.isEmpty()) {
            List<Integer> here = q.poll();
            int cost = toSort.get(here);

            for (int i = 0; i < n; i++) {
                for (int j = i + 2; j <= n; j++) {
                    List<Integer> there = new ArrayList<>(here);
                    List<Integer> subList = new ArrayList<>(here.subList(i, j));
                    Collections.reverse(subList);
                    for (int k = 0; k < subList.size(); k++) {
                        there.set(i + k, subList.get(k));
                    }
                    if (!toSort.containsKey(there)) {
                        toSort.put(there, cost + 1);
                        q.offer(there);
                    }
                }
            }
        }
    }

    private static int solve(List<Integer> perm) {
        // perm 을 [0, ..., n-1] 의 순열로 변환한다.
        int n = perm.size();
        int[] fixed = new int[n];
        for (int i = 0; i < n; i++) {
            int smaller = 0;
            for (int j = 0; j < n; j++) {
                if (perm.get(j) < perm.get(i)) {
                    smaller++;
                }
            }
            fixed[i] = smaller;
        }

        return toSort.get(Arrays.stream(fixed).boxed().collect(Collectors.toList()));
    }
}
