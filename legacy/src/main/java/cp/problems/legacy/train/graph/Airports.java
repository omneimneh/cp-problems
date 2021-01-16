package cp.problems.legacy.train.graph;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Airports {

	private static class Edge implements Comparable<Edge> {
		int x, y, w;

		Edge(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(w, o.w);
		}
	}

	private static int find(int u) {
		return u == par[u] ? u : (par[u] = find(par[u]));
	}

	private static void join(int u, int v) {
		par[find(u)] = find(v);
	}

	private static int M, N, A;
	private static final PriorityQueue<Edge> edges = new PriorityQueue<>();
	private static final int[] par = new int[(int) 1e4];
	private static final boolean[] vis = new boolean[(int) 1e4];
	@SuppressWarnings("unchecked")
	private static final LinkedList<Integer>[] adj = new LinkedList[(int) 1e4];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		int c = 0;

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}

		while (c++ < tc) {
			N = s.nextInt();
			M = s.nextInt();
			A = s.nextInt();

			for (int i = 0; i < N; i++) {
				par[i] = i;
				vis[i] = false;
				adj[i].clear();
			}

			edges.clear();
			for (int i = 0; i < M; i++) {
				int x = s.nextInt() - 1;
				int y = s.nextInt() - 1;
				int w = s.nextInt();
				if (w < A) {
					edges.add(new Edge(x, y, w));
				}
			}

			int cost = 0;
			int airports = 0;

			while (!edges.isEmpty()) {
				Edge e = edges.poll();
				if (find(e.x) != find(e.y)) {
					join(e.x, e.y);
					adj[e.x].add(e.y);
					adj[e.y].add(e.x);
					cost += e.w;
				}
			}

			for (int i = 0; i < N; i++) {
				if (!vis[i]) {
					vis[i] = true;
					dfs(i);
					airports++;
					cost += A;
				}
			}

			System.out.printf("Case #%d: %d %d\n", c, cost, airports);

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

	private static class Scanner {
		private final BufferedReader reader;
		private StringTokenizer st;

		public Scanner(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream));
		}

		public String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					String line = reader.readLine();
					if (line == null)
						return null;
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
