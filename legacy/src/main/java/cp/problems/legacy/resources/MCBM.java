package cp.problems.legacy.resources;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MCBM {
    private static int N;
    public static List<LinkedList<Integer>> adj;
    private static final int[] vis = new int[N];
    private static final int[] match = new int[N];

    private static int aug(int n) {
        if (vis[n] < n) return 0;
        vis[n] = n;
        for (int m : adj.get(n)) {
            if (match[m] == -1 || aug(match[m]) == 1) {
                match[m] = n;
                return 1;
            }
        }
        return 0;
    }

    private static int mcbm() {
        int mcbm = 0;
        Arrays.fill(vis, -1);
        Arrays.fill(match, -1);
        for (int i = 0; i < N; i++) {
            mcbm += aug(i);
        }
        return mcbm;
    }
}