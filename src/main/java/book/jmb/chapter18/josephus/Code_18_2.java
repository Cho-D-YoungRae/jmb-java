package book.jmb.chapter18.josephus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class List {
    Node first;
    Node last;

    void pushBack(int e) {
        Node newNode = new Node();
        Node l = last;
        newNode.e = e;
        newNode.prev = l;
        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    }

    Node begin() {
        return first;
    }

    Node erase(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        if (node == first) {
            first = next;
        }
        if (node == last) {
            last = prev;
        }
        return next;
    }
}

class Node {
    int e;
    Node prev;
    Node next;
}

public class Code_18_2 {

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
        // 리스트를 준비한다
        List survivors = new List();
        for (int i = 1; i <= n; i++) {
            survivors.pushBack(i);
        }

        // 이번에 죽을 사람을 나타내는 포인터
        Node kill = survivors.begin();
        while (n > 2) {
            // 첫 번째 사람이 자살한다. erase 는 지운 원소의 다음 원소를 반환한다.
            kill = survivors.erase(kill);
            if (kill == null) {
                kill = survivors.begin();
            }
            n -= 1;

            // k - 1 번 다음 사람으로 옮긴다.
            for (int i = 0; i < k - 1; i++) {
                kill = kill.next;
                if (kill == null) {
                    kill = survivors.begin();
                }
            }
        }

        System.out.println(
                survivors.first.e + " " + survivors.last.e
        );
    }
}
