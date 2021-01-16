package cp.problems.legacy.train.math;

import java.util.Scanner;

public class TriangleCounting {

	private static final long[] result = new long[(int) 1e6 + 1];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		result[3] = 0L;
		for (long i = 4; i < result.length; i++) {
			result[(int) i] = result[(int) (i - 1)] + ((i - 1) * (i - 2) / 2 - (i - 1) / 2) / 2;
		}
		while (true) {
			int n = s.nextInt();
			if (n < 3)
				break;
			System.out.println(result[n]);
		}
	}
}
