package book.jmb.chapter19.ites;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class RNG {

    private long seed;

    public RNG() {
        this.seed = 1983;
    }

    public int next() {
        int ret = (int) (seed % 10000 + 1);
        seed = (seed * 214013 + 2531011) % (1L << 32);
        return ret;
    }
}

public class Code_19_5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(countRanges(k, n));
        }
    }

    private static int countRanges(int k, int n) {
        RNG rng = new RNG();    // 신호 값을 생성하는 난수 생성기
        Queue<Integer> range = new ArrayDeque<>();  // 현재 구간에 들어있는 숫자들을 저장하는 큐
        int ret = 0, rangeSum = 0;
        for (int i = 0; i < n; i++) {
            // 구간에 숫자를 추가한다.
            int newSignal = rng.next();
            rangeSum += newSignal;
            range.offer(newSignal);

            // 구간의 합이 k를 초과하는 동안 구간에서 숫자를 뺀다
            while (rangeSum > k) {
                rangeSum -= range.poll();
            }

            if (rangeSum == k) {
                ret++;
            }
        }

        return ret;
    }
}
