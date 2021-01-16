package cp.problems.legacy.week8;

import java.util.Scanner;

public class TakeTheLand {

	private static final int[][] tree = new int[101][101];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {
			int m = s.nextInt();
			int n = s.nextInt();
			if (m == 0 && n == 0)
				break;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					tree[i][j] = s.nextInt();
					if (i == 0 & j != 0) {
						tree[i][j] += tree[i][j - 1];
					} else if (i != 0 && j == 0) {
						tree[i][j] += tree[i - 1][j];
					} else if (i != 0) {
						tree[i][j] += tree[i - 1][j] + tree[i][j - 1] - tree[i - 1][j - 1];
					}
				}
			}

			int maxArea = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if ((m - i) * (n - j) <= maxArea)
						break;
					for (int i2 = i; i2 < m; i2++) {
						for (int j2 = j; j2 < n; j2++) {
							
							
							int newArea = (i2 - i + 1) * (j2 - j + 1);
							if (newArea <= maxArea)
								continue;

							if(newArea == 9) 
								System.out.print("");

							int trees = tree[i2][j2];
							if (i == 0 & j != 0) {
								trees -= tree[i2][j - 1];
							} else if (i != 0 && j == 0) {
								trees -= tree[i - 1][j2];
							} else if (i != 0) {
								trees += tree[i - 1][j - 1] - tree[i - 1][j2] - tree[i2][j - 1];
							}
							if (trees == 0) {
								maxArea = newArea;
							} else {
								break;
							}
						}
					}
				}
			}

			System.out.println(maxArea);
		}
	}

}
