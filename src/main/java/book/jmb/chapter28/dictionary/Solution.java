package book.jmb.chapter28.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    // 알파벳의 각 글자에 대한 인접 행렬 표현
    // 간선(i, j)는 알파벳 i가 j보다 앞에 와야 함을 나타낸다.
    private static boolean[][] adj;
    private static boolean[] seen;
    private static List<Integer> order;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(br.readLine());
            List<String> words = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                words.add(br.readLine());
            }
            makeGraph(words);
            List<Integer> result = topologicalSort();
            if (result.isEmpty()) {
                System.out.println("INVALID HYPOTHESIS");
            } else {
                StringBuilder sb = new StringBuilder();
                result.stream()
                        .map(i -> (char) (i + 'a'))
                        .forEach(sb::append);
                System.out.println(sb);
            }
        }
    }

    // 주어진 단어들로부터 알파벳 간의 선후관계 그래프를 생성한다.
    private static void makeGraph(List<String> words) {
        adj = new boolean[26][26];
        for (int j = 1; j < words.size(); j++) {
            int i = j - 1;
            int len = Math.min(words.get(i).length(), words.get(j).length());
            // word[i] 가 word[j] 앞에 오는 이유를 찾는다.
            for (int k = 0; k < len; k++) {
                if (words.get(i).charAt(k) != words.get(j).charAt(k)) {
                    int a = words.get(i).charAt(k) - 'a';
                    int b = words.get(j).charAt(k) - 'a';
                    adj[a][b] = true;
                    break;
                }
            }
        }
    }

    // adj에 주어진 그래프를 위상정렬한 결과를 반환한다.
    // 그래프가 DAG가 아니라면 빈 벡터를 반환한다.
    private static List<Integer> topologicalSort() {
        int m = adj.length;
        seen = new boolean[m];
        order = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            if (!seen[i]) {
                dfs(i);
            }
        }
        Collections.reverse(order);

        // 만약 그래프가 DAG가 아니라면 정렬 결과에 역방향 간선이 있다.
        for (int i = 0; i < order.size(); i++) {
            for (int j = i + 1; j < order.size(); j++) {
                if (adj[order.get(j)][order.get(i)]) {
                    return new ArrayList<>();
                }
            }
        }

        // 없는 경우라면 깊이 우선 탐색에서 얻은 순서를 반환한다.
        return order;
    }

    private static void dfs(int here) {
        seen[here] = true;
        for (int there = 0; there < adj[here].length; there++) {
            if (adj[here][there] && !seen[there]) {
                dfs(there);
            }
        }

        order.add(here);
    }

}
