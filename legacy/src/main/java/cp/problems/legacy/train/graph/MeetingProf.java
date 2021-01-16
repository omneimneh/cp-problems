package cp.problems.legacy.train.graph;

import java.util.Scanner;
import java.util.TreeSet;

public class MeetingProf {

	private static final int[][] adj1 = new int['Z' - 'A' + 1]['Z' - 'A' + 1];
	private static final int[][] adj2 = new int['Z' - 'A' + 1]['Z' - 'A' + 1];

	private static final int[][] shortest1 = new int['Z' - 'A' + 1]['Z' - 'A' + 1];
	private static final int[][] shortest2 = new int['Z' - 'A' + 1]['Z' - 'A' + 1];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {
			reset();
			int n = s.nextInt();
			if (n == 0)
				break;
			for (int i = 0; i < n; i++) {
				char young = s.next().charAt(0);
				char uni = s.next().charAt(0);
				int u = s.next().charAt(0) - 'A';
				int v = s.next().charAt(0) - 'A';
				int value = s.nextInt();
				if (young == 'Y') {
					shortest1[u][v] = adj1[u][v] = Math.min(adj1[u][v], value);
					if (uni == 'B') {
						shortest1[v][u] = adj1[v][u] = Math.min(adj1[v][u], value);
					}
				} else {
					shortest2[u][v] = adj2[u][v] = Math.min(adj2[u][v], value);
					if (uni == 'B') {
						shortest2[v][u] = adj2[v][u] = Math.min(adj2[v][u], value);
					}
				}
			}

			for (int k = 0; k < adj1.length; k++) {
				for (int i = 0; i < adj1.length; i++) {
					for (int j = 0; j < adj1[i].length; j++) {
						shortest1[i][j] = Math.min(shortest1[i][j], shortest1[i][k] + shortest1[k][j]);
						shortest2[i][j] = Math.min(shortest2[i][j], shortest2[i][k] + shortest2[k][j]);
					}
				}
			}

			int city1 = s.next().charAt(0) - 'A';
			int city2 = s.next().charAt(0) - 'A';

			int shortest = Integer.MAX_VALUE;
			TreeSet<Integer> results = new TreeSet<>();
			for (int i = 0; i < adj1.length; i++) {
				int otherDist = shortest1[city1][i] + shortest2[city2][i];
				if (otherDist < shortest) {
					results.clear();
					results.add(i);
					shortest = otherDist;
				} else if (otherDist == shortest) {
					results.add(i);
				}
			}

			if (shortest < 100000) {
				System.out.print(shortest);
				while (!results.isEmpty()) {
					//noinspection ConstantConditions
					System.out.print(" " + (char) (results.pollFirst() + 'A'));
				}
				System.out.println();
			} else {
				System.out.println("You will never meet.");
			}

		}

	}

	private static void reset() {
		for (int i = 0; i < adj1.length; i++) {
			for (int j = 0; j < adj1[i].length; j++) {
				if (i != j)
					shortest1[i][j] = shortest2[i][j] = adj1[i][j] = adj2[i][j] = Integer.MAX_VALUE / 4;
			}
			shortest1[i][i] = shortest2[i][i] = adj1[i][i] = adj2[i][i] = 0;
		}
	}
}
