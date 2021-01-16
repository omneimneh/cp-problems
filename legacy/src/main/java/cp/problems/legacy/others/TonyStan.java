package cp.problems.legacy.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TonyStan {

    private static class Node {
        int node;
        int level;

        Node(int node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    private static final int[][] adj = new int[200][200];
    private static final int[][] police = new int[201][201];
    private static final boolean[][] vis = new boolean[200][200];
    private static int N, M, K, S, D;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            N = s.nextInt();
            M = s.nextInt();
            K = s.nextInt();

            reset();

            for (int i = 0; i < M; i++) {
                int n1 = s.nextInt() - 1;
                int n2 = s.nextInt() - 1;
                adj[n1][n2] = adj[n2][n1] = 1;
            }

            for (int i = 0; i < K; i++) {
                int node = s.nextInt() - 1;
                police[i][0] = 1; // counter
                police[i][1] = node;
                for (int j = 0; j < N; j++) {
                    if (adj[node][j] == 1) {
                        police[i][++police[i][0]] = j;
                    }
                }
            }

            S = s.nextInt() - 1;
            D = s.nextInt() - 1;

            Queue<Node> q = new LinkedList<>();
            q.add(new Node(S, 0));
            vis[0][S] = true;

            int found = -1;
            while (!q.isEmpty()) {
                Node c = q.remove();
                if (c.node == D) {
                    found = c.level;
                    break;
                }

                for (int i = 0; i < N; i++) {
                    if (!vis[(c.level + 1) % K][i] && adj[c.node][i] == 1 && safe(c, i)) {
                        q.add(new Node(i, c.level + 1));
                        vis[(c.level + 1) % K][i] = true;
                    }
                }

            }

            if (found != -1) System.out.println(found);
            else System.out.println("WASTED");
        }
    }

    private static boolean safe(Node c, int i) {
        for (int j = 1; j <= police[(c.level + 1) % K][0]; j++) {
            if (police[(c.level + 1) % K][j] == i) return false;
        }
        return true;
    }

    private static void reset() {
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                adj[i][j] = adj[j][i] = 0;
                vis[i][j] = vis[j][i] = false;
            }
        }
    }
}
