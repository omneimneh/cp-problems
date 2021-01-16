package cp.problems.legacy.train.data_structures;

import java.util.Arrays;
import java.util.Scanner;

public class LittleGirl {

	private static class Elem implements Comparable<Elem> {
		int value;
		int index;

		Elem(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(Elem o) {
			return Integer.compare(value, o.value);
		}

		@Override
		public String toString() {
			return "[" + index + ", " + value + "]";
		}

	}

	private static int n, q;
	private static final int[] arr = new int[200005];
	private static final int[] lefts = new int[200005];
	private static final int[] rights = new int[200005];
	private static final Elem[] counters = new Elem[200005];
	private static final int[] sortedArr = new int[200005];
	private static final long[] sums = new long[200005];
	private static final int[] st = new int[600015];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		q = s.nextInt();
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
		}
		for (int i = 0; i < q; i++) {
			int l = s.nextInt() - 1;
			int r = s.nextInt() - 1;
			lefts[i] = l;
			rights[i] = r;
			lazyIncrement(l, r, 0, n - 1, 0);
		}
		for (int i = 0; i < n; i++) {
			counters[i] = new Elem(i, valueAt(i, 0, n - 1, 0));
		}
		Arrays.sort(arr, 0, n);
		Arrays.sort(counters, 0, n);

		for (int i = 0; i < n; i++) {
			sortedArr[counters[i].index] = arr[i];
		}

		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += sortedArr[i];
			sums[i] = sum;
		}

		long finalSum = 0;
		for (int i = 0; i < q; i++) {
			finalSum += sums[rights[i]] - (lefts[i] > 0 ? sums[lefts[i] - 1] : 0);
		}

		System.out.println(finalSum);

	}

	private static void lazyIncrement(int l, int r, int start, int end, int node) {
		if (l <= start && end <= r) {
			st[node]++;
			return;
		}

		if (l > end || r < start) {
			return;
		}

		int mid = (start + end) / 2;

		lazyIncrement(l, r, start, mid, 2 * node + 1);
		lazyIncrement(l, r, mid + 1, end, 2 * node + 2);
	}

	private static int valueAt(int index, int start, int end, int node) {
		if (start == index && end == index) {
			return st[node];
		}

		if (start > index || end < index) {
			return 0;
		}

		int mid = (start + end) / 2;

		int leftSide = valueAt(index, start, mid, 2 * node + 1);
		int rightSide = valueAt(index, mid + 1, end, 2 * node + 2);

		return leftSide + rightSide + st[node];

	}

}
