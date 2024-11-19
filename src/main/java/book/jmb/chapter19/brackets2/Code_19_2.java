package book.jmb.chapter19.brackets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Code_19_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            String formula = br.readLine();
            String result = webMatched(formula) ? "YES" : "NO";
            System.out.println(result);
        }
    }


    private static boolean webMatched(String formula) {
        // 여는 괄호 문자들과 닫는 괄호 문자들
        String opening = "({[", closing = ")}]";
        // 이미 열린 괄호들을 순서대로 담는 스택
        Deque<Character> openStack = new ArrayDeque<>();
        for (int i = 0; i < formula.length(); i++) {
            // 여는 괄호인지 닫는 괄호인지 확인한다
            if (opening.indexOf(formula.charAt(i)) != -1) {
                // 여는 괄호라면 무조건 스택에 집어넣는다.
                openStack.push(formula.charAt(i));
            } else {
                // 이 외의 경우 스택 맨 위의 문자와 맞춰보자.
                // 스택이 비어 있는 경우에는 실패
                if (openStack.isEmpty()) {
                    return false;
                }
                // 서로 짝이 맞지 않아도 실패
                if (opening.indexOf(openStack.pop()) != closing.indexOf(formula.charAt(i))) {
                    return false;
                }
            }
        }

        // 닫히지 않은 괄호가 없어야 성공
        return openStack.isEmpty();
    }
}
