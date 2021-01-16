package cp.problems.legacy.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class InternetBandwidth {

    private static final int[] parent = new int[100];
    private static final int[][] adj = new int[100][100];
    private static int n;
    private static int src;
    private static int t;
    private static int bottleNeck;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = 1;
        while ((n = s.nextInt()) != 0) {

            src = s.nextInt() - 1;
            t = s.nextInt() - 1;
            int c = s.nextInt();

            reset();

            for (int i = 0; i < c; i++) {
                addBiEdge(s.nextInt() - 1, s.nextInt() - 1, s.nextInt());
            }

            System.out.printf("Network %d\n", tc++);
            System.out.printf("The bandwidth is %d.\n", maxFlow());
            System.out.println();
        }
    }

    private static int maxFlow() {
        int maxFlow = 0;


        while (true) {
            boolean[] vis = new boolean[n];
            Queue<Integer> q = new LinkedList<>();


            parent[t] = -1;

            q.add(src);

            while (!q.isEmpty()) {
                int u = q.remove();
                if (u == t) break;
                for (int v = 0; v < n; v++) {
                    if (adj[u][v] > 0 && !vis[v]) {
                        parent[v] = u;
                        vis[v] = true;
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

    private static void augmentPath(int u, int min) {
        if (u == src) {
            bottleNeck = min;
            return;
        }

        if (parent[u] != -1) {
            augmentPath(parent[u], Math.min(min, adj[parent[u]][u]));
            adj[parent[u]][u] -= bottleNeck;
            adj[u][parent[u]] += bottleNeck;
        }

    }

    private static void reset() {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                adj[i][j] = adj[j][i] = 0;
            }
        }
    }

    private static void addBiEdge(int i, int j, int w) {
        addEdge(i, j, w);
        addEdge(j, i, w);
    }

    private static void addEdge(int i, int j, int w) {
        adj[i][j] += w;
    }
}
