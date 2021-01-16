package cp.problems.legacy.train.graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class LiftlessEME {
    private static final int RANGE = 150 * 15 + 1;
    @SuppressWarnings("unchecked")
    private static final LinkedList<Point>[] adj = new LinkedList[RANGE];
    private static int n, m;
    private static final ArrayList<Integer> topo = new ArrayList<>();
    private static final int[] dist = new int[RANGE];
    private static final int[] vis = new int[RANGE];
    private static int vid;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
        while (s.hasNext()) {
            vid++;
            String testName = s.next();
            n = s.nextInt();
            m = s.nextInt();

            for (int i = 0; i <= n * m; i++) {
                adj[i].clear();
                dist[i] = Integer.MAX_VALUE >> 1;
            }

            for (int i = 0; i < m; i++) {
                adj[0].add(new Point(i + 1, 0));
            }

            for (int k = 0; k < n - 1; k++) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        adj[k * m + i + 1].add(new Point((k + 1) * m + j + 1, s.nextInt()));
                    }
                }
            }

            topoSort();

            dist[0] = 0;
            for (int node : topo) {
                for (Point edge : adj[node]) {
                    dist[edge.x] = Math.min(dist[edge.x], dist[node] + edge.y);
                }
            }

            int min = Integer.MAX_VALUE;
            for (int i = (n - 1) * m + 1; i <= n * m; i++) {
                min = Math.min(min, dist[i]);
            }

            System.out.println(testName);
            System.out.println(min + ((n - 1) << 1));

        }
    }

    private static void topoSort() {
        topo.clear();

        vis[0] = vid;
        topoSort(0);

        for (int i = 0; i < topo.size() >> 1; i++) {
            int tmp = topo.get(i);
            topo.set(i, topo.get(topo.size() - 1 - i));
            topo.set(topo.size() - 1 - i, tmp);
        }
    }

    private static void topoSort(int i) {
        for (Point child : adj[i]) {
            if (vis[child.x] < vid) {
                vis[child.x] = vid;
                topoSort(child.x);
            }
        }
        topo.add(i);
    }
}
