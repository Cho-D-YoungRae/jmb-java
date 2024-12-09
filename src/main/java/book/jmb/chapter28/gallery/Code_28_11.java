package book.jmb.chapter28.gallery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Code_28_11 {

    private static int V;
    private static List<List<Integer>> adj;
    private static boolean[] visited;

    private static final int UNWATCHED = 0;
    private static final int WATCHED = 1;
    private static final int INSTALLED = 2;

    // 지금까지 설치한 카메라의 총 수
    private static int installed;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            V = g;
            visited = new boolean[g];
            adj = new ArrayList<>();
            for (int i = 0; i < g; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adj.get(a).add(b);
                adj.get(b).add(a);
            }

            System.out.println(installCamera());
        }
    }

    // here 로부터 깊이 우선 탐색을 하고, here의 정보를 반환한다.
    private static int dfs(int here) {
        visited[here] = true;
        int[] children = new int[]{0, 0, 0};
        for (int i = 0; i < adj.get(here).size(); i++) {
            int there = adj.get(here).get(i);
            if (!visited[there]) {
                children[dfs(there)] += 1;
            }
        }

        // 자손 노드 중 감시되지 않는 노드가 있을 경우 카메라를 설치한다.
        if (children[UNWATCHED] > 0) {
            installed += 1;
            return INSTALLED;
        }

        // 자손 노드 중 카메라가 설치된 노드가 있을 경우 설치할 필요가 없다.
        if (children[INSTALLED] > 0) {
            return WATCHED;
        }

        return UNWATCHED;
    }

    // 그래프를 감시하는 데 필요한 카메라의 최소 수를 반환한다.
    private static int installCamera() {
        installed = 0;
        visited = new boolean[V];
        for (int u = 0; u < V; u++) {
            if (!visited[u] && dfs(u) == UNWATCHED) {
                installed += 1;
            }

        }

        return installed;
    }
}
