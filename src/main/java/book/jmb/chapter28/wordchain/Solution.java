package book.jmb.chapter28.wordchain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    // 그래프의 인접 행렬 표현
    private static int[][] adj;

    // graph[i][j] = i로 시작해서 j로 끝나는 단어의 목록
    private static List<List<List<String>>> graph;

    // indegree[i] = i로 시작하는 단어의 수
    private static int[] indegree;

    // outdegree[i] = i로 끝나는 단어의 수
    private static int[] outdegree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(br.readLine());
            List<String> words = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                words.add(br.readLine());
            }
            System.out.println(solve(words));
        }
    }

    private static void makeGraph(List<String> words) {
        // 전역 변수 초기화
        graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < 26; j++) {
                graph.get(i).add(new ArrayList<>());
            }
        }
        adj = new int[26][26];
        indegree = new int[26];
        outdegree = new int[26];

        // 각 단어를 그래프에 추가한다.
        for (int i = 0; i < words.size(); i++) {
            int a = words.get(i).charAt(0) - 'a';
            int b = words.get(i).charAt(words.get(i).length() - 1) - 'a';
            graph.get(a).get(b).add(words.get(i));
            adj[a][b]++;
            outdegree[a]++;
            indegree[b]++;
        }
    }

    // 유향 그래프의 인점 행렬 adj가 주어질 때 오일러 서킷 혹은 트레일을 계산한다.
    private static void getEulerCircuit(int here, List<Integer> circuit) {
        for (int there = 0; there < adj.length; there++) {
            while (adj[here][there] > 0) {
                adj[here][there]--; // 간선을 지운다.
                getEulerCircuit(there, circuit);
            }
        }

        circuit.add(here);
    }

    // 현재 그래프의 오일러 트레일이나 서킷을 반환한다.
    private static List<Integer> getEulerTrailOrCircuit() {
        List<Integer> circuit = new ArrayList<>();

        // 우선 트레일을 찾아본다: 시작점이 존재하는 경우
        for (int i = 0; i < 26; i++) {
            if (outdegree[i] == indegree[i] + 1) {
                getEulerCircuit(i, circuit);
                return circuit;
            }
        }

        // 아니면 서킷이니, 간선에 인접한 아무 정점에서 시작한다.
        for (int i = 0; i < 26; i++) {
            if (outdegree[i] > 0) {
                getEulerCircuit(i, circuit);
                return circuit;
            }
        }

        // 모두 실패한 경우 빈 배열을 반환한다.
        return circuit;
    }

    // 현재 그래프의 오일러 서킷/트레일 존재 여부를 확인한다.
    private static boolean checkEuler() {
        // 예비 시작점과 끝점의 수
        int plus1 = 0, minus1 = 0;
        for (int i = 0; i < 26; i++) {
            int delta = outdegree[i] - indegree[i];
            // 모든 정점의 차수는 -1, 1 또는 0 이어야 한다.
            if (delta < -1 || 1 < delta) {
                return false;
            }
            if (delta == 1) {
                plus1++;
            }
            if (delta == -1) {
                minus1++;
            }
        }

        // 시작점과 끝점은 각 하나씩 있거나 하나도 없어야 한다.
        return (plus1 == 1 && minus1 == 1) || (plus1 == 0 && minus1 == 0);
    }

    private static String solve(List<String> words) {
        makeGraph(words);

        // 차수가 맞지 않으면 실패!
        if (!checkEuler()) {
            return "IMPOSSIBLE";
        }

        // 오일러 서킷이나 경로를 찾아낸다.
        List<Integer> circuit = getEulerTrailOrCircuit();

        // 모든 간선을 방문하지 못했으면 실패!
        if (circuit.size() != words.size() + 1) {
            return "IMPOSSIBLE";
        }

        // 아닌 경우 방문 순서를 뒤집은 뒤 간선들을 모아 문자열로 만들어 반환한다.
        Collections.reverse(circuit);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < circuit.size(); i++) {
            int a = circuit.get(i - 1);
            int b = circuit.get(i);
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(graph.get(a).get(b).get(graph.get(a).get(b).size() - 1));
            graph.get(a).get(b).remove(graph.get(a).get(b).size() - 1);
        }

        return sb.toString();
    }
}
