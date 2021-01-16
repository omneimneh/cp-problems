package cp.problems.legacy.train.graph;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TheNecklace {

	private static class Edge {
		int from, to;

		Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}

	private static final int[][] adj = new int[50][50];
	private static final int[] degree = new int[50];
	private static final Edge[] result = new Edge[1001];
	private static int resCount;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = s.nextInt();

		int c = 0;
		while (c++ < tc) {
			int con = s.nextInt();

			Arrays.fill(degree, 0);

			for (int i = 0; i < 50; i++) {
				for (int j = 0; j < 50; j++) {
					adj[i][j] = 0;
				}
			}

			int color = 0;
			for (int v = 0; v < con; v++) {
				int a = s.nextInt() - 1;
				int b = s.nextInt() - 1;
				color = Math.max(color, Math.max(a, b));
				adj[a][b]++;
				adj[b][a]++;
				degree[a]++;
				degree[b]++;
			}

			out.printf("Case #%d\n", c);
			out.flush();
			if (!hasEuler()) {
				out.println("some beads may be lost");
			} else {
				resCount = 0;
				findEuler(color);
				if (resCount != con || result[0].to != result[resCount - 1].from) {
					System.out.println("some beads may be lost");
				} else {
					for (int i = resCount - 1; i >= 0; i--) {
						out.printf("%d %d\n", result[i].from + 1, result[i].to + 1);
					}
				}
			}

			if (tc != c)
				out.println();

		}
		out.flush();
	}

	private static void findEuler(int current) {
		for (int i = 0; i < 50; i++) {
			if (adj[current][i] > 0) {
				adj[current][i]--;
				adj[i][current]--;
				degree[i]--;
				degree[current]--;
				findEuler(i);
				result[resCount++] = new Edge(current, i);
			}
		}
	}

	private static boolean hasEuler() {
		for (int j : degree) {
			if (j % 2 != 0)
				return false;
		}
		return true;
	}

}
