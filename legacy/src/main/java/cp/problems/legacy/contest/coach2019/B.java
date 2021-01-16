package cp.problems.legacy.contest.coach2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class B {

	private static final int[] map1 = new int[100];
	private static final int[] map2 = new int[100];

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("scoreboard.in")));
		int tc = s.nextInt();
		while (tc-- > 0) {
			int n = s.nextInt();
			for (int i = 0; i < n; i++) {
				map1[s.nextInt() - 1] = i;
			}
			for (int i = 0; i < n; i++) {
				map2[s.nextInt() - 1] = i;
			}
			for (int i = 0; i < n; i++) {
				if (map1[i] == map2[i]) {
					System.out.printf("cp.problems.uva.team %d stayed at %s position\n", i + 1,
							posify(map1[i]));
				} else {
					System.out.printf(
							"cp.problems.uva.team %d moved from %s position to %s position\n",
							i + 1, posify(map1[i]), posify(map2[i]));
				}
			}
		}
	}

	private static String posify(int i) {
		if (i == 10 || i == 11 || i == 12)
			return (i + 1) + "th";
		if (i % 10 == 0)
			return (i + 1) + "st";
		if (i % 10 == 1)
			return (i + 1) + "nd";
		if (i % 10 == 2)
			return (i + 1) + "rd";
		return (i + 1) + "th";
	}
}
