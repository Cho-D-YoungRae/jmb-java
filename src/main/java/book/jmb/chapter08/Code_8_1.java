package book.jmb.chapter08;

public class Code_8_1 {

    public static int bino(int n, int r) {
        // 기저 사례: n=r (모든 원소를 다 고르는 경우) 혹은 r=0 (고를 원소가 없는 경우)
        if (r == 0 || n == r) {
            return 1;
        }

        return bino(n - 1, r - 1) + bino(n - 1, r);
    }
}
