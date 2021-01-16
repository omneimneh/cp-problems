package cp.problems.legacy.train.introduction;

import java.util.Scanner;

public class CostCutting {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		int k = 1;
		while (tc-- > 0) {
			int x1 = s.nextInt();
			int x2 = s.nextInt();
			int x3 = s.nextInt();
			if ((x1 <= x2 && x1 >= x3) || (x1 >= x2 && x1 <= x3)) {
				System.out.println("Case " + k++ + ": " + x1);
			} else if ((x2 <= x1 && x2 >= x3) || (x2 >= x1 && x2 <= x3)) {
				System.out.println("Case " + k++ + ": " + x2);
			} else {
				System.out.println("Case " + k++ + ": " + x3);
			}
		}
	}

}
