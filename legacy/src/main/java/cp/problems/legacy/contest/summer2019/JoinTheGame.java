package cp.problems.legacy.contest.summer2019;

import java.util.Scanner;

public class JoinTheGame {

	public static void main(String[] args) {
		Scanner s = new Scanner(/* new File("join.in") */ System.in);
		int tc = s.nextInt();
		while (tc-- > 0) {
			String[] exp = s.next().split("-");

			StringBuilder result = new StringBuilder(exp[0]);
			for (int i = 1; i < exp.length; i++) {
				result.append("-").append(exp[i].charAt(0));
				if (exp[i].length() > 1) {
					int j = 0;

					while (++j < exp[i].length() && exp[i].charAt(j) == '0') {
						result.append("+0");
					}

					int firstSign = exp[i].length();
					for (int k = j; k < exp[i].length(); k++) {
						if (exp[i].charAt(j) == '+') {
							firstSign = j;
							break;
						}
					}

					if (j < firstSign)
						result.append("+").append(exp[i].substring(j));
					else
						result.append(exp[i].substring(j));
				}
			}
			System.out.println(result);

		}

	}
}
