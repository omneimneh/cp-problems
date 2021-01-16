package cp.problems.legacy.contest.summer2019;

import java.util.Scanner;

public class HeavyCaffeine {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while (tc-- > 0) {
			int X = s.nextInt();
			int x = s.nextInt();
			int y = s.nextInt();
			if (X <= x) {
				System.out.println(0);
			} else {
				System.out.println((int) Math.ceil((X - x) / (double) y));
			}
		}
	}

}
