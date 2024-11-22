package book.jmb.chapter21.fortress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class TreeNode {
    final List<TreeNode> children = new ArrayList<>();
}

public class Solution {

    // 입력 데이터
    private static int n;
    private static int[] y, x, radius;
    // 지금까지 찾은 가장 긴 잎-잎 경로의 길이를 저장한다.
    private static int longest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            n = Integer.parseInt(br.readLine().trim());
            y = new int[n];
            x = new int[n];
            radius = new int[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
                radius[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(solve(getTree(0)));
        }
    }

    // 두 노드 사이의 가장 긴 경로의 길이를 계산한다.
    private static int solve(TreeNode root) {
        longest = 0;
        // 트리의 높이와 최대 잎-잎 경로 길이 중 큰 것을 선택한다.
        int h = height(root);
        return Math.max(longest, h);
    }

    // root를 루트로 하는 서브 트리의 높이를 계산한다.
    private static int height(TreeNode root) {

        // 각 자식을 루트로 하는 서브트리의 높이를 계산한다.
        List<Integer> heights = new ArrayList<>();
        for (TreeNode child : root.children) {
            heights.add(height(child));
        }

        // 만약 자식이 하나도 없다면 0을 반환한다.
        if (heights.isEmpty()) {
            return 0;
        }
        Collections.sort(heights);
        // root 를 최상위 노드로 하는 경로를 고려하자.
        if (heights.size() >= 2) {
            longest = Math.max(longest,
                    2 + heights.get(heights.size() - 2) + heights.get(heights.size() - 1));
        }

        // 트리의 높이는 서브트리 높이의 최대치에 1을 더해 계산한다.
        return heights.get(heights.size() - 1) + 1;
    }

    // root 성벽을 루트로 하는 트리를 생성한다.
    private static TreeNode getTree(int root) {
        TreeNode ret = new TreeNode();
        for (int ch = 0; ch < n; ch++) {
            // ch 성벽이 root 성벽에 직접적으로 포함되어 있다면
            // 트리를 만들고 자손 목록에 추가한다.
            if (isChild(root, ch)) {
                ret.children.add(getTree(ch));
            }
        }

        return ret;
    }

    // '성벽'트리에서 parent가 child의 부모인지 확인한다.
    // parent는 child를 꼭 직접 포함해야 한다.
    private static boolean isChild(int parent, int child) {
        if (!encloses(parent, child)) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (i != parent && i != child &&
                    encloses(parent, i) && encloses(i, child)) {
                return false;
            }
        }

        return true;
    }

    // 성벽 a가 성벽 b를 포함하는지 확인한다.
    private static boolean encloses(int a, int b) {
        return radius[a] > radius[b] &&
                sqrdist(a, b) < sqr(radius[a] - radius[b]);
    }

    // 두 성벽 a, b의 중심점 간의 거리의 제곱을 반환한다.
    private static int sqrdist(int a, int b) {
        return sqr(y[a] - y[b]) + sqr(x[a] - x[b]);
    }

    // x^2 를 반환한다.
    private static int sqr(int x) {
        return x * x;
    }
}
