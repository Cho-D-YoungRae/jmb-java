package book.jmb.chapter22.nerd2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution {

    // 현재 다른 점에 지배당하지 않는 점들의 목록을 저장한다.
    // coords[x] = y
    private static TreeMap<Integer, Integer> coords;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            coords = new TreeMap<>();
            int result = 0;
            int n = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
                result += registered(p, q);
            }

            System.out.println(result);

        }
    }

    // 새로운 점 (x,y)가 기존의 다른 점들에 지배당하는지 확인한다.
    private static boolean isDominated(int x, int y) {
        // x보다 오른쪽에 있는 점 중 가장 왼쪽에 있는 점을 찾는다.
        Map.Entry<Integer, Integer> entry = coords.higherEntry(x);
        // 그런 점이 없으면 (x,y)는 지배당하지 않는다.
        if (entry == null) {
            return false;
        }

        // 이 점은 x보다 오른쪽에 있는 점 중 가장 위에 있는 점이므로,
        // (x,y)가 어느 점에 지배되려면 이 점에도 지배되어야 한다.
        return y < entry.getValue();
    }

    // 새로운 점 (x,y)에 지배당하는 점들을 트리에서 지운다.
    private static void removeDominated(int x, int y) {
        Integer it = coords.lowerKey(x);
        // (x,y)보다 왼쪽에 있는 점이 없다!
        if (it == null) {
            return;
        }

        // 반복문 불변식: it는 (x,y)바로 왼쪽에 있는 점.
        while (true) {
            // (x,y) 바로 왼쪽에 오는 점을 찾는다.
            // it가 표시하는 점이 (x,y)에 지배되지 않는다면 곧장 종료
            if (coords.get(it) > y) {
                break;
            }

            // 이전 점이 더 없으므로 it만 지우고 종료한다.
            if (it.equals(coords.firstKey())) {
                coords.remove(it);
                break;
            } else { // 이전 점으로 이터레이터를 하나 옮겨 놓고 it를 지운다.
                Integer jt = coords.lowerKey(it);
                coords.remove(it);
                it = jt;
            }
        }
    }

    // 새 점 (x,y)가 추가되었을 때 coords를 갱신하고,
    // 다른 점에 지배당하지 않는 점들의 개수를 반환한다.
    private static int registered(int x, int y) {
        // (x,y)가 이미 지배당하는 경우에는 그냥 (x,y)를 버린다.
        if (isDominated(x, y)) {
            return coords.size();
        }
        // 기존에 있던 점 중 (x,y)에 지배당하는 점들을 지운다.
        removeDominated(x, y);
        coords.put(x, y);
        return coords.size();
    }
}
