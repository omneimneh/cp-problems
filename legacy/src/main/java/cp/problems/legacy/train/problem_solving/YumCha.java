package cp.problems.legacy.train.problem_solving;

import java.util.Scanner;

public class YumCha {

	private static int n, x, T, K;
	private static final int[] weights = new int[100];
	private static final double[] values = new double[100];

	private static final Double[][][] dp = new Double[100][500][200];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {
			n = s.nextInt() + 1;
			x = s.nextInt();
			T = s.nextInt();
			K = s.nextInt();

			if (n == 1 && x == 0 && T == 0 && K == 0) {
				break;
			}

			for (int i = 0; i < K; i++) {
				weights[i] = s.nextInt();
				int favorIndex = 0;
				for (int j = 0; j < n; j++) {
					favorIndex += s.nextInt();
				}
				values[i] = (double) favorIndex / n;
			}

			for (int i = 0; i < K; i++) {
				for (int j = 0; j < 500; j++) {
					for (int j2 = 0; j2 < 200; j2++) {
						dp[i][j][j2] = null;
					}
				}
			}

			System.out.printf("%.2f\n", best(0, 0, 0));

		}
	}

	private static double best(int index, int money, int plates) {

		if (Math.ceil((money + T * n) * 0.1) + money + T * n > x * n || plates > 2 * n) {
			return Integer.MIN_VALUE;
		}

		if (index == K) {
			return 0;
		}

		 if (dp[index][money][plates] != null)
		 return dp[index][money][plates];

		double x1 = best(index + 1, money, plates);
		double x2 = values[index] + best(index + 1, money + weights[index], plates + 1);
		double x3 = 2 * values[index] + best(index + 1, money + 2 * weights[index], plates + 2);
		return dp[index][money][plates] = Math.max(x1, Math.max(x2, x3));
	}
}
