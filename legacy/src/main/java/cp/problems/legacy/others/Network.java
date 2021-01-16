package cp.problems.legacy.others;

import java.util.LinkedList;
import java.util.Scanner;

public class Network {

    private static int c;
    private static int dt;
    @SuppressWarnings("unchecked")
    private static final LinkedList<Integer>[] adj = new LinkedList[101];
    private static final int[] disc = new int[101];
    private static final int[] low = new int[101];
    private static final int[] parent = new int[101];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }

        int n;
        while ((n = s.nextInt()) != 0) {

            c = 0;
            dt = 0;

            for (int i = 0; i < n; i++) {
                adj[i].clear();
                disc[i] = -1;
                parent[i] = -1;
            }

            String line;
            s.nextLine();
            while (!(line = s.nextLine()).equals("0")) {
                //System.out.println(line);
                String[] network = line.split(" ");
                int x = Integer.parseInt(network[0]) - 1;
                for (int i = 1; i < network.length; i++) {
                    addBiEdge(x, Integer.parseInt(network[i]) - 1);
                }
            }

            ap(0);
            System.out.println(c);
        }

    }

    private static void ap(int u) {
        disc[u] = low[u] = dt++;
        int children = 0;
        boolean a = false;
        for (Integer v : adj[u]) {
            if (disc[v] == -1) {
                parent[v] = u; // im your freaking parent
                children++;

                ap(v);

                low[u] = Math.min(low[u], low[v]);

                if (parent[u] != -1 && low[v] >= disc[u]) {
                    a = true;
                }

                if (parent[u] == -1 && children > 1) {
                    a = true;
                }

            } else if (parent[u] != v) {
                low[u] = Math.min(low[u], disc[v]);
            }

        }
        if (a) {
            c++;
        }
    }


    private static void addBiEdge(int i, int j) {
        addEdge(i, j);
        addEdge(j, i);
    }

    private static void addEdge(int i, int j) {
        adj[i].add(j);
    }
}
