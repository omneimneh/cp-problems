package cp.problems.legacy.train.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DrivingRange {

	private static class Point implements Comparable<Point> {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(y, o.y);
		}
	}

	private static final int[] par = new int[(int) 1e6];
	@SuppressWarnings("unchecked")
	private static final LinkedList<Point>[] adj = new LinkedList[(int) 1e6];
	private static final boolean[] mstSet = new boolean[(int) 1e6];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}

		while (true) {
			int n = s.nextInt();
			int m = s.nextInt();

			if (n == 0 && m == 0)
				break;

			for (int i = 0; i < n; i++) {
				par[i] = i;
				adj[i].clear();
				mstSet[i] = false;
			}

			for (int i = 0; i < m; i++) {
				int x = s.nextInt();
				int y = s.nextInt();
				int w = s.nextInt();
				//noinspection SuspiciousNameCombination
				adj[x].add(new Point(y, w));
				adj[y].add(new Point(x, w));
				join(x, y);
			}

			int count = 0;
			for (int i = 0; i < n; i++) {
				if (par[i] == i)
					count++;
			}

			if (count > 1) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(minCost());
			}
		}
	}

	private static int minCost() {
		int cost = 0;

		PriorityQueue<Point> q = new PriorityQueue<>();
		q.add(new Point(0, 0));

		while (!q.isEmpty()) {

			Point p = q.poll();
			while (!q.isEmpty() && mstSet[p.x]) {
				p = q.poll();
			}
			if (mstSet[p.x])
				break;
			mstSet[p.x] = true;

			for (Point pt : adj[p.x]) {
				q.add(new Point(pt.x, pt.y));
			}
			cost = Math.max(cost, p.y);
		}
		return cost;
	}

	private static void join(int x, int y) {
		//noinspection SuspiciousNameCombination
		par[find(x)] = find(y);
	}

	private static int find(int x) {
		return x == par[x] ? x : (par[x] = find(par[x]));
	}

	@SuppressWarnings("DuplicatedCode")
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
