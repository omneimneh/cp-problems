package cp.problems.legacy.others;

import java.util.Scanner;

public class HistoryGrading {

    private static final int[] rank = new int[100];
    private static int n;
    private static final int[] currentOrdering = new int[100];
    private static final int[] dp = new int[100];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        String line = null;
        while (s.hasNext()) {

            if (line != null) {
                n = Integer.parseInt(line);
            }

            //System.out.println("n = " + n);

            for (int i = 0; i < n; i++) {
                rank[i] = s.nextInt() - 1;
            }

            s.nextLine();

            while (s.hasNext() && (line = s.nextLine()).contains(" ")) {
                String[] ints = line.split(" ");
                for (int i = 0; i < n; i++) {
                    currentOrdering[Integer.parseInt(ints[i]) - 1] = i;
                }

                System.out.println(LIS());
            }
        }
    }

    private static int LIS() {
        dp[0] = 1;
        int res = 0;
        for (int i = 1; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (rank[currentOrdering[i]] > rank[currentOrdering[j]]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            res = Math.max(res, max);
        }
        return res;
    }
}
