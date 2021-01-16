package cp.problems.legacy.train.math;

import java.util.Scanner;

public class SafeSaluations {

	static long choose(int n, int k) {
		long ret = 1;
		for (int i = k + 1; i <= n; i++) {
			int d = i - k;
			int q = i / d;
			int r = i - q * d;
			ret = ret * q + ret * r / d;
		}
		return ret;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean first = true;
		while (s.hasNext()) {
			int n = s.nextInt();
			if (!first) {
				System.out.println();
			} else {
				first = false;
			}
			System.out.println(choose(2 * n, n) / (n + 1));
		}
	}
}
