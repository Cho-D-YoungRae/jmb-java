package book.jmb.chapter08.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.max;

public class Code_8_10 {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            n = Integer.parseInt(br.readLine());
            List<Integer> sequence = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            System.out.println(lis(sequence));
        }

    }

    private static int lis(List<Integer> A) {
        // 기저 사례: A 가 텅 비어 있을 떄
        if (A.isEmpty()) {
            return 0;
        }

        int ret = 0;
        for (int i = 0; i < A.size(); i++) {
            List<Integer> B = new ArrayList<>();
            for (int j = i + 1; j < A.size(); j++) {
                if (A.get(i) < A.get(j)) {
                    B.add(A.get(j));
                }
            }

            ret = max(ret, 1 + lis(B));
        }

        return ret;
    }


}
