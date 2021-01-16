package cp.problems.legacy.train.graph;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.Math.min;

public class TheShortestPath {


    private static class Edge {
        int x, w;

        Edge(int x, int w) {
            this.x = x;
            this.w = w;
        }
    }

    private static boolean negativeCycle;
    private static final int[] vis = new int[2000];
    @SuppressWarnings("unchecked")
    private static final LinkedList<Edge>[] adj = new LinkedList[2000];
    private static final long[] dist = new long[2000];
    private static int vid;
    private static final long INF = Long.MAX_VALUE >> 1;
    private static final HashMap<Integer, Boolean> negs = new HashMap<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }

        int tc = s.nextInt();
        while (tc-- > 0) {
            vid += 2;
            negs.clear();
            int n = s.nextInt();
            int m = s.nextInt();

            long min = INF;
            for (int i = 0; i < n; i++) {
                adj[i].clear();
                dist[i] = INF;
            }

            for (int i = 0; i < m; i++) {
                int u = s.nextInt() - 1;
                int v = s.nextInt() - 1;
                int w = s.nextInt();
                adj[u].add(new Edge(v, w));
                min = min(min, w);
                if (w < 0) {
                    negs.put(u, true);
                }
            }

            negativeCycle = false;
            for (int i = 0; i < n; i++) {
                dfs(i, 0);
            }

            if (min >= 0) {
                out.println(min);
            } else if (negativeCycle) {
                out.println("-inf");
            } else {
                long res = INF;
                for (int x : negs.keySet()) {
                    Arrays.fill(dist, 0, n, INF);
                    dist[x] = 0;
                    for (int i = 0; i < n - 1; i++) {
                        for (int u = 0; u < n; u++) {
                            for (Edge e : adj[u]) {
                                dist[e.x] = min(dist[e.x], dist[u] + e.w);
                                res = min(res, dist[e.x]);
                            }
                        }
                    }
                }
                out.println(res);
            }
        }
        out.flush();
    }

    private static void dfs(int i, long w) {
        vis[i] = vid; // gray
        for (Edge e : adj[i]) {
            if (vis[e.x] == vid) { // gray
                if (w + e.w < 0) {
                    negativeCycle = true;
                    break;
                }
            } else if (vis[e.x] < vid) { // white
                dfs(e.x, w + e.w);
            }
        }
        vis[i] = vid + 1; // black
    }

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

}
