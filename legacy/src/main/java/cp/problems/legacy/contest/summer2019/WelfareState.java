package cp.problems.legacy.contest.summer2019;

import java.util.Scanner;

public class WelfareState {

	private static final int[] a = new int[(int) 2e5];
	private static final boolean[] payoff = new boolean[(int) 6e5];
	private static int lastPayoff = 0;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();

		for (int i = 0; i < n; i++) {
			a[i] = s.nextInt();
		}

		int q = s.nextInt();
		while (q-- > 0) {
			if (s.nextInt() == 1) {
				int prs = s.nextInt() - 1;
				int amount = s.nextInt();
				a[prs] = amount;
				removePayoff(0, n - 1, prs, 0);
			} else {
				lastPayoff = Math.max(lastPayoff, s.nextInt());
				setPayoff();
			}
		}

		for (int i = 0; i < n; i++) {
			if (hasPayoff(0, n - 1, i, 0)) {
				System.out.print(Math.max(a[i], lastPayoff) + " ");
			} else {
				System.out.print(a[i] + " ");
			}
		}
		System.out.println();
	}

	private static void removePayoff(int left, int right, int p, int index) {

		if (p < left || p > right) {
			return;
		}

		if (left == p && right == p) {
			payoff[index] = false;
			return;
		}

		int mid = (left + right) / 2;

		if (payoff[index]) {
			payoff[index] = false;
			payoff[2 * index + 1] = true;
			payoff[2 * index + 2] = true;
		}

		removePayoff(left, mid, p, 2 * index + 1);
		removePayoff(mid + 1, right, p, 2 * index + 2);

	}

	private static void setPayoff() {
		payoff[0] = true;
	}

	private static boolean hasPayoff(int left, int right, int p, int index) {
		if (p < left || p > right) {
			return false;
		}

		if (p == right && p == left) {
			return payoff[index];
		}

		if (p >= left && p <= right) {
			if (payoff[index])
				return true;
		}

		int mid = (left + right) / 2;
		return hasPayoff(left, mid, p, 2 * index + 1) || hasPayoff(mid + 1, right, p, 2 * index + 2);

	}

}
