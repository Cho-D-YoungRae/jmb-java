package book.jmb.chapter08.wildcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Code_8_6 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            String w = br.readLine();
            int n = Integer.parseInt(br.readLine());
            List<String> filenames = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                filenames.add(br.readLine());
            }

            filenames.stream()
                    .filter(filename -> match(w, filename))
                    .sorted()
                    .forEach(System.out::println);
        }

    }

    // 코드 8.6
    // 와일드카드 패턴 w가 문자열 s에 대응되는지 여부를 반환한다.
    private static boolean match(String w, String s) {
        // w[pos]와 s[pos]를 맞춰나간다.
        int pos = 0;
        while (pos < s.length() && pos < w.length() && (w.charAt(pos) == '?' || w.charAt(pos) == s.charAt(pos))) {
            pos += 1;
        }

        // 더이상 대응할 수 없으면 왜 while 문이 끝났는지 확인한다.
        // 2. 패턴 끝에 도달해서 끝난 경우: 문자열도 끝났어야 대응됨
        if (pos == w.length()) {
            return pos == s.length();
        }

        // 4. *를 만나서 끝난 경우: *에 몇 글자를 대응해야 할지 재귀 호출하면서 확인한다.
        if (w.charAt(pos) == '*') {
            for (int skip = 0; pos + skip <= s.length(); skip++) {
                if (match(w.substring(pos + 1), s.substring(pos + skip))) {
                    return true;
                }
            }
        }

        // 이 외의 경우에는 모두 대응되지 않는다.
        return false;
    }

}
