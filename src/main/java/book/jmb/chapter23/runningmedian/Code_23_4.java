package book.jmb.chapter23.runningmedian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Code_23_4 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(runningMedian(n, new RNG(a, b)));
        }
    }

    private static int runningMedian(int n, RNG rng) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int ret = 0;
        // 반복문 불변식:
        // 1. maxHeap의 크기는 minHeap의 크기와 같거나 1 더 크다
        // 2. maxHeap.top() <= minHeap.top()
        for (int cnt = 1; cnt <= n; cnt++) {
            // 우선 1번 불변식부터 만족 시킨다.
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.offer(rng.next());
            } else {
                minHeap.offer(rng.next());
            }

            // 2번 불변식이 깨졌을 경우 복구하자
            if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
                int a = maxHeap.poll();
                int b = minHeap.poll();
                maxHeap.offer(b);
                minHeap.offer(a);
            }

            ret = (ret + maxHeap.peek()) % 20090711;
        }

        return ret;
    }
}
