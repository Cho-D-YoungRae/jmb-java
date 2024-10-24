package book.jmb.chapter07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quadtree {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(bf.readLine());

        for (int testcase = 0; testcase < c; testcase++) {
            String quadtreeImage = bf.readLine();
            System.out.println(reverse(quadtreeImage));
        }
    }

    public static String reverse(String quadtreeImage) {
        return doReverse(quadtreeImage, 0);
    }

    private static String doReverse(String quadtreeImage, int idx) {
        char head = quadtreeImage.charAt(idx);
        if (head == 'b' || head == 'w') {
            return quadtreeImage.substring(idx, idx + 1);
        }

        idx += 1;
        String upperLeft = doReverse(quadtreeImage, idx);
        idx += upperLeft.length();
        String upperRight = doReverse(quadtreeImage, idx);
        idx += upperRight.length();
        String lowerLeft = doReverse(quadtreeImage, idx);
        idx += lowerLeft.length();
        String lowerRight = doReverse(quadtreeImage, idx);

        return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
    }
}
