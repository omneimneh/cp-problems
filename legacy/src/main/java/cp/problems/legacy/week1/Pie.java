package cp.problems.legacy.week1;

import java.util.Scanner;

import static java.lang.Math.PI;
import static java.lang.Math.floor;

public class Pie {
    private static int n;
    private static int f;
    private static final int[] r = new int[(int) 1e4 + 3];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            n = in.nextInt();
            f = in.nextInt();
            for (int i = 0; i < n; i++) {
                r[i] = in.nextInt();
            }
            System.out.printf("%.4f\n", bs());
        }

    }

    private static double bs() {
        double st = 0, sz = 1e9;
        for (sz *= .5; sz > 1e-9; sz *= .5) {
            if (ok(st + sz))
                st += sz;
        }
        return st;
    }

    private static boolean ok(double v) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += floor(r[i] * r[i] * PI / v);
        }
        return cnt > f;
    }
}
