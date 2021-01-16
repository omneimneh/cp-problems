package cp.problems.legacy.week7;

import java.util.Scanner;

public class ECoin {

    private static final int[] conventional = new int[300];
    private static final int[] infoTech = new int[300];

    private static final int[][][] dp = new int[40][301][301];

    private static final int NO_RESULT = 10000;

    private static int m, S;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            m = s.nextInt();
            S = s.nextInt();
            for (int i = 0; i < m; i++) {
                conventional[i] = s.nextInt();
                infoTech[i] = s.nextInt();
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j <= S; j++) {
                    for (int k = 0; k <= S; k++) {
                        dp[i][j][k] = -1;
                    }
                }
            }

            int k = knapsack(0, 0, 0, 0);
            if (k < NO_RESULT) {
                System.out.println(k);
            } else {
                System.out.println("not possible");
            }
        }
    }

    private static int knapsack(int index, int cSum, int iSum, int count) {

        if (iSum * iSum + cSum * cSum > S * S) return NO_RESULT;

        if (cSum * cSum + iSum * iSum == S * S) {
            return count;
        }

        if (index == m) {
            return NO_RESULT;
        }

        if (dp[index][cSum][iSum] != -1) return dp[index][cSum][iSum];

        int k1 = knapsack(index + 1, cSum, iSum, count);
        int k2 = knapsack(index, cSum + conventional[index], iSum + infoTech[index], count + 1);

        return dp[index][cSum][iSum] = Math.min(k1, k2);

    }
}
