package cp.problems.legacy.team;

import java.awt.Point;
import java.util.Arrays;
import java.util.Scanner;

public class ArraySplitting {

	private static final int[] arr = new int[(int) 3e5 + 1];
	private static final Point[] diff = new Point[(int) 3e5 + 1];
	private static final boolean[] split = new boolean[(int) 3e5 + 1];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int k = s.nextInt();

		arr[0] = s.nextInt();
		for (int i = 1; i < n; i++) {
			arr[i] = s.nextInt();
			diff[i - 1] = new Point(i - 1, arr[i] - arr[i - 1]);
		}

		Arrays.sort(diff, 0, n - 1, (o1, o2) -> Integer.compare(o2.y, o1.y));

		for (int i = 0; i < k - 1; i++) {
			split[diff[i].x] = true;
		}

		int min = arr[0];
		int max = arr[0];
		int sum = 0;
		for (int i = 1; i < n; i++) {
			if (split[i - 1]) {
				sum += max - min;
				min = arr[i];
				max = arr[i];
			}
			if (arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		sum += max - min;
		System.out.println(sum);

	}
}
