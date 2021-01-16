package cp.problems.legacy.train.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DownWentTheTitanic {

	private static final char[][] matrix = new char[30][30];
	private static final int[][] adj = new int[1802][1802];
	private static final boolean[] vis = new boolean[1802];
	private static final int[] parent = new int[1802];
	private static int Y;
	private static int SOURCE, SINK;
	private static int bottleNeck;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			int x = s.nextInt();
			Y = s.nextInt();
			int p = s.nextInt();

			for (int i = 0; i < x; i++) {
				char[] line = s.next().toCharArray();
				if (Y >= 0) System.arraycopy(line, 0, matrix[i], 0, Y);
			}

			SOURCE = 0;
			SINK = x * Y * 2 + 1;

			for (int i = 0; i <= SINK; i++) {
				for (int j = 0; j <= SINK; j++) {
					adj[i][j] = 0;
				}
			}

			for (int i = 0; i < x; i++) {
				for (int j = 0; j < Y; j++) {
					int enter = entering(i, j);
					int exit = enter + 1;
					if (matrix[i][j] == '@') {
						adj[enter][exit] = 10000000;
					} else if (matrix[i][j] == '#') {
						adj[enter][exit] = 10000000;
						adj[exit][SINK] = p;
					} else if (matrix[i][j] == '.') {
						adj[enter][exit] = 1;
					} else if (matrix[i][j] == '*') {
						adj[enter][exit] = 1;
						adj[SOURCE][enter] = 1;
					}
					int[] nx = { i, i, i + 1, i - 1 };
					int[] ny = { j + 1, j - 1, j, j };
					for (int k = 0; k < nx.length; k++) {
						if (nx[k] >= 0 && ny[k] >= 0 && nx[k] < x && ny[k] < Y) {
							if (matrix[nx[k]][ny[k]] == '@' || matrix[nx[k]][ny[k]] == '#'
									|| matrix[nx[k]][ny[k]] == '.') {
								adj[exit][entering(nx[k], ny[k])] = 1000000;
							}
						}
					}
				}
			}
			System.out.println(mf());
		}
	}

	private static int mf() {
		int maxFlow = 0;
		while (true) {
			Queue<Integer> q = new LinkedList<>();
			q.add(SOURCE);
			Arrays.fill(vis, false);
			Arrays.fill(parent, -1);
			bottleNeck = 0;
			while (!q.isEmpty()) {
				int current = q.poll();
				if (current == SINK) {
					break;
				}
				for (int i = 0; i <= SINK; i++) {
					if (adj[current][i] > 0 && !vis[i]) {
						vis[i] = true;
						parent[i] = current;
						q.add(i);
					}
				}

			}
			augmentPath(SINK, Integer.MAX_VALUE);

			if (bottleNeck == 0)
				break;
			maxFlow += bottleNeck;
		}
		return maxFlow;
	}

	@SuppressWarnings("DuplicatedCode")
	private static void augmentPath(int current, int minEdge) {
		if (current == SOURCE) {
			bottleNeck = minEdge;
		} else if (parent[current] != -1) {
			augmentPath(parent[current], Math.min(minEdge, adj[parent[current]][current]));
			adj[parent[current]][current] -= bottleNeck;
			adj[current][parent[current]] += bottleNeck;
		}
	}

	private static int entering(int i, int j) {
		return 1 + 2 * (i * Y + j);
	}

}
