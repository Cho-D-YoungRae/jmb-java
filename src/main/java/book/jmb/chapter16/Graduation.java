package book.jmb.chapter16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Graduation {

    private static final int MAXN = 12;
    private static final int INF = 987654321;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(bf.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            int[] prerequisites = new int[n];
            for (int i = 0; i < n; i++) {
                String[] prerequisite = bf.readLine().split(" ");
                for (int j = 1; j < prerequisite.length; j++) {
                    prerequisites[i] |= 1 << Integer.parseInt(prerequisite[j]);
                }
            }

            int[] classes = new int[m];
            for (int i = 0; i < m; i++) {
                String[] classInfo = bf.readLine().split(" ");
                for (int j = 1; j < classInfo.length; j++) {
                    classes[i] |= 1 << Integer.parseInt(classInfo[j]);
                }
            }

            int result = new Graduation(n, k, m, l, prerequisites, classes).graduate(0, 0);
            if (result >= INF) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(result);
            }
        }
    }

    private final int n, k, m, l;
    private final int[] prerequisites;
    // classes[i] = i번째 학기에 개설되는 과목의 집합
    private final int[] classes;
    private final int[][] cache = new int[10][1 << MAXN];

    public Graduation(int n, int k, int m, int l, int[] prerequisites, int[] classes) {
        this.n = n;
        this.k = k;
        this.m = m;
        this.l = l;
        this.prerequisites = prerequisites;
        this.classes = classes;

        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
    }

    // 이번 학기가 semester 이고, 지금까지 들은 과목의 집합이 taken 일 때
    // k개 이상의 과목을 모두 들으려면 몇 학기나 더 있어야 하는가?
    // 불가능한 경우 INF 를 반환한다.
    public int graduate(int semester, int taken) {
        // 기저 사례: k개 이상의 과목을 이미 들은 경우
        if (Integer.bitCount(taken) >= k) {
            return 0;
        }
        // 기저 사례: m 학기가 전부 지난 경우
        if (semester == m) {
            return INF;
        }

        // 메모제이션
        int ret = cache[semester][taken];
        if (ret != -1) {
            return ret;
        }

        ret = INF;
        // 이번 학기에 들을 수 있는 과목 중 아직 듣지 않은 과목을 찾는다.
        int canTake = (classes[semester] & ~taken);
        // 선수 과목을 다 듣지 않은 과목을 걸러낸다.
        for (int i = 0; i < n; i++) {
            if ((canTake & (1 << i)) != 0 && (taken & prerequisites[i]) != prerequisites[i]) {
                canTake &= ~(1 << i);
            }
        }

        // 이 집합의 모든 부분 집합을 순회한다.
        for (int take = canTake; take > 0; take = ((take - 1) & canTake)) {
            // 한 학기에 l 과목까지만 들을 수 있다.
            if (Integer.bitCount(take) > l) {
                continue;
            }
            ret = Math.min(ret, graduate(semester + 1, taken | take) + 1);
        }

        // 이번 학기에 아무 것도 듣지 않을 경우
        ret = Math.min(ret, graduate(semester + 1, taken));
        cache[semester][taken] = ret;
        return ret;
    }
}
