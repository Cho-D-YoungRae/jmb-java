package book.jmb.chapter06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름: 소풍
 * 문제 링크: https://www.algospot.com/judge/problem/read/PICNIC
 * 교재: 1권 157pg
 */
public class Picnic {

    private static int n;
    private static boolean[][] areFriends;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < C; c++) {
            init(bf);

            sb.append(countPairings(new boolean[n]));
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void init(BufferedReader bf) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        areFriends = new boolean[n][n];

        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < m; i++) {
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            areFriends[f1][f2] = true;
            areFriends[f2][f1] = true;
        }
    }

    // taken[i] = i 번째 학생이 이미 짝을 찾았으면 true, 아니면 false
    private static int countPairings(boolean[] taken) {
        // 남은 학생들 중 가장 번호가 빠른 학생을 찾는다.
        int firstFree = -1;
        for (int i = 0; i < n; i++) {
            if (!taken[i]) {
                firstFree = i;
                break;
            }
        }

        // 기저 사례: 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
        if (firstFree == -1) {
            return 1;
        }
        int ret = 0;

        // 이 학생과 짝지을 학생을 결정한다.
        for (int pairWith = firstFree + 1; pairWith < n; pairWith++) {
            if (!taken[pairWith] && areFriends[firstFree][pairWith]) {
                taken[firstFree] = true;
                taken[pairWith] = true;

                ret += countPairings(taken);

                taken[firstFree] = false;
                taken[pairWith] = false;
            }
        }

        return ret;
    }
}

/*
3
2 1
0 1
4 6
0 1 1 2 2 3 3 0 0 2 1 3
6 10
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
 */