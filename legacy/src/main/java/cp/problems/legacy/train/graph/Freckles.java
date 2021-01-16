package cp.problems.legacy.train.graph;

import java.util.Scanner;

public class Freckles {

	private static final double[] xs = new double[100];
	private static final double[] ys = new double[100];
	private static final int[] mstSet = new int[100];
	private static final double[] dist = new double[100];
	private static int n, tid;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while (tid++ < tc) {
			n = s.nextInt();
			for (int i = 0; i < n; i++) {
				xs[i] = s.nextDouble();
				ys[i] = s.nextDouble();
			}
			System.out.printf("%.2f\n", mst());
			if (tid != tc)
				System.out.println();

		}
		s.close();
	}

	private static double mst() {
		for (int i = 1; i < n; i++) {
			dist[i] = Double.MAX_VALUE;
		}
		dist[0] = 0.0f;

		double res = 0;

		int current = 0;
		mstSet[current] = tid;
		double minDist;

		int count = 0;
		while (++count < n) {

			for (int i = 0; i < n; i++) {
				if (mstSet[i] != tid) {
					dist[i] = Math.min(dist[i], dist(current, i));
				}
			}

			minDist = Double.MAX_VALUE;

			for (int i = 0; i < n; i++) {
				if (mstSet[i] != tid && minDist > dist[i]) {
					minDist = dist[i];
					current = i;
				}
			}

			mstSet[current] = tid;
			res += minDist;

		}
		return res;
	}

	private static double dist(int i, int j) {
		return Math.sqrt((xs[i] - xs[j]) * (xs[i] - xs[j]) + (ys[i] - ys[j])
				* (ys[i] - ys[j]));
	}
}
