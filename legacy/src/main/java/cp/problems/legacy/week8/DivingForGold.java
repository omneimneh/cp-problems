package cp.problems.legacy.week8;

import java.util.Scanner;

public class DivingForGold {

	private static class Pair {
		int x;
		boolean y;
	}

	private static Pair makepair(int x, boolean y) {
		Pair p = new Pair();
		p.x = x;
		p.y = y;
		return p;
	}

	private static final int[] weights = new int[30];
	private static final int[] gold = new int[30];

	private static final Pair[][] dp = new Pair[30][1001];

	private static final String[] res = new String[30];
	private static int resCount;

	private static int t, w, n;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean first = true;
		while (s.hasNext()) {

			if (!first) {
				System.out.println();
			} else {
				first = false;
			}

			t = s.nextInt();
			w = s.nextInt();
			n = s.nextInt();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= t; j++) {
					dp[i][j] = null;
				}
			}

			for (int i = 0; i < n; i++) {
				weights[i] = s.nextInt();
				gold[i] = s.nextInt();
			}

			resCount = 0;
			System.out.println(rec(0, t).x);

			int time_left = t;
			for (int i = 0; i < n; i++) {
				if (rec(i, time_left).y) {
					res[resCount++] = weights[i] + " " + gold[i];
					time_left -= 3 * w * weights[i];
				}
			}
			System.out.println(resCount);
			for (int i = 0; i < resCount; i++) {
				System.out.println(res[i]);
			}
		}
	}

	private static Pair rec(int i, int timeleft) {
		if (timeleft < 0)
			return makepair(Integer.MIN_VALUE / 2, false);
		if (i == n)
			return makepair(0, false);
		if (dp[i][timeleft] != null)
			return dp[i][timeleft];

		Pair p1 = rec(i + 1, timeleft - 3 * w * weights[i]);
		Pair p2 = rec(i + 1, timeleft);

		if (p1.x + gold[i] > p2.x) {
			return dp[i][timeleft] = makepair(p1.x + gold[i], true);
		} else {
			return dp[i][timeleft] = makepair(p2.x, false);
		}
	}
}
