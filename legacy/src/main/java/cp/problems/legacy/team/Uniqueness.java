package cp.problems.legacy.team;

import java.util.HashMap;
import java.util.Scanner;

public class Uniqueness {

	private static final HashMap<Integer, Boolean> numbers = new HashMap<>();
	private static final int[] arr = new int[2000];
	private static int n;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
		}

		int min = n - 1;
		numbers.clear();
		for (int i = 0; i < n; i++) {
			min = Math.min(min, bs(i) - i);
			if (numbers.containsKey(arr[i]))
				break;
			numbers.put(arr[i], true);
		}
		System.out.println(min);
	}

	private static int bs(int i) {
		int low = i;
		int hi = n;
		while (hi != low) {
			int mid = (hi + low) / 2;
			if (ok(mid - 1)) {
				hi = mid;
			} else {
				low = mid + 1;
			}
		}
		return ok(low) ? low : hi;
	}

	private static boolean ok(int v) {
		HashMap<Integer, Boolean> exists = new HashMap<>();
		for (int i = v + 1; i < n; i++) {
			if (numbers.containsKey(arr[i]) || exists.containsKey(arr[i]))
				return false;
			exists.put(arr[i], true);
		}
		return true;
	}

}
