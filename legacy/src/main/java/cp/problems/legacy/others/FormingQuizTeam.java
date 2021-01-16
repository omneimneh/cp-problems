package cp.problems.legacy.others;

import java.awt.Point;
import java.util.Scanner;

public class FormingQuizTeam {

    private static int n;
    private static final int[][] states = new int[20][1<<16]; // in binary
    private static final int[] sc = new int[1<<16];
    private static final double[] dp = new double[1<<16];
    private static final Point[] points = new Point[20];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = 1;
        while ((n = s.nextInt() * 2) != 0) {
            for (int i = 0; i < n; i++) {
                s.next();
                points[i] = new Point(s.nextInt(), s.nextInt());
            }
            System.out.printf("Case %d: %.2f\n", t++, min());
        }
    }

    private static void getStates(int current, int active, int state, int put) {

        if (current - active + 1 < 0) return;

        if (current == 0 && active == 0) {
            states[put][sc[put]++] = state;
            return;
        }

        if (current == 0) return;

        getStates(current - 1, active - 1, state + (1 << (current - 1)), put);
        getStates(current - 1, active, state, put);
    }

    private static boolean areComplements(int x, int y) {
        return (x | y) == (x + y);
    }

    private static double dist(Point x1, Point x2) {
        return Math.sqrt((x1.x - x2.x) * (x1.x - x2.x) + (x1.y - x2.y) * (x1.y - x2.y));
    }

    private static double min() {

        for (int i = 0; i < 1 << n; i++) {
            dp[i] = Double.MAX_VALUE;
            sc[i] = 0;
        }

        for (int i = 2; i <= n; i += 2) {
            getStates(n, i, 0, i);
        }

        for (int i = 0; i < sc[2]; i++) {
            int state = states[2][i];
            int cpy = state;
            int c = 0;
            while (cpy % 2 == 0) {
                cpy = cpy >> 1;
                c++;
            }

            Point first = points[c], second;

            c++;
            cpy = cpy >> 1;

            while (cpy % 2 == 0) {
                cpy = cpy >> 1;
                c++;
            }
            second = points[c];

            dp[state] = dist(first, second);
        }

        for (int i = 2; i <= n; i += 2) {
            for (int j = 0; j < sc[i]; j++) {
                int state = states[i][j];
                for (int k = 0; k < sc[2]; k++) {
                    int state2 = states[2][k];
                    if (areComplements(state, state2)) {
                        dp[state + state2] = Math.min(dp[state + state2], dp[state] + dp[state2]);
                    }
                }
            }
        }
        return dp[states[n][0]]; // dp[1111111...] all states active
    }
}
/*
5
sohel 10 10
mahmud 20 10
sanny 5 5
prince 1 1
per 120 3
mf 6 6
kugel 50 60
joey 3 24
limon 6 9
manzoor 0 0
1
derek 9 9
jimmy 10 10
0
 */