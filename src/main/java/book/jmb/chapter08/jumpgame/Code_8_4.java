package book.jmb.chapter08.jumpgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Code_8_4 {

    private static int n;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(bf.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            n = Integer.parseInt(bf.readLine());
            board = new int[n][];
            for (int i = 0; i < n; i++) {
                board[i] = Arrays.stream(bf.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            String result = jump(0, 0) ? "YES" : "NO";
            System.out.println(result);
        }
    }

    private static boolean jump(int y, int x) {
        int n = board.length;
        // 기저 사례: 게임판 밖을 벗어난 경우
        if (y >= n || x >= n) {
            return false;
        }
        // 기저 사례: 마지막 칸에 도착한 경우
        if (y == n - 1 && x == n - 1) {
            return true;
        }

        int jumpSize = board[y][x];
        return jump(y + jumpSize, x) || jump(y, x + jumpSize);
    }

}
