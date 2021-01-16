package cp.problems.legacy.train.math;

import java.util.Scanner;

public class SpanningSubtree {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int c = 1;
		while (true) {
			int n = s.nextInt();
			if (n == 0)
				break;
			System.out.printf("Case %d: %d\n", c++, n / 2);
		}

	}

}
