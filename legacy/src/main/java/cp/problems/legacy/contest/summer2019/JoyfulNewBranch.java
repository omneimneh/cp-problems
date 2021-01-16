package cp.problems.legacy.contest.summer2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JoyfulNewBranch {

	private static int n, w, h;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("joyful.in")); // "JOYFUL>INNINNN"
		int tc = s.nextInt();
		while (tc-- > 0) {
			n = s.nextInt();
			w = s.nextInt();
			h = s.nextInt();

			double bs = bs();
			System.out.printf("%.4f\n", bs * bs);
		}
	}

	private static double bs() {
		double low = 0, hi = Double.MAX_VALUE;
		while (hi - low > 0.000001) {
			double mid = low + (hi - low) / 2;
			if (ok(mid)) {
				low = mid;
			} else {
				hi = mid;
			}
		}
		return ok(hi) ? hi : low;
	}

	private static boolean ok(double sqr) {
		int ww = (int) Math.floor(w / (sqr));
		int hh = (int) Math.floor(h / (sqr));
		return ww * hh >= n;
	}

}
