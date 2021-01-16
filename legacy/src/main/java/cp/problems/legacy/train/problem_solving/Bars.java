package cp.problems.legacy.train.problem_solving;

import java.util.Scanner;

public class Bars {

	private static int n, p;
	private static final int[] bars = new int[20];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while (tc-- > 0) {
			n = s.nextInt();
			p = s.nextInt();
			for (int i = 0; i < p; i++) {
				bars[i] = s.nextInt();
			}

			if (rec(0, 0)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

	}

	private static boolean rec(int index, int length) {
		if (length == n)
			return true;

		if (length > n || index == p)
			return false;

		return rec(index + 1, length) || rec(index + 1, length + bars[index]);
	}

}
