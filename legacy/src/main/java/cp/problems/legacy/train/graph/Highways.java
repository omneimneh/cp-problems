package cp.problems.legacy.train.graph;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Highways {

	private static class Edge implements Comparable<Edge> {
		int x, y;
		double w;

		Edge(int x, int y, double w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(w, o.w);
		}
	}

	private static final int[] parent = new int[751];
	private static final Point[] cities = new Point[751];
	private static final ArrayList<Edge> edges = new ArrayList<>(751 * 751);
	private static int N, M;
	private static final PrintWriter out = new PrintWriter(System.out);

	private static int find(int u) {
		return u == parent[u] ? u : (parent[u] = find(parent[u]));
	}

	private static void join(int u, int v) {
		parent[find(u)] = parent[find(v)];
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while (tc-- > 0) {
			N = s.nextInt();

			for (int i = 1; i <= N; i++) {
				cities[i] = new Point(s.nextInt(), s.nextInt());
				parent[i] = i;
			}

			edges.clear();
			for (int i = 0; i < N * N; i++) {
				edges.add(new Edge(i % N + 1, i / N + 1, dist(i % N + 1, i / N + 1)));
			}

			Collections.sort(edges);

			M = s.nextInt();
			for (int i = 0; i < M; i++) {
				join(s.nextInt(), s.nextInt());
			}

			boolean exits = false;

			for (int i = 0; i < N * N; i++) {
				if (find(edges.get(i).x) != find(edges.get(i).y)) {
					join(edges.get(i).x, edges.get(i).y);
					exits = true;
					out.printf("%d %d\n", edges.get(i).y, edges.get(i).x);
				}
			}

			if (!exits)
				out.println("No new highways need");
			if (tc != 0)
				out.println();
		}
		out.flush();
	}

	private static double dist(int i, int j) {
		return (cities[i].x - cities[j].x) * (cities[i].x - cities[j].x)
				+ (cities[i].y - cities[j].y) * (cities[i].y - cities[j].y);
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
