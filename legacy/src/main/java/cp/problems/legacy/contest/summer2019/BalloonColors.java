package cp.problems.legacy.contest.summer2019;

import java.util.Scanner;

public class BalloonColors {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();

		while (tc-- > 0) {
			int r = s.nextInt();
			int g = s.nextInt();
			int b = s.nextInt();
			for (int i = 0; i < 8; i++) {
				boolean different = (binDigit(r, 8 - i - 1) ^ binDigit(b, i)) == 1;
				if (different) {
					if (binDigit(b, i) == 1) {
						b -= 1 << i;
					} else {
						r -= 1 << (8 - i - 1);
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				boolean different = (binDigit(g, i) ^ binDigit(g, 8 - i - 1)) == 1;
				if (different) {
					if (binDigit(g, i) == 1) {
						g -= 1 << i;
					} else {
						g -= 1 << (8 - i - 1);
					}
				}
			}

			System.out.printf("%s %s %s\n", pad(r), pad(g), pad(b));
		}
	}

	private static String pad(int num) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			ret.insert(0, num % 10);
			num /= 10;
		}
		return ret.toString();
	}

	private static int binDigit(int num, int at) {
		return ((num >> at) & 1);
	}
}
