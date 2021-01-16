package cp.problems.legacy.week7;

import java.util.Arrays;
import java.util.Scanner;

public class DivisibleGroupSum {

    private static final int[] numbers = new int[200];
    private static final int[][][] dp = new int[201][11][24];
    private static int n;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int set = 1;
        while (true) {
            n = s.nextInt();
            int q = s.nextInt();
            if (n == 0 && q == 0) break;

            for (int i = 0; i < n; i++) {
                numbers[i] = s.nextInt();
            }

            System.out.printf("SET %d:\n", set++);


            for (int i = 0; i < q; i++) {
                for (int[][] ints : dp) {
                    for (int[] anInt : ints) {
                        Arrays.fill(anInt, -1);
                    }
                }
                int d = s.nextInt();
                int m = s.nextInt();
                System.out.printf("QUERY %d: %d\n", i + 1, knapsack(0, m, d, 0));
            }
        }
    }

    private static int knapsack(int index, int wLeft, int d, int sum) {
        if (index == n && wLeft == 0) {
            return (sum % d == 0) ? 1 : 0;
        } else if (index == n) return 0;

        if (wLeft < 0) {
            return 0;
        }

        if (dp[index][wLeft][sum] != -1) return dp[index][wLeft][sum];

        int s1 = knapsack(index + 1, wLeft, d, sum);
        int s2 = knapsack(index + 1, wLeft - 1, d, (sum + ((numbers[index]) % d) + d) % d);
        return dp[index][wLeft][sum] = s1 + s2;

    }

}
