package book.jmb.chapter08.wildcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Final {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(bf.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            String w = bf.readLine();
            int n = Integer.parseInt(bf.readLine());
            List<String> filenames = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                filenames.add(bf.readLine());
            }

            filenames.stream()
                    .filter(filename -> matchMemoized(w, 0, filename, 0))
                    .sorted()
                    .forEach(System.out::println);
        }

    }

    // O(n^2)
    // W, S: 패턴과 문자열
    // 와일드카드 패턴 W[w..]가 문자열 S[s..]에 대응되는지 여부를 반환한다.
    private static boolean matchMemoized(String W, int w, String S, int s) {
        // -1은 아직 답이 계산되지 않았음을 의미한다.
        // 1은 해당 입력들이 서로 대응됨을 의미한다.
        // 0은 해당 입력들이 서로 대응되지 않음을 의미한다.
        int[][] cache = new int[101][101];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }

        return doMatchMemoized(cache, W, w, S, s);
    }

    private static boolean doMatchMemoized(int[][] cache, String W, int w, String S, int s) {
        // 메모이제이션
        int ret = cache[w][s];
        if (ret != -1) {
            return ret == 1;
        }

        if (s < S.length() && w < W.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            ret = matchMemoized(W, w + 1, S, s + 1) ? 1 : 0;
            cache[w][s] = ret;
            return ret == 1;
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
            if (doMatchMemoized(cache, W, w + 1, S, s)
                    || (s < S.length() && doMatchMemoized(cache, W, w, S, s + 1))) {
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
