package cp.problems.legacy.contest.summer2019;

import java.util.Scanner;

public class Zamalek {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while (tc-- > 0) {
			int h1 = s.nextInt();
			int a2 = s.nextInt();
			int h2 = s.nextInt();
			int a1 = s.nextInt();

			int t1 = h1 + a1;
			int t2 = h2 + a2;

			if (t1 != t2) {
				System.out.println(t1 > t2 ? "first" : "second");
			} else {
				t1 = h1 + 2 * a1;
				t2 = h2 + 2 * a2;
				if (t1 != t2) {
					System.out.println(t1 > t2 ? "first" : "second");
				} else {
					System.out.println("penalties");
				}
			}
		}

	}

}
