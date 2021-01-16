package cp.problems.legacy.facebook;

import java.util.Scanner;

@SuppressWarnings("DuplicatedCode")
public class Leapfrog1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		int c = 0;
		while (c++ < tc) {
			char[] str = s.next().toCharArray();
			int dotCount = 0;
			int BCount = 0;
			for (int i = 1; i < str.length; i++) {
				if (str[i] == '.') {
					dotCount++;
				} else {
					BCount++;
				}
			}
			System.out.printf("Case #%d: %s\n", c, (BCount >= dotCount && dotCount > 0 ? "Y" : "N"));
		}
	}
}
