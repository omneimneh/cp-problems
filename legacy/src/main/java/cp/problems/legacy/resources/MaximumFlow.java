package cp.problems.legacy.resources;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumFlow {

    private static int[][] adj;
    private static int n, bottleNeck;
    private static int[] dist;
    private static int[] parent;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();

        adj = new int[n][n];
        parent = new int[n];
        dist = new int[n];

        int e = s.nextInt();


        for (int i = 0; i < e; i++) {
            addFlowEdge(s.nextInt(), s.nextInt(), s.nextInt());
        }


        System.out.println(maxFlow());
    }

    private static int maxFlow() {
        int maxFlow = 0;
        int source = 0, sink = n - 1;

        Queue<Integer> q = new LinkedList<>();

        Arrays.fill(dist, -1);

        dist[source] = 0;
        parent[sink] = -1;
        while (true) {
            q.add(0); // from src

            Arrays.fill(dist, -1);

            while (!q.isEmpty()) {
                int u = q.remove();
                if (u == sink) break;
                for (int v = 0; v < adj[u].length; v++) {
                    if (adj[u][v] > 0 && dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        q.add(v);
                        parent[v] = u; // memorize the way back home kiddo
                    }
                }
            }

            augmentPath(sink, Integer.MAX_VALUE, source);
            if (bottleNeck == 0) break; // no paths left to augment

            maxFlow += bottleNeck;

        }

        return maxFlow;
    }

    private static void augmentPath(int u, int minEdge, int source) {
        if (u == source) {
            bottleNeck = minEdge;
            return;
        }
        if (parent[u] != -1) {
            augmentPath(parent[u], Math.min(minEdge, adj[parent[u]][u]), source);
            adj[parent[u]][u] -= bottleNeck;
            adj[u][parent[u]] += bottleNeck;
        }
    }

    private static void addFlowEdge(int f, int t, int w) {
        adj[f][t] = w;
        adj[t][f] = 0;
    }
}
