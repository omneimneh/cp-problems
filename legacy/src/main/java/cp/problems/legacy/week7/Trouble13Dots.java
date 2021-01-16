package cp.problems.legacy.week7;

import java.util.Scanner;

public class Trouble13Dots {

    private static int n, coins;
    private static final int[] prices = new int[100];
    private static final int[] favorIndex = new int[100];
    private static final int[][] dp = new int[101][10201];


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            coins = s.nextInt();
            n = s.nextInt();

            for (int i = 0; i < n; i++) {
                prices[i] = s.nextInt();
                favorIndex[i] = s.nextInt();
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= coins + 200; j++) {
                    dp[i][j] = -1;
                }
            }

            System.out.println(knapsack(0, coins));
        }
    }

    private static int knapsack(int index, int money_left) {
        int purchasedAmount = coins - money_left;
        if (index == n && (money_left >= 0 || (purchasedAmount > 2000 && money_left >= -200))) return 0;
        if (index == n) return Integer.MIN_VALUE;
        if (money_left < -200) return Integer.MIN_VALUE;

        if (dp[index][money_left + 200] != -1) return dp[index][money_left + 200];

        int k1 = favorIndex[index] + knapsack(index + 1, money_left - prices[index]);
        int k2 = knapsack(index + 1, money_left);

        return dp[index][money_left + 200] = Math.max(k1, k2);

    }
}
