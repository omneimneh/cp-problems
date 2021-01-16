package cp.problems.legacy.train.graph;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class ComeAndGo {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}

		while (true) {
			n = s.nextInt();
			m = s.nextInt();
			if (n == 0 && m == 0)
				break;

			reset();
			for (int i = 0; i < m; i++) {
				int v = s.nextInt() - 1;
				int w = s.nextInt() - 1;
				int p = s.nextInt();
				adj[v].add(w);
				if (p == 2) {
					adj[w].add(v);
				}
			}

			int no = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i] == UNVISITED_WHITE) {
					DFS_Tarjan(i);
					no++;
				}
			}

			if (count_scc != 1 || no > 1) {
				System.out.println(0);
			} else {
				System.out.println(1);
			}
		}
	}

	private static void reset() {
		for (int i = 0; i < n; i++) {
			adj[i].clear();
			dfs_low[i] = dfs_discovery[i] = 0;
			visited[i] = UNVISITED_WHITE;
			in_stack[i] = false;
		}
		discoveryTime = count_scc = 0;
		dfs_scc.clear();
	}

	private final static int UNVISITED_WHITE = 1, VISITED_GRAY = 2, DONE_BLACK = 3;

	@SuppressWarnings("unchecked")
	private static final LinkedList<Integer>[] adj = new LinkedList[2000];
	private static final int[] visited = new int[2000];
	private static final int[] dfs_low = new int[2000];
	private static final int[] dfs_discovery = new int[2000];
	private static int discoveryTime;
	private static int n, m;
	private static final boolean[] in_stack = new boolean[2000];
	private static final Stack<Integer> dfs_scc = new Stack<>();
	private static int count_scc;

	private static void DFS_Tarjan(int p) {

		dfs_discovery[p] = dfs_low[p] = discoveryTime++;
		visited[p] = VISITED_GRAY;

		in_stack[p] = true;
		dfs_scc.push(p);

		for (Integer child : adj[p]) {
			if (visited[child] == UNVISITED_WHITE) {
				DFS_Tarjan(child);
				dfs_low[p] = Math.min(dfs_low[p], dfs_low[child]);

			} else if (in_stack[child]) {
				dfs_low[p] = Math.min(dfs_low[p], dfs_low[child]);
			}

		}

		if (dfs_low[p] == dfs_discovery[p]) {
			// System.out.println("SCC:");
			while (!dfs_scc.empty() && dfs_scc.peek() != p) {
				int pop = dfs_scc.pop();
				in_stack[pop] = false;
				// System.out.print(pop + " ");
			}
			int pop = dfs_scc.pop();
			in_stack[pop] = false;
			// System.out.println(pop);
			count_scc++;
		}

		visited[p] = DONE_BLACK;
	}

}
