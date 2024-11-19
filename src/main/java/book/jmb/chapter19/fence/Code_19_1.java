package book.jmb.chapter19.fence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.max;

public class Code_19_1 {

    // 각 판자의 높이를 저장하는 배열
    private static List<Integer> h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(br.readLine());
            h = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            System.out.println(solveStack());
        }
    }

    // 스택을 사용한 O(n) 해법
    private static int solveStack() {
        // 남아 있는 판자들의 위치들을 저장한다.
        Deque<Integer> remaining = new ArrayDeque<>();
        h.add(0);
        int ret = 0;
        for (int i = 0; i < h.size(); i++) {
            // 남아 있는 판자들 중 오른쪽 끝 판자가 h[i]보다 높다면
            // 이 판자의 최대 사각형은 i에서 끝난다
            while (!remaining.isEmpty() && h.get(remaining.peekLast()) >= h.get(i)) {
                int j = remaining.pollLast();
                // j번째 판자 왼쪽에 판자가 하나도 안 남아 있을 경우 left[j] = -1,
                // 아닌 경우 left[j]=남아 있는 판자 중 가장 오른쪽에 있는 판자의 번호가 된다.
                int width = remaining.isEmpty() ? i : i - remaining.peekLast() - 1;
                ret = max(ret, h.get(j) * width);
            }

            remaining.offerLast(i);
        }

        return ret;
    }
}
