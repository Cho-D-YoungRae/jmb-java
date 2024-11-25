package book.jmb.chapter22.insertion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

// 트립의 한 노드를 저장한다.
class Node<T extends Comparable<T>> {


    private static final Random random = new Random();

    // 노드에 저장된 원소
    private final T key;
    // 이 노드의 우선순위(priority)
    private final int priority;
    // 이 노드를 루트로 하는 서브트리의 크기(size)
    private int size;
    // 두 자식 노드의 포인터
    private Node<T> left;
    private Node<T> right;

    // 생성자에서 난수 우선순위를 생성하고, size와 left/right를 초기화한다.
    public Node(T key) {
        this.key = key;
        this.priority = random.nextInt();
        this.size = 1;
        this.left = null;
        this.right = null;
    }

    // root를 루트로 하는 트립에서 key를 지우고 결과 트립의 루트를 반환한다.
    public static <T extends Comparable<T>> Node<T> erase(Node<T> root, T key) {

        if (root == null) {
            return null;
        }

        if (root.key.compareTo(key) == 0) {
            Node<T> ret = merge(root.left, root.right);
            return ret;
        }

        if (root.key.compareTo(key) > 0) {
            root.setLeft(erase(root.left, key));
        } else {
            root.setRight(erase(root.right, key));
        }

        return root;
    }


    // a와 b가 트립이고, max(a) < max(b) 일 때 이 둘을 합친다.
    private static <T extends Comparable<T>> Node<T> merge(Node<T> a, Node<T> b) {

        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        if (a.priority < b.priority) {
            b.setLeft(merge(a, b.left));
            return b;
        }

        a.setRight(merge(a.right, b));
        return a;
    }

    // root를 루트로 하는 트립에 새 노드 node를 삽입한 뒤 결과 트립의 루트를 반환한다.
    public static <T extends Comparable<T>> Node<T> insert(Node<T> root, Node<T> node) {

        if (root == null) {
            return node;
        }

        if (root.priority < node.priority) {
            Pair<Node<T>, Node<T>> splitted = split(root, node.key);
            node.setLeft(splitted.getFirst());
            node.setRight(splitted.getSecond());
            return node;
        } else if (node.key.compareTo(root.key) < 0) {
            root.setLeft(insert(root.left, node));
        } else {
            root.setRight(insert(root.right, node));
        }

        return root;
    }

    // root를 루트로 하는 트립을 key 미만의 값과 이상의 값을 갖는 두 개의 트립으로 분리한다.
    private static <T extends Comparable<T>> Pair<Node<T>, Node<T>> split(Node<T> root, T key) {

        if (root == null) {
            return Pair.empty();
        }

        if (root.key.compareTo(key) < 0) {
            Pair<Node<T>, Node<T>> rs = split(root.right, key);
            root.setRight(rs.getFirst());
            return new Pair<>(root, rs.getSecond());
        }

        Pair<Node<T>, Node<T>> ls = split(root.left, key);
        root.setLeft(ls.getSecond());
        return new Pair<>(ls.getFirst(), root);
    }

    // root를 루트로 하는 트리 중에서 k번째 원소를 반환한다.
    public static <T extends Comparable<T>> Node<T> kth(Node<T> root, int k) {

        int leftSize = 0;
        if (root.left != null) {
            leftSize = root.left.size;
        }

        if (k <= leftSize) {
            return kth(root.left, k);
        }

        if (k == leftSize + 1) {
            return root;
        }

        return kth(root.right, k - leftSize - 1);
    }

    // key보다 작은 키값의 수를 반환한다.
    public static <T extends Comparable<T>> int countLessThan(Node<T> root, T key) {

        if (root == null) {
            return 0;
        }

        if (root.key.compareTo(key) >= 0) {
            return countLessThan(root.left, key);
        }

        int ls = (root.left != null) ? root.left.size : 0;
        return ls + 1 + countLessThan(root.right, key);
    }

    private void setLeft(Node<T> newLeft) {
        this.left = newLeft;
        calcSize();
    }

    private void setRight(Node<T> newRight) {
        this.right = newRight;
        calcSize();
    }

    // size 멤버를 갱신한다.
    private void calcSize() {
        size = 1;
        if (left != null) {
            size += left.getSize();
        }
        if (right != null) {
            size += right.getSize();
        }
    }

    public T getKey() {
        return key;
    }

    public int getSize() {
        return size;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }
}

class Pair<T1, T2> {

    private final T1 first;
    private final T2 second;

    public static <T1, T2> Pair<T1, T2> empty() {
        return new Pair<>(null, null);
    }

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }
}

public class Solution {

    private static int n;
    // shifted[i] = A[i]가 왼쪽으로 몇 칸 움직이는가?
    private static int[] shifted;
    private static int[] A;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            n = Integer.parseInt(br.readLine());
            shifted = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            A = new int[n];
            solve();
            System.out.println(Arrays.stream(A)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ")));
        }
    }

    // n, shifted[]를 보고 A[]에 값을 저장한다.
    private static void solve() {
        // 1 ~ N까지의 숫자를 모두 저장하는 트립을 만든다.
        Node<Integer> candidates = null;
        for (int i = 0; i < n; i++) {
            candidates = Node.insert(candidates, new Node<>(i + 1));
        }
        // 뒤에서부터 A[]를 채워나간다.
        for (int i = n - 1; i >= 0; i--) {
            // 후보 중 이 수보다 큰 수가 larger개 있다.
            int larger = shifted[i];
            Node<Integer> k = Node.kth(candidates, i + 1 - larger);
            A[i] = k.getKey();
            candidates = Node.erase(candidates, k.getKey());
        }
    }

}
