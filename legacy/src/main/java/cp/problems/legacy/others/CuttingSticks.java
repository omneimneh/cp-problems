package cp.problems.legacy.others;

import java.util.Scanner;

public class CuttingSticks {

    public static int l;
    public static int cutCount;
    public static int[] cuts = new int[52];
    private static final Integer[][] mem = new Integer[52][52];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while ((l = s.nextInt()) != 0) {

            cutCount = s.nextInt();

            for (int i = 0; i < cutCount + 2; i++) {
                for (int j = 0; j < cutCount + 2; j++) {
                    mem[i][j] = null;
                }
            }

            cuts[0] = 0;
            for (int i = 1; i < cutCount + 1; i++) {
                cuts[i] = s.nextInt();
            }
            cuts[cutCount + 1] = l;
            System.out.printf("The minimum cutting is %d.\n", dp(0, cutCount + 1));

        }
    }

    private static int dp(int f, int t) {
        if (t - f == 1) {
            return 0;
        }

        if (mem[f][t] != null) {
            return mem[f][t];
        }

        int q = Integer.MAX_VALUE;
        for (int i = f + 1; i < t; i++) {
            q = Math.min(q, dp(f, i) + dp(i, t) + (cuts[t] - cuts[f]));
        }
        return mem[f][t] = q;
    }
}
