package cp.problems.legacy.team;

import java.util.Scanner;

public class KnightInWarGrid {

	private static final boolean[][] vis = new boolean[105][105];
	private static final boolean[][] water = new boolean[105][105];
	private static int r, c, m, n, odd, even;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		for (int kase = 1; kase <= tc; kase++) {
			r = s.nextInt();
			c = s.nextInt();
			m = s.nextInt();
			n = s.nextInt();

			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					water[i][j] = vis[i][j] = false;
				}
			}

			int wat = s.nextInt();
			while (wat-- > 0) {
				int x = s.nextInt();
				int y = s.nextInt();
				water[x][y] = true;
			}

			odd = even = 0;

			if (water[0][0]) {
				System.out.printf("Case %d: %d %d\n", kase, 0, 0);
			} else {
				vis[0][0] = true;
				bfs(0, 0);
				System.out.printf("Case %d: %d %d\n", kase, even, odd);
			}
		}
	}

	private static void bfs(int i, int j) {
		int count = 0;
		int[] newX;
		int[] newY;

		if (m == n) {
			newX = new int[] { i + m, i + m, i - m, i - m };
			newY = new int[] { j + m, j - m, j + m, j - m };
		} else if (m == 0) {
			newX = new int[] { i + n, i - n, i, i };
			newY = new int[] { j, j, j + n, j - n };
		} else if (n == 0) {
			newX = new int[] { i + m, i - m, i, i };
			newY = new int[] { j, j, j + m, j - m };
		} else {
			newX = new int[] { i + m, i + m, i - m, i - m, i + n, i + n, i - n, i - n };
			newY = new int[] { j + n, j - n, j + n, j - n, j + m, j - m, j + m, j - m };
		}

		for (int k = 0; k < newX.length; k++) {

			if (valid(newX[k], newY[k])) {
				count++;
				if (!vis[newX[k]][newY[k]]) {
					vis[newX[k]][newY[k]] = true;
					bfs(newX[k], newY[k]);
				}
			}
		}

		if (count % 2 == 0) {
			even++;
		} else {
			odd++;
		}

	}

	private static boolean valid(int x, int y) {
		return !(x < 0 || y < 0 || x >= r || y >= c || water[x][y]);
	}
}
