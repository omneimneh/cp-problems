package cp.problems.legacy.contest.coach2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class E {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("game.in")));
		int tc = s.nextInt();
		while (tc-- > 0) {
			char[] chars = s.next().toCharArray();
			int upper = 0;
			int lower = 0;
			for (char aChar : chars) {
				if (Character.isUpperCase(aChar)) {
					upper++;
				} else {
					lower++;
				}
			}
			if (lower > upper) {
				for (char aChar : chars) {
					if (!Character.isUpperCase(aChar)) {
						System.out.print(aChar);
					}
				}
			} else {
				for (char aChar : chars) {
					if (Character.isUpperCase(aChar)) {
						System.out.print(aChar);
					}
				}
			}
			System.out.println();
		}

	}

}
