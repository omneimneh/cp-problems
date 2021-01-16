package cp.problems.legacy.contest.summer2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FillTheCoachsBattery {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner s = new Scanner(new File("fill.in"));
		int tc = s.nextInt();
		while (tc-- > 0) {
			int n = s.nextInt();
			int x = s.nextInt();
			int y = s.nextInt();

			int currentCharge = 0, time = 0, firstTime = -1;
			boolean found = false;

			for (int i = 0; i < n; i++) {
				int num = s.nextInt();
				if (firstTime == -1) {

					if (num < 0) {
						currentCharge = Math.max(0,
								currentCharge - y * Math.abs(num));
					} else {
						if (currentCharge + x * Math.abs(num) < 100) {
							currentCharge = currentCharge + x * Math.abs(num);
						} else {
							time += Math.ceil((100 - currentCharge)
									/ (double) x);
							firstTime = time;
						}
					}
					if (!found) {
						time += Math.abs(num);
						if (currentCharge == 100) {
							found = true;
						}
					}
				}
			}
			System.out.println(firstTime);
		}
	}
}
