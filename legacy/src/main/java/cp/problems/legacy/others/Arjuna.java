package cp.problems.legacy.others;

import java.util.Scanner;

public class Arjuna {

    private static final double[][] adj = new double[50][50];
    private static int N, K;
    private static final int T = 1, S = 0;

    private static final Double[][] dp = new Double[51][51];
    private static final boolean[] vis = new boolean[50];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            N = s.nextInt();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    adj[i][j] = s.nextDouble();
                }
            }
            K = s.nextInt();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= K; j++) {
                    dp[i][j] = null;
                }
                vis[i] = false;
            }

            vis[S] = true;
            System.out.println(dfs(S, K));
        }
    }

    private static double dfs(int src, int k) {

        double min = Double.MAX_VALUE / 2;

        //if (k < 0) return min;
        if (src == T) return 0;

        if (dp[src][k] != null) return dp[src][k];

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                vis[i] = true;
                double curMin = Math.min(adj[src][i] + dfs(i, k), k > 0 ? adj[src][i] * 0.5 + dfs(i, k - 1) : Double.MAX_VALUE);
                min = Math.min(curMin, min);
                vis[i] = false;
            }
        }

        return dp[src][k] = min;
    }
}