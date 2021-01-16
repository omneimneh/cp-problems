package cp.problems.legacy.contest.coach2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H {

	@SuppressWarnings("unchecked")
	private static final LinkedList<Integer>[] adj = new LinkedList[(int) 1e5];
	private static final long[] incs = new long[(int) 1e5];
	private static final long[] vals = new long[(int) 1e5];
	private static final int[] parent = new int[(int) 1e5];
	private static final boolean[] vis = new boolean[(int) 1e5];
	private static int m, n;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("vertex.in")));
		PrintWriter out = new PrintWriter(System.out);
		int tc = s.nextInt();
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}
		while (tc-- > 0) {
			n = s.nextInt();
			m = s.nextInt();
			for (int i = 0; i < n; i++) {
				adj[i].clear();
				vals[i] = 0;
				incs[i] = 0;
				vis[i] = false;
				parent[i] = -1;
			}

			for (int i = 0; i < n - 1; i++) {
				int a = s.nextInt() - 1;
				int b = s.nextInt() - 1;
				adj[a].add(b);
				adj[b].add(a);
			}

			int randRoot = (int) (Math.random() * n);
			computeTree(randRoot);

			for (int i = 0; i < m; i++) {
				int q = s.nextInt();
				if (q == 1) {
					int u = s.nextInt() - 1;
					long sln = vals[u];
					if (parent[u] != -1)
						sln += incs[parent[u]];
					out.println(sln);
				} else {
					int u = s.nextInt() - 1;
					int x = s.nextInt();
					incs[u] += x;
					if (parent[u] != -1)
						vals[parent[u]] += x;
				}
			}
			out.flush();

		}
	}

	private static void computeTree(int i) {
		vis[i] = true;
		for (int j : adj[i]) {
			if (!vis[j]) {
				parent[j] = i;
				computeTree(j);
			}
		}
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

	}
}
