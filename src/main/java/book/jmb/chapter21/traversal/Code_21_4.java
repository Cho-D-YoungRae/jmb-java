package book.jmb.chapter21.traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Code_21_4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(br.readLine());
            List<Integer> preorder = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            List<Integer> inorder = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            printPostOrder(preorder, inorder);
            System.out.println();
        }
    }

    // 트리의 전위탐색 결과와 중위탐색 결과가 주어질 때 후위탐색 결과를 출력한다.
    private static void printPostOrder(List<Integer> preorder, List<Integer> inorder) {
        // 트리에 포함된 노드의 수
        int N = preorder.size();
        // 기저 사례: 텅빈 트리면 곧장 종료
        if (preorder.isEmpty()) {
            return;
        }
        // 이 트리의 루트는 전위 탐색 결과로부터 곧장 알 수 있다.
        int root = preorder.get(0);

        // 이 트리의 왼쪽 서브트리의 크기는 중위 탐색 결과에서 루트의 위치를 찾아서 알 수 있다.
        int L = inorder.indexOf(root);

        // 오른쪽 서브트리의 크기는 N에서 왼쪽 서브트리와 루트를 빼면 알 수 있다.
        int R = N - 1 - L;

        // 왼쪽과 오른쪽 서브트리의 순회 결과를 출력
        printPostOrder(preorder.subList(1, L + 1), inorder.subList(0, L));
        printPostOrder(preorder.subList(L + 1, N), inorder.subList(L + 1, N));

        // 후위 순회이므로 루트를 갖아 마지막에 출력한다.
        System.out.print(root + " ");
    }
}
