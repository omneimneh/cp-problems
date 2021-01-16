package cp.problems.legacy.train.problem_solving;

import java.util.Scanner;

public class BitMask {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		while (s.hasNext()) {

			long n = s.nextLong();
			long l = s.nextLong();
			long u = s.nextLong();

			long result = 0;

			boolean leading = true;

			for (int i = 32; i >= 0; i--) {
				if (leading) {
					long ldigit = getDigit(l, i);
					long udigit = getDigit(u, i);

					if (ldigit == udigit) {
						result += ldigit << i;
					} else {
						leading = false;
						if ((getDigit(n, i) == 0 || result < ((l >> i) << i))) {
							result += 1L << i;
						}

						if (result > u) {
							result -= 1L << i;
						}
					}

				} else {
					if ((getDigit(n, i) == 0 || result < ((l >> i) << i))) {
						result += 1L << i;
					}

					if (result > u) {
						result -= 1L << i;
					}
				}
			}
			System.out.println(result);
		}
	}

	private static long getDigit(long x, int at) {
		return 1 & ((x & (1 << at)) >> at);
	}
}
