package cp.problems.legacy.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PowerTransmission {

    private static final int[][] adj = new int[202][202];
    private static final int[] parent = new int[202];
    private static int n, bottleNeck, t;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (s.hasNext()) {

            n = s.nextInt();
            t = 2 * n + 1;

            reset();

            for (int i = 1; i <= n; i++) {
                addFlowEdge(i, i + n, s.nextInt());
            }

            int M = s.nextInt();

            for (int i = 0; i < M; i++) {
                addFlowEdge(s.nextInt() + n, s.nextInt(), s.nextInt());
            }

            int b = s.nextInt();
            int d = s.nextInt();

            for (int i = 0; i < b; i++) {
                addFlowEdge(0, s.nextInt(), Integer.MAX_VALUE);
            }

            for (int i = 0; i < d; i++) {
                addFlowEdge(s.nextInt() + n, t, Integer.MAX_VALUE);
            }

            System.out.println(maxFlow());
        }
    }

    private static void reset() {
        for (int i = 0; i < 2 * n + 2; i++) {
            for (int j = i; j < 2 * n + 2; j++) {
                adj[i][j] = adj[j][i] = 0;
            }
            parent[i] = -1;
        }
    }

    private static void addFlowEdge(int f, int t, int w) {
        adj[f][t] += w;
        adj[t][f] = 0;
    }

    private static int maxFlow() {
        int maxFlow = 0;

        while (true) {
            boolean[] vis = new boolean[2 * n + 2];

            Queue<Integer> q = new LinkedList<>();
            q.add(0);
            while (!q.isEmpty()) {
                int u = q.remove();
                if (u == t) break;
                for (int v = 0; v < 2 * n + 2; v++) {
                    if (adj[u][v] > 0 && !vis[v]) {
                        vis[v] = true;
                        parent[v] = u;
                        q.add(v);
                    }
                }
            }

            bottleNeck = 0;
            augmentPath(t, Integer.MAX_VALUE);

            if (bottleNeck == 0) break;

            maxFlow += bottleNeck;
        }

        return maxFlow;
    }

    private static void augmentPath(int u, int minEdge) {
        if (u == 0) {
            bottleNeck = minEdge;
            return;
        }
        if (parent[u] != -1) {
            augmentPath(parent[u], Math.min(minEdge, adj[parent[u]][u]));
            adj[parent[u]][u] -= bottleNeck;
            adj[u][parent[u]] += bottleNeck;
        }
    }

}
