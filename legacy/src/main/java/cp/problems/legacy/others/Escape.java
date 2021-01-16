package cp.problems.legacy.others;

import java.util.Scanner;

public class Escape {

	private static double vp, vd, t, f, c;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		vp = s.nextDouble();
		vd = s.nextDouble();
		t = s.nextDouble();
		f = s.nextDouble();
		c = s.nextDouble();

		System.out.println(lol());
	}

	private static int lol() {
		double distance = t * vp;
		double time = t;

		int b = 0;

		while (distance < c) {
			if (vp >= vd)
				break;
			time += (distance) / (vd - vp);
			distance = time * vp;
			if (distance >= c)
				break;
			b++;
			time += distance / vd + f;
			distance = time * vp;
		}
		return b;
	}

}
