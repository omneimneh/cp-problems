package cp.problems.legacy.others;

import java.util.Scanner;

public class Fishmonger {

    private static final Integer[][] dp = new Integer[51][1001];
    private static final int[][] times = new int[51][51];
    private static final int[][] coins = new int[51][51];
    private static int n, t;
    private static int resCost;
    private static final int[][] resTime = new int[51][1001];
    private static final boolean[] visited = new boolean[51];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (true) {
            n = s.nextInt();
            t = s.nextInt();

            if (n == 0 && t == 0) break;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    times[i][j] = s.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    coins[i][j] = s.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= t; j++) {
                    dp[i][j] = null;
                }
            }

            resCost = dpFn(0, t);

            System.out.printf("%d %d\n", resCost, resTime[0][t]);
        }
    }

    private static int dpFn(int current, int remainTime) {
        if (current == n - 1) {
            resTime[current][remainTime] = 0;
            return 0;
        }
        if (dp[current][remainTime] != null) {
            return dp[current][remainTime];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (remainTime >= times[current][i] && !visited[i]) {
                visited[i] = true;
                int path = dpFn(i, remainTime - times[current][i]);
                if (path != Integer.MAX_VALUE) {
                    if (coins[current][i] + path < min) {
                        min = coins[current][i] + path;
                        resTime[current][remainTime] = times[current][i] + resTime[i][remainTime - times[current][i]];
                    }
                }
                visited[i] = false;
            }
        }
        return dp[current][remainTime] = min;
    }
}
