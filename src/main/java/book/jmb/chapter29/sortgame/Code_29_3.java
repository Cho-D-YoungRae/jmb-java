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

public class Code_29_3 {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(br.readLine());
            List<Integer> perm = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            System.out.println(bfs(perm));
        }
    }

    // perm을 정렬하기 위해 필요한 최소 뒤집기 연산의 수를 계산한다.
    private static int bfs(List<Integer> perm) {
        int n = perm.size();
        // 목표 정점을 미리 계산한다.
        List<Integer> sorted = perm.stream().sorted().collect(Collectors.toList());

        // 방문 목록(큐)과 시작점부터 각 정점까지의 거리
        Queue<List<Integer>> q = new ArrayDeque<>();
        Map<List<Integer>, Integer> distance = new HashMap<>();

        // 시작점을 큐에 넣는다.
        distance.put(perm, 0);
        q.offer(perm);

        while (!q.isEmpty()) {
            List<Integer> here = q.poll();
            // 목표 정점을 발견했으면 곧장 종료한다.
            if (here.equals(sorted)) {
                return distance.get(here);
            }
            int cost = distance.get(here);

            for (int i = 0; i < n; i++) {
                for (int j = i + 2; j <= n; j++) {
                    List<Integer> there = new ArrayList<>(here);
                    List<Integer> subList = new ArrayList<>(here.subList(i, j));
                    Collections.reverse(subList);

                    for (int k = 0; k < subList.size(); k++) {
                        there.set(i + k, subList.get(k));
                    }

                    if (!distance.containsKey(there)) {
                        distance.put(there, cost + 1);
                        q.offer(there);
                    }
                }
            }
        }

        // oops
        return -1;
    }
}
