package cp.problems.legacy.train.problem_solving;

import java.util.Scanner;

public class GridGame {
	private static final int[][] matrix = new int[8][8];
	private static final boolean[] cols = new boolean[8];
	private static int n;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int tc = s.nextInt();
		while (tc-- > 0) {
			n = s.nextInt();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = s.nextInt();
				}
			}

			for (int i = 0; i < n; i++) {
				cols[i] = false;
			}

			System.out.println(rec(0));
		}
	}

	private static int rec(int step) {
		if (step == n)
			return 0;

		int result = Integer.MAX_VALUE;
		for (int j = 0; j < n; j++) {
			if (!cols[j]) {
				cols[j] = true;
				result = Math.min(result, matrix[step][j] + rec(step + 1));
				cols[j] = false;
			}
		}

		return result;
	}
}
