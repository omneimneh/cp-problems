package cp.problems.legacy.train.introduction;

import java.util.Scanner;

public class RelationalOperator {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while(tc-- >0) {
			long x1 = s.nextLong();
			long x2 = s.nextLong();
			System.out.println(x1 > x2 ? ">" : x1 == x2 ? "=" : "<");
		}

	}

}
