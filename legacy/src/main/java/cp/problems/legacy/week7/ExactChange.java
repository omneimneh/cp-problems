package cp.problems.legacy.week7;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ExactChange {

    private static int target;

    private static class Scanner {
        private final BufferedReader reader;
        private StringTokenizer st;

        Scanner(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = reader.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (Exception e) {
                    throw (new RuntimeException());
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }


    private static final int[] coins = new int[300];
    private static final int[][] dp = new int[101][10001];
    private static final int[][] dp2 = new int[101][10001];
    private static int m, S;
    private static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            S = s.nextInt();
            m = s.nextInt();
            for (int i = 0; i < m; i++) {
                coins[i] = s.nextInt();
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j <= S; j++) {
                    dp[i][j] = -1;
                    dp2[i][j] = -1;
                }
            }

            target = knapsack(0, 0);
            out.printf("%d %d\n", target, build(0, 0));

        }
        out.flush();
    }

    private static int build(int index, int sum) {

        if (sum == target) return 0;
        if (index == m) return Integer.MAX_VALUE / 2;

        if (dp2[index][sum] != -1) return dp2[index][sum];

        int count = Integer.MAX_VALUE;
        int k = knapsack(index, sum);

        if (knapsack(index + 1, sum) == k) {
            count = Math.min(count, build(index + 1, sum));
        }

        if (knapsack(index + 1, sum + coins[index]) == k) {
            count = Math.min(count, 1 + build(index + 1, sum + coins[index]));
        }

        return dp2[index][sum] = count;
    }

    private static int knapsack(int index, int sum) {

        if (sum >= S) return sum;
        if (index == m) return Integer.MAX_VALUE;

        if (dp[index][sum] != -1) return dp[index][sum];

        int k1 = knapsack(index + 1, sum);
        int k2 = knapsack(index + 1, sum + coins[index]);

        return dp[index][sum] = Math.min(k1, k2);

    }

}
