package cp.problems.legacy.train.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class DovesAndBombs {

	private static class Point implements Comparable<Point> {
		int connections;
		int index;

		@Override
		public int compareTo(Point o) {
			if (o.connections != connections)
				return Integer.compare(o.connections, connections);

			return Integer.compare(index, o.index);
		}
	}

	private static final int VISITED_GRAY = 1;
	private static final int UNVISITED_WHITE = 0;
	private static final int DONE_BLACK = 2;

	public static void main(String[] args) {
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
			points[i] = new Point();
		}

		Scanner s = new Scanner(System.in);
		while (true) {
			n = s.nextInt();
			m = s.nextInt();
			if (n == 0 && m == 0)
				break;
			reset();
			while (true) {
				int u = s.nextInt();
				int v = s.nextInt();
				if (u == -1 && v == -1)
					break;
				adj[u].add(v);
				adj[v].add(u);
			}
			DFS_ArticulationPoints(0);
			Arrays.sort(points, 0 , n);
			for (int i = 0; i < m; i++) {
				System.out.printf("%d %d\n", points[i].index, points[i].connections + 1);
			}
			System.out.println();
		}

	}

	private static void reset() {
		for (int i = 0; i < n; i++) {
			adj[i].clear();
			points[i].index = i;
			points[i].connections = 0;
			dfs_low[i] = 0;
			dfs_discovery[i] = 0;
			visited[i] = UNVISITED_WHITE;
			parent[i] = -1;
		}
		discoveryTime = 0;

	}

	private static int n, m;
	private static final int[] visited = new int[10000];
	private static final int[] dfs_low = new int[10000];
	private static final int[] parent = new int[10000];
	private static int discoveryTime;
	private static final int[] dfs_discovery = new int[10000];
	@SuppressWarnings("unchecked")
	private static final LinkedList<Integer>[] adj = new LinkedList[10000];
	private static final Point[] points = new Point[10000];

	private static void DFS_ArticulationPoints(int p) {
		visited[p] = VISITED_GRAY;
		dfs_discovery[p] = dfs_low[p] = discoveryTime++;

		int children = 0;

		for (Integer child : adj[p]) {
			if (visited[child] == UNVISITED_WHITE) {

				children++;
				parent[child] = p;

				DFS_ArticulationPoints(child);

				dfs_low[p] = Math.min(dfs_low[p], dfs_low[child]);

				if (parent[p] == -1 && children > 1) {
					points[p].connections++;
				}

				if (parent[p] != -1 && dfs_low[child] >= dfs_discovery[p]) {
					points[p].connections++;
				}

			} else if (child != parent[p]) {
				dfs_low[p] = Math.min(dfs_low[p], dfs_discovery[child]);
			}
		}
		visited[p] = DONE_BLACK;
	}

}
