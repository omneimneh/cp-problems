package cp.problems.legacy.others;

import java.util.Scanner;

public class Glider {

	private static final int[] ints = new int[(int) 4e5 + 2];
	private static int n, h, total;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		h = s.nextInt();

		total = 2 * n + 2;

		ints[0] = (int) -1e9;
		ints[total - 1] = (int) 1e9;

		for (int i = 1; i <= 2 * n; i++) {
			ints[i] = s.nextInt();
		}

		int max = h;
		int start = 0;
		int end = 0;
		int hleft = h;

		while (start < total) {
			while (end < total - 1 && hleft > 0) {
				if (end % 2 == 0) {
					if (hleft >= ints[end + 1] - ints[end]) {
						hleft -= ints[end + 1] - ints[end];
						end++;
					} else if (start == end) {
						end = ++start;
					} else {
						break;
					}
				} else {
					end++;
				}
			}

			if (start >= total)
				break;

			max = Math.max(max, ints[end] - ints[start] + hleft);
			if (start % 2 == 0) {
				hleft += ints[start + 1] - ints[start];
			}
			start++;
		}

		System.out.println(max);
	}

}
