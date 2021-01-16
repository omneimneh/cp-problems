package cp.problems.legacy.contest.summer2019;

import java.util.Scanner;

public class CityDay {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int x = s.nextInt();
		int y = s.nextInt();
		int[] a = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = s.nextInt();
		}

		for (int i = 0; i < n; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = Math.max(0, i - x); j < i; j++) {
				min = Math.min(min, a[j]);
			}
			for (int j = i + 1; j <= Math.min(n - 1, i + y); j++) {
				min = Math.min(min, a[j]);
			}
			if (min > a[i]) {
				System.out.println(i + 1);
				break;
			}
		}

	}

}
