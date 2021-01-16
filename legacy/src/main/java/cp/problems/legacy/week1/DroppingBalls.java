package cp.problems.legacy.week1;

import java.util.Scanner;

public class DroppingBalls {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            int depth = s.nextInt();
            int ball = s.nextInt() - 1;

            int res = 1;

            while (depth-- > 1) {
                if (ball % 2 == 0) {
                    res *= 2;
                } else {
                    res = 2 * res + 1;
                }
                ball /= 2;
            }

            System.out.println(res);
        }
        s.nextInt();
    }
}
