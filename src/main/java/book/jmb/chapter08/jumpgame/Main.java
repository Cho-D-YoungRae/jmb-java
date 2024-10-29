package book.jmb.chapter08.jumpgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(bf.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            int n = Integer.parseInt(bf.readLine());
            int[][] board = new int[n][];
            for (int i = 0; i < n; i++) {
                board[i] = Arrays.stream(bf.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            String result = jump2(board, 0, 0) == 1 ? "YES" : "NO";
            System.out.println(result);
        }
    }

    private static int jump2(int[][] board, int y, int x) {
        int n = board.length;
        int[][] cache = new int[n][n];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        return doJump2(board, cache, y, x);
    }

    private static int doJump2(int[][] board, int[][] cache, int y, int x) {
        int n = board.length;
        // 기저 사례 처리
        if (y >= n || x >= n) {
            return 0;
        }
        if (y == n - 1 && x == n - 1) {
            return 1;
        }

        int ret = cache[y][x];
        if (ret != -1) {
            return ret;
        }

        int jumpSize = board[y][x];
        ret = doJump2(board, cache, y + jumpSize, x) | doJump2(board, cache, y, x + jumpSize);
        cache[y][x] = ret;
        return ret;
    }

    private static boolean jump(int[][] board, int y, int x) {
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
        return jump(board, y + jumpSize, x) || jump(board, y, x + jumpSize);
    }

}
