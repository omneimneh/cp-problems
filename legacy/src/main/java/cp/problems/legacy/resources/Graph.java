package cp.problems.legacy.resources;

import java.util.LinkedList;
import java.util.Stack;

@SuppressWarnings("unused")
public class Graph {

    private final static int UNVISITED_WHITE = 1, VISITED_GRAY = 2, DONE_BLACK = 3;

    private final LinkedList<Integer>[] adj;
    private final int[] visited;
    private final int[] dfs_low;
    private final int[] dfs_discovery;
    private final int[] parent;
    private int discoveryTime;
    private final boolean[] ap;

    private final boolean[] in_stack;
    private final Stack<Integer> dfs_scc;

    private void DFS_Tarjan(int p) {

        dfs_discovery[p] = dfs_low[p] = discoveryTime++;
        visited[p] = VISITED_GRAY;

        in_stack[p] = true;
        dfs_scc.push(p);

        for (Integer child : adj[p]) {
            if (visited[child] == UNVISITED_WHITE) {
                DFS_Tarjan(child);
                dfs_low[p] = Math.min(dfs_low[p], dfs_low[child]);

            } else if (in_stack[child]) {
                dfs_low[p] = Math.min(dfs_low[p], dfs_discovery[child]);
            }

        }

        if (dfs_low[p] == dfs_discovery[p]) {
            System.out.println("SCC:");
            while (!dfs_scc.empty() && dfs_scc.peek() != p) {
                int pop = dfs_scc.pop();
                in_stack[pop] = false;
                System.out.print(pop + " ");
            }
            int pop = dfs_scc.pop();
            in_stack[pop] = false;
            System.out.println(pop);
        }

        visited[p] = DONE_BLACK;
    }


    private void DFS_EdgeProperty(int p) {
        visited[p] = VISITED_GRAY;
        for (Integer child : adj[p]) {
            if (visited[child] == UNVISITED_WHITE) {
                System.out.println("Tree Edge: " + p + " " + child);
                parent[child] = p;
                DFS_EdgeProperty(child);
            } else if (visited[child] == VISITED_GRAY && parent[p] != child) {
                System.out.println("Back Edge: " + p + " " + child);
            } else if (visited[child] == VISITED_GRAY) {
                System.out.println("Bidirectional Edge: " + p + " " + child);
            } else if (visited[child] == DONE_BLACK) {
                System.out.println("Forward/Cross Edge: " + p + " " + child);
            }
        }
        visited[p] = DONE_BLACK;
    }

    private void DFS_ArticulationPoints(int p) {
        visited[p] = VISITED_GRAY;
        dfs_discovery[p] = dfs_low[p] = discoveryTime++;

        int children = 0;

        for (Integer child : adj[p]) {
            if (visited[child] == UNVISITED_WHITE) {

                children++;
                parent[child] = p;

                DFS_ArticulationPoints(child);

                dfs_low[p] = Math.min(dfs_low[p], dfs_low[child]);

                if (this.parent[p] == -1 && children > 1) {
                    ap[p] = true;
                }

                if (this.parent[p] != -1 && dfs_low[child] >= dfs_discovery[p]) {
                    ap[p] = true;
                }

            } else if (child != this.parent[p]) {
                dfs_low[p] = Math.min(dfs_low[p], dfs_discovery[child]);
            }
        }
        visited[p] = DONE_BLACK;
    }

    private void AP() {

        discoveryTime = 0;

        for (int i = 0; i < adj.length; i++) {
            visited[i] = UNVISITED_WHITE;
            parent[i] = -1;
            ap[i] = false;
        }

        DFS_ArticulationPoints(0);

        for (int i = 0; i < adj.length; i++) {
            if (ap[i])
                System.out.println(i);
        }

    }

    private void EP() {

        discoveryTime = 0;

        for (int i = 0; i < adj.length; i++) {
            visited[i] = UNVISITED_WHITE;
            parent[i] = -1;
        }

        DFS_EdgeProperty(0);
    }

    private void Tarjan() {
        discoveryTime = 0;
        for (int i = 0; i < adj.length; i++) {
            visited[i] = UNVISITED_WHITE;
        }
        DFS_Tarjan(0);
    }

    @SuppressWarnings("unchecked")
    private Graph(int v) {
        adj = new LinkedList[v];
        ap = new boolean[v];
        parent = new int[v];
        dfs_low = new int[v];
        dfs_discovery = new int[v];
        visited = new int[v];
        in_stack = new boolean[v];
        dfs_scc = new Stack<>();
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    private void addBiEdge(int i, int j) {
        addEdge(i, j);
        addEdge(j, i);
    }

    private void addEdge(int i, int j) {
        adj[i].add(j);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int len = s.nextInt();
        Graph g = new Graph(len);
        int e = s.nextInt();
        for (int i = 0; i < e; i++) {
            g.addBiEdge(s.nextInt(), s.nextInt());
        }
        //g.EP();
        g.AP();
    }
}
