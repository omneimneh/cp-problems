package cp.problems.legacy.train.math;

import java.util.Scanner;

public class FindTheWays {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			int n = s.nextInt();
			int k = s.nextInt();
			double digits = 0;
			if (k > n - k) {
				for (int i = k + 1; i <= n; i++) {
					digits += Math.log10(i) - Math.log10(n - i + 1);
				}
			} else {
				for (int i = n - k + 1; i <= n; i++) {
					digits += Math.log10(i) - Math.log10(n - i + 1);
				}
			}
			System.out.println((int) Math.floor(digits) + 1);
		}

	}
}
