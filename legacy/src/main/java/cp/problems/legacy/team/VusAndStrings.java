package cp.problems.legacy.team;

import java.util.Scanner;

public class VusAndStrings {

	private static final int[] cumulative = new int[1000000];
	private static int onesInB = 0;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		char[] a = s.next().toCharArray();
		char[] b = s.next().toCharArray();

		for (char c : b) {
			if (c == '1') {
				onesInB++;
			}
		}

		cumulative[0] = a[0] == '1' ? 1 : 0;
		for (int i = 1; i < a.length; i++) {
			cumulative[i] = cumulative[i - 1] + (a[i] == '1' ? 1 : 0);
		}

		int evenCount = 0;
		for (int i = 0; i < a.length - b.length + 1; i++) {
			int j = i + b.length - 1;
			int diff = Math.abs(onesInB - onesInInterval(i, j));
			if (diff % 2 == 0) {
				evenCount++;
			}
		}
		System.out.println(evenCount);

	}

	private static int onesInInterval(int start, int end) {
		if (start == 0)
			return cumulative[end];
		return cumulative[end] - cumulative[start - 1];
	}

}
