package cp.problems.legacy.train.graph;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Dominos {

	private static int n, m;
	@SuppressWarnings("unchecked")
	private static final LinkedList<Integer>[] adj = new LinkedList[(int) 1e5 + 1];
	private static final boolean[] vis = new boolean[(int) 1e5 + 1];
	private static final Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}

		while (tc-- > 0) {
			n = s.nextInt();
			m = s.nextInt();

			for (int i = 0; i < n; i++) {
				adj[i].clear();
				vis[i] = false;
			}

			for (int i = 0; i < m; i++) {
				adj[s.nextInt() - 1].add(s.nextInt() - 1);
			}

			for (int i = 0; i < n; i++) {
				if (!vis[i]) {
					vis[i] = true;
					kosaraju(i);
				}
			}

			for (int i = 0; i < n; i++) {
				vis[i] = false;
			}

			int count = 0;
			while (!stack.isEmpty()) {
				if (!vis[stack.peek()]) {
					vis[stack.peek()] = true;
					dfs(stack.pop());
					count++;
				} else {
					stack.pop();
				}
			}

			System.out.println(count);

		}
	}

	private static void dfs(int u) {
		for (int v : adj[u]) {
			if (!vis[v]) {
				vis[v] = true;
				dfs(v);
			}
		}
	}

	private static void kosaraju(int u) {
		for (int v : adj[u]) {
			if (!vis[v]) {
				vis[v] = true;
				kosaraju(v);
			}
		}
		stack.push(u);
	}

}
