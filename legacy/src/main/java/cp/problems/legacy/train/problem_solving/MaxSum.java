package cp.problems.legacy.train.problem_solving;

import java.util.Scanner;

public class MaxSum {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n;
		while ((n = s.nextInt()) != 0) {
			boolean flag = false;
			for (int i = 0; i < n; i++) {
				int x = s.nextInt();
				if (x != 0) {
					if (!flag) {
						flag = true;
					} else {
						System.out.print(" ");
					}
					System.out.print(x);
				}
			}
			if (!flag) {
				System.out.println(0);
			} else {
				System.out.println();
			}
		}
	}
}
