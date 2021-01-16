package cp.problems.legacy.contest.coach2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G {

	private static int m, n;
	@SuppressWarnings("unchecked")
	private static final LinkedList<Point>[] adj = new LinkedList[(int) 1e5];
	private static final double[] dist = new double[(int) 1e5];

	private static class Point implements Comparable<Point> {
		int index;
		double dist;

		Point(int index, double dist) {
			this.index = index;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			return Double.compare(o.dist, dist);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("path.in")));
		int tc = s.nextInt();
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}
		while (tc-- > 0) {
			n = s.nextInt();
			m = s.nextInt();
			for (int i = 0; i < n; i++) {
				adj[i].clear();
				dist[i] = 0;
			}
			for (int i = 0; i < m; i++) {
				int x = s.nextInt() - 1;
				int y = s.nextInt() - 1;
				double e = s.nextDouble();
				adj[x].add(new Point(y, e));
				adj[y].add(new Point(x, e));
			}
			PriorityQueue<Point> q = new PriorityQueue<>();
			q.add(new Point(0, 1));
			double res = 0.00;
			while (!q.isEmpty()) {
				Point pop = q.poll();
				if (pop.index == n - 1) {
					res = pop.dist;
					break;
				}
				for (Point p : adj[pop.index]) {
					if (dist[p.index] < pop.dist * p.dist) {
						dist[p.index] = pop.dist * p.dist;
						q.add(new Point(p.index, dist[p.index]));
					}
				}
			}
			System.out.printf("%.2f\n", round(res));
		}

	}

	public static double round(double d) {
		double k = (int) (d * 100 + .5);
		return k / 100.0;
	}

	private static class Scanner {
		public BufferedReader reader;
		public StringTokenizer st;

		public Scanner(FileReader stream) {
			reader = new BufferedReader(stream);
			st = null;
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

		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}
}
