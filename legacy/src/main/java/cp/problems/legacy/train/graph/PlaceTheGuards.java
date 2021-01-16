package cp.problems.legacy.train.graph;

import java.util.Scanner;

public class PlaceTheGuards {

	private static final boolean[][] adj = new boolean[200][200];
	private static final int UNVISITED = 0, RED = 1, BLUE = 2;
	private static final int[] color = new int[200];
	private static int e, j, redCount, blueCount;
	private static boolean impossible;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while (tc-- > 0) {
			j = s.nextInt();
			e = s.nextInt();

			reset();

			for (int i = 0; i < e; i++) {
				int u = s.nextInt();
				int v = s.nextInt();
				adj[u][v] = adj[v][u] = true;
			}

			int actualCount = 0, prevBlue = 0, prevRed = 0;
			for (int i = 0; i < j; i++) {
				if (color[i] == UNVISITED) {
					color[i] = RED;
					dfs(i);
					if (blueCount == prevBlue)
						actualCount += -prevRed + redCount;
					else if (redCount == prevRed)
						actualCount += -prevBlue + blueCount;
					else {
						actualCount += Math.min(blueCount - prevBlue, redCount - prevRed);
					}
					prevBlue = blueCount;
					prevRed = redCount;
				}
			}

			if (impossible)
				System.out.println(-1);
			else
				System.out.println(actualCount);

		}

	}

	private static int not(int x) {
		return x == BLUE ? RED : BLUE;
	}

	private static void dfs(int current) {
		if (color[current] == RED)
			redCount++;
		else {
			blueCount++;
		}
		for (int i = 0; i < j; i++) {
			if (adj[current][i]) {

				if (color[i] == UNVISITED) {
					color[i] = not(color[current]);
					dfs(i);
				} else if (color[i] == color[current]) {
					impossible = true;
					return;
				}
			}
		}
	}

	private static void reset() {
		for (int i = 0; i < j; i++) {
			color[i] = UNVISITED;
			for (int k = 0; k < j; k++) {
				adj[i][k] = false;
			}
		}
		blueCount = redCount = 0;
		impossible = false;

	}

}
