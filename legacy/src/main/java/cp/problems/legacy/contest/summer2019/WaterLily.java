package cp.problems.legacy.contest.summer2019;

import java.util.Scanner;

public class WaterLily {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		double h = s.nextInt();
		double l = s.nextInt();
		double m = Math.sqrt(h * h + l * l);
		double sinAngle = l / m;
		double angle = Math.asin(sinAngle);
		double thirdAngle = Math.PI - angle - angle;
		double k = sinAngle * m / Math.sin(thirdAngle);
		System.out.printf("%.7f\n", (k - h));

	}

}
