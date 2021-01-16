package cp.problems.legacy.contest.coach2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class C {

	private static final int DAY = 86400;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("time.in")));
		int tc = s.nextInt();
		while (tc-- > 0) {
			int h = s.nextInt();
			int m = s.nextInt();
			int sec = s.nextInt();

			int add = s.nextInt();

			int time = h * 3600 + m * 60 + sec + add;
			time %= DAY;

			sec = time % 60;
			time /= 60;
			m = time % 60;
			time /= 60;
			h = time;
			System.out.println(h + " " + m + " " + sec);
		}

	}

}
