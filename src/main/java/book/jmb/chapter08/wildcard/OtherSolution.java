package book.jmb.chapter08.wildcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OtherSolution {

    private static int[][] cache;
    private static String W, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            W = br.readLine();
            int n = Integer.parseInt(br.readLine());

            List<String> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                S = br.readLine();
                cache = new int[W.length() + 1][S.length() + 1];
                for (int[] row : cache) {
                    Arrays.fill(row, -1);
                }

                if (match(0, 0)) {
                    result.add(S);
                }
            }

            result.sort(Comparator.naturalOrder());
            result.forEach(System.out::println);
        }

    }

    private static boolean match(int w, int s) {
        // 메모이제이션
        int ret = cache[w][s];
        if (ret != -1) {
            return ret == 1;
        }

        if (s < S.length() && w < W.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            ret = match(w + 1, s + 1) ? 1 : 0;
            cache[w][s] = ret;
            return ret == 1;
        }

        if (w == W.length()) {
            ret = s == S.length() ? 1 : 0;
            cache[w][s] = ret;
            return ret == 1;
        }

        if (W.charAt(w) == '*') {
            if (match(w + 1, s)
                    || (s < S.length() && match(w, s + 1))) {
                ret = 1;
                cache[w][s] = ret;
                return true;
            }
        }

        ret = 0;
        cache[w][s] = ret;
        return false;
    }

}
