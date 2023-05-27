package book.jmb.chapter06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름: 게임판 덮기
 * 문제 링크: https://www.algospot.com/judge/problem/read/BOARDCOVER
 * 교재: 1권 161pg
 *
 * 우리는 최대 50/3 = 16 개의 블록을 놓기 때문에 가능한 답의 상한은 4^16 개가 될 것 같지만,
 * 실제 입력을 생각해보면 가능한 경우의 수가 많이 제한되기 때문에 완전탐색이 가능함
 */
public class BoardCover {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < C; c++) {
            int[][] board = init(bf);

            sb.append(cover(board));
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int[][] init(BufferedReader bf) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] board = new int[H][W];
        for (int i = 0; i < H; i++) {
            char[] row = bf.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                board[i][j] = row[j] == '#' ? 1 : 0;
            }
        }

        return board;
    }

    // 주어진 칸을 덮을 수 있는 네 가지 방법
    // 블록을 구성하는 세 칸의 상대적 위치 (dy, dx)의 목록
    private static final int[][][] coverType = {
            {{0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {0, 1}, {1, 1}},
            {{0, 0}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, -1}}
    };

    // board의 (y, x)를 type번 방법으로 덮거나, 덮었던 블록을 없앤다.
    // delta = 1이면 덮고, -1이면 덮었던 블록을 없앤다.
    // 만약 블록이 제대로 덮이지 않은 경우 (게임판 밖으로 나가거나, 겹치거나, 검은 칸을 덮을 때) false를 반환한다.
    private static boolean set(int[][] board, int y, int x, int type, int delta) {
        boolean ok = true;
        for (int i = 0; i < 3; i++) {
            final int ny = y + coverType[type][i][0];
            final int nx = x + coverType[type][i][1];
            if (ny < 0 || ny >= board.length || nx < 0 || nx >= board[0].length) {
                ok = false;
            } else if ((board[ny][nx] += delta) > 1) {
                ok = false;
            }
        }

        return ok;
    }

    // board의 모든 빈 칸을 덮을 수 있는 방법의 수를 반환한다.
    // board[i][j] = 1 이미 덮인 칸 혹은 검은 칸
    // board[i][j] = 0 아직 덮이지 않은 칸
    private static int cover(int[][] board) {
        // 아직 채우지 못한 칸 중 가장 윗줄 왼쪽에 있는 칸을 찾는다.
        /*
        게임판을 덮는 블럭의 크기가 제각각이라 블럭으로 덮은 후 다음 칸을 찾기가 어려운데
        게임판이 작기 때문에 전체를 돌면서 찾아도 괜찮다.
         */
        int y = -1;
        int x = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if (y != -1) {
                break;
            }
        }
        
        // 기저 사례: 모든 칸을 채웠으면 1을 반환한다.
        if (y == -1) {
            return 1;
        }

        int ret = 0;
        for (int type = 0; type < 4; type++) {
            // 만약 board[y][x]를 type 형태로 덮을 수 있으면 재귀 호출한다.
            if (set(board, y, x, type, 1)) {
                ret += cover(board);
            }
            // 덮었던 블록을 치운다.
            set(board, y, x, type, -1);
        }

        return ret;
    }
}

/*
3
3 7
#.....#
#.....#
##...##
3 7
#.....#
#.....#
##..###
8 10
##########
#........#
#........#
#........#
#........#
#........#
#........#
##########
 */