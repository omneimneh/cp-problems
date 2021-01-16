package cp.problems.legacy.contest.coach2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class D {
	public static void main(String[] args) throws FileNotFoundException {
		java.util.Scanner s = new java.util.Scanner(new FileReader(new File("vote.in")));
		int tc = s.nextInt();
		while (tc-- > 0) {
			int x = s.nextInt();
			int y = s.nextInt();
			int z = s.nextInt();
			System.out.println(Math.max(x, Math.max(y, z)));
		}
	}
}
