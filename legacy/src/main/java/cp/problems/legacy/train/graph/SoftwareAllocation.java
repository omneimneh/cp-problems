package cp.problems.legacy.train.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SoftwareAllocation {

	private static final int[][] adj = new int[38][38];
	private static final int[] parent = new int[38];
	private static int bottleNeck;
	private static final boolean[] vis = new boolean[38];

	private static final int SOURCE = 0, SINK = 37;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {

			// reset matrix
			for (int[] ints : adj) {
				Arrays.fill(ints, 0);
			}

			int appsCount = 0;
			String nextLine;

			while (s.hasNext() && (nextLine = s.nextLine()).length() > 0) {
				int app = nextLine.charAt(0) - 'A' + 1;
				int edge = nextLine.charAt(1) - '0';
				appsCount += edge;

				adj[SOURCE][app] = edge;

				for (int i = 27; i < 37; i++) {
					adj[i][SINK] = 1;
				}

				for (int i = 3; i < nextLine.length() - 1; i++) {
					int pc = nextLine.charAt(i) - '0' + 27;
					adj[app][pc] = edge;
				}
			}

			int maxFlow = 0;
			while (true) {
				Queue<Integer> q = new LinkedList<>();
				q.add(0);
				Arrays.fill(vis, false);
				Arrays.fill(parent, -1);
				bottleNeck = 0;
				while (!q.isEmpty()) {
					int current = q.poll();
					if (current == SINK) {
						break;
					}
					for (int i = 0; i < adj[current].length; i++) {
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
			if (maxFlow < appsCount) {
				System.out.println("!");
			} else {
				for (int i = 27; i < 37; i++) {
					boolean has = false;
					for (int j = 1; j < 27; j++) {
						if (adj[i][j] > 0) {
							System.out.print((char) (j + 'A' - 1));
							has = true;
							break;
						}
					}
					if (!has) {
						System.out.print('_');
					}
				}
				System.out.println();
			}
		}
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

}
