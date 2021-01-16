package cp.problems.legacy.train.problem_solving;

import java.util.Scanner;

public class FillTheContainers {

	private static int n, m;
	private static final long[] vessels = new long[1000];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			n = s.nextInt();
			m = s.nextInt();
			for (int i = 0; i < n; i++) {
				vessels[i] = s.nextLong();
			}

			System.out.println(bs());
		}

	}

	private static long bs() {
		long low = 0;
		long hi = Long.MAX_VALUE;
		long mid;
		while (hi - low > 1) {
			mid = low + (hi - low) / 2;
			if (ok(mid)) {
				hi = mid;
			} else {
				low = mid;
			}
		}
		return ok(low) ? low : hi;
	}

	private static boolean ok(long mid) {
		int container = 1;
		long containerFilled = 0;
		for (int i = 0; i < n; i++) {
			if (vessels[i] > mid)
				return false;
			if (containerFilled + vessels[i] <= mid) {
				containerFilled += vessels[i];
			} else {
				container++;
				containerFilled = vessels[i];
			}
		}
		return container <= m;
	}

}
