package cp.problems.legacy.others;

import java.util.*;

public class FormingTeams {

    private static final int[][] adj = new int[101][101];
    private static final int[] vis = new int[101];
    private static final boolean[] color = new boolean[101];
    private static final int WHITE = 0, GRAY = 1, BLACK = 2;
    private static int n;
    private static int c = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        int m = s.nextInt();

        for (int i = 0; i < m; i++) {
            int f = s.nextInt() - 1;
            int t = s.nextInt() - 1;
            addEdge(f, t);
        }

        bip();

        System.out.println((n - c) % 2 == 0 ? c : c + 1);
    }

    private static void bip() {
        for (int i = 0; i < n; i++) {
            if (vis[i] == WHITE) {
                dfs(i);
            }
        }
    }

    private static void dfs(int u) {
        vis[u] = GRAY;
        for (int i = 0; i < n; i++) {
            if (adj[u][i] == 1) {
                if (vis[i] == WHITE) {
                    color[i] = !color[u];
                    dfs(i);
                } else if (vis[i] == GRAY && color[i] == color[u]) {
                    c++;
                }
            }
        }
        vis[u] = BLACK;
    }


    private static void addEdge(int i, int j) {
        adj[i][j] = 1;
        adj[j][i] = 1;
    }
}
