package book.jmb.chapter08.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Code_8_14 {

    private static final int INF = 987654321;

    private static String N;
    private static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine().trim());

        for (int testcase = 0; testcase < c; testcase++) {
            N = br.readLine().trim();
            cache = new int[N.length()];
            Arrays.fill(cache, -1);
            System.out.println(memorize(0));
        }
    }

    // 수열 N[begin..]를 외우는 방법 중 난이도의 최소 합을 출력한다.
    private static int memorize(int begin) {
        // 기저 사례: 수열의 끝에 도달했을 경우
        if (begin == N.length()) {
            return 0;
        }

        // 메모이제이션
        int ret = cache[begin];
        if (ret != -1) {
            return ret;
        }

        ret = INF;
        for (int L = 3; L <= 5; L++) {
            if (begin + L <= N.length()) {
                ret = min(ret, memorize(begin + L) + classify(begin, begin + L - 1));
            }
        }

        return cache[begin] = ret;
    }

    // N[a..b] 구간의 난이도를 반환한다.
    private static int classify(int a, int b) {
        // 숫자 조각을 가져온다.
        String M = N.substring(a, b + 1);

        // 첫 글자 만으로 이루어진 문자열과 같으면 난이도는 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M.length(); i++) {
            sb.append(M.charAt(0));
        }
        if (M.equals(sb.toString())) {
            return 1;
        }

        // 등차수열인지 검사한다.
        boolean progressive = true;
        for (int i = 0; i < M.length() - 1; i++) {
            if (M.charAt(i + 1) - M.charAt(i) != M.charAt(1) - M.charAt(0)) {
                progressive = false;
            }
        }

        // 등차수열이고 공차가 1 혹은 -1이면 난이도는 2
        if (progressive && abs(M.charAt(1) - M.charAt(0)) == 1) {
            return 2;
        }

        // 두 수가 번갈아 등장하는지 확인한다.
        boolean alternating = true;
        for (int i = 0; i < M.length(); i++) {
            if (M.charAt(i) != M.charAt(i % 2)) {
                alternating = false;
            }
        }

        if (alternating) {  // 두 수가 번갈아 등장하면 난이도는 4
            return 4;
        }
        if (progressive) {  // 공차가 1 아닌 등차수열의 난이도는 5
            return 5;
        }

        return 10;
    }
}
