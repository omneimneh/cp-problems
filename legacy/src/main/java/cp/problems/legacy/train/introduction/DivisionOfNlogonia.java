package cp.problems.legacy.train.introduction;

import java.util.Scanner;

public class DivisionOfNlogonia {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int q;
		while ((q = s.nextInt()) != 0) {
			int dvX = s.nextInt();
			int dvY = s.nextInt();
			for (int i = 0; i < q; i++) {
				int x = s.nextInt();
				int y = s.nextInt();
				if (x == dvX || y == dvY) {
					System.out.println("divisa");
				} else if (x < dvX && y < dvY) {
					System.out.println("SO");
				} else if (x < dvX) {
					System.out.println("NO");
				} else if (y < dvY) {
					System.out.println("SE");
				} else {
					System.out.println("NE");
				}
			}
		}

	}

}
