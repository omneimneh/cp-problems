package cp.problems.legacy.train.problem_solving;

import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("DuplicatedCode")
public class Zones {

	private static class Pair<K, V> {
		K k;
		V v;

		Pair(K k, V v) {
			this.k = k;
			this.v = v;
		}
	}

	private static final ArrayList<Pair<Integer, ArrayList<Integer>>> common = new ArrayList<>(10);
	private static final int[] towers = new int[20];
	private static final boolean[] taken = new boolean[20];
	private static final boolean[] cpy = new boolean[20];
	private static int max;
	private static int n;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = 1;
		while (true) {

			n = s.nextInt();
			int b = s.nextInt();

			if (b == 0 && n == 0) {
				break;
			}

			for (int i = 0; i < n; i++) {
				towers[i] = s.nextInt();
			}

			common.clear();

			int m = s.nextInt();
			for (int i = 0; i < m; i++) {
				int num = s.nextInt();
				ArrayList<Integer> tows = new ArrayList<>();
				for (int j = 0; j < num; j++) {
					tows.add(s.nextInt() - 1);
				}
				common.add(new Pair<>(s.nextInt(), tows));
			}

			max = Integer.MIN_VALUE;
			rec(0, 0, b);

			System.out.println("Case Number  " + tc++);
			System.out.println("Number of Customers: " + max);
			System.out.print("Locations recommended: ");
			boolean flg = false;
			for (int i = 0; i < n; i++) {
				if (cpy[i]) {
					if (flg) {
						System.out.print(" ");
					} else {
						flg = true;
					}
					System.out.print(i + 1);
				}
			}
			System.out.println();
			System.out.println();
		}

	}

	private static void rec(int people, int current, int left) {
		if (current == n || left == 0) {
			if (people > max) {
				max = people;
				if (n >= 0) System.arraycopy(taken, 0, cpy, 0, n);
			}
			return;
		}
		int tmp = addTower(current);
		taken[current] = true;
		rec(people + tmp, current + 1, left - 1);
		taken[current] = false;
		rec(people, current + 1, left);

	}

	private static int addTower(int t) {
		int res = towers[t];
		for (Pair<Integer, ArrayList<Integer>> c : common) {
			if (c.v.contains(t)) {
				if (containsTaken(c.v)) {
					res -= c.k;
				}
			}
		}
		return res;
	}

	private static boolean containsTaken(ArrayList<Integer> v) {
		for (int t : v) {
			if (taken[t])
				return true;
		}
		return false;
	}

}
