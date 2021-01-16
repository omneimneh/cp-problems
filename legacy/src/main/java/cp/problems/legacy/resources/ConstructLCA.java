package cp.problems.legacy.resources;

import java.util.ArrayList;

public class ConstructLCA {
    private static int[][] adj;
    private static int[][] lca;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        adj = new int[n][n];
        lca = new int[n][n];

        for (int i = 0; i < n - 1; i++) {
            adj[s.nextInt()][s.nextInt()] = 1;
        }

        LowestCommonAncestorDFS(0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(lca[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void LowestCommonAncestorDFS(int u, int n) {
        ArrayList<Integer> myDescendants = new ArrayList<>();
        dfs(u, n, myDescendants); // basically say to all children in subtree i'm your closest ancestor
        for (Integer i : myDescendants) {
            for (Integer j : myDescendants) {
                lca[i][j] = u;
            }
        }
        for (int i = 0; i < n; i++) {
            if (adj[u][i] == 1) {
                LowestCommonAncestorDFS(i, n);
            }
        }
    }

    private static void dfs(int u, int n, ArrayList<Integer> desc) {
        desc.add(u);
        for (int i = 0; i < n; i++) {
            if (adj[u][i] == 1) {
                dfs(i, n, desc);
            }
        }
    }
}
