package cp.problems.legacy.train.problem_solving;

import java.util.Scanner;

public class DynamicFrog {

	private static final int[] rocks = new int[102];
	private static final boolean[] big = new boolean[102];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int tc = s.nextInt();

		for (int icase = 1; icase <= tc; icase++) {
			int n = s.nextInt();
			int d = s.nextInt();

			big[0] = big[n + 1] = true;
			rocks[0] = 0;
			rocks[n + 1] = d;

			for (int i = 1; i <= n; i++) {
				String str = s.next();
				big[i] = str.charAt(0) == 'B';
				rocks[i] = Integer.parseInt(str.substring(2));
			}

			int minDist = rocks[1] - rocks[0];
			for (int i = 1; i <= n; i++) {
				if (big[i]) {
					minDist = Math.max(minDist, rocks[i + 1] - rocks[i]);
				} else {
					minDist = Math.max(minDist, rocks[i + 1] - rocks[i - 1]);
				}
			}
			System.out.printf("Case %d: %d\n", icase, minDist);
		}

	}

}
