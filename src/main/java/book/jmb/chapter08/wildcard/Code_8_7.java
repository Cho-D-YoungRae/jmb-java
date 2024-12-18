package book.jmb.chapter08.wildcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Code_8_7 {

    // -1은 아직 답이 계산되지 않았음을 의미한다.
    // 1은 해당 입력들이 서로 대응됨을 의미한다.
    // 0은 해당 입력들이 서로 대응되지 않음을 의미한다.
    private static int[][] cache;
    // W, S: 패턴과 문자열
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

                if (matchMemoized(0, 0)) {
                    result.add(S);
                }
            }

            result.sort(Comparator.naturalOrder());
            result.forEach(System.out::println);
        }

    }

    private static boolean matchMemoized(int w, int s) {
        // 메모이제이션
        int ret = cache[w][s];
        if (ret != -1) {
            return ret == 1;
        }

        // W[w]와 S[s]를 맞춰나간다.
        while (s < S.length() && w < W.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            w += 1;
            s += 1;
        }

        // 더이상 대응할 수 없으면 왜 while문이 끝났는지 확인한다
        // 2. 패턴 끝에 도달해서 끝난 경우: 문자열도 끝났어야 참
        if (w == W.length()) {
            ret = s == S.length() ? 1 : 0;
            cache[w][s] = ret;
            return ret == 1;
        }

        // 4. *를 만나서 끝난 경우: *에 몇 글자를 대응해야 할지 재귀 호출하면서 확인한다.
        if (W.charAt(w) == '*') {
            for (int skip = 0; s + skip <= S.length(); skip++) {
                if (matchMemoized(w + 1, s + skip)) {
                    ret = 1;
                    cache[w][s] = ret;
                    return true;
                }
            }
        }

        ret = 0;
        cache[w][s] = ret;
        return false;
    }

}
