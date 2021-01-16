package cp.problems.legacy.train.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ForwardingEmails {

	private static final int[] forwardTo = new int[50000];
	private static final boolean[] visited = new boolean[50000];
	private static final Queue<Integer> cycle = new LinkedList<>();
	private static final int[] cycleLength = new int[50000];
	private static final Integer[] dp = new Integer[50000];

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		int t = 0;
		while (t++ < tc) {

			int n = s.nextInt();
			for (int i = 0; i < n; i++) {
				forwardTo[s.nextInt() - 1] = s.nextInt() - 1;
				cycleLength[i] = -1;
				visited[i] = false;
				dp[i] = null;
			}

			int max = 0;
			int maxIndex = -1;
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					traverse(i, 0);
				}

			}
			for (int i = 0; i < n; i++) {
				int trav = rec(i);
				if (trav > max) {
					max = trav;
					maxIndex = i;
				}
			}

			System.out.printf("Case %d: %d\n", t, maxIndex + 1);
		}

	}

	public static void traverse(int person, int deep) {
		if (cycleLength[person] != -1) {
			cycle.clear();
			return;
		}
		if (visited[person]) {

			int cycleSize = 0;
            //noinspection ConstantConditions
            while (cycle.peek() != person) {
				cycle.poll();
				cycleSize++;
				if (cycle.isEmpty())
					return;
			}
			while (!cycle.isEmpty()) {
				cycleLength[cycle.poll()] = deep - cycleSize;
			}
			return;
		}
		cycle.add(person);
		visited[person] = true;
		traverse(forwardTo[person], deep + 1);
	}

	public static int rec(int person) {
		if (cycleLength[person] != -1)
			return cycleLength[person];
		if (dp[person] != null)
			return dp[person];
		return dp[person] = 1 + rec(forwardTo[person]);
	}

}
