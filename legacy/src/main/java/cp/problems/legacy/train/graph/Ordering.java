package cp.problems.legacy.train.graph;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Ordering {

	private static final TreeSet<String> orderings = new TreeSet<>();
	private static final ArrayList<Integer> builder = new ArrayList<>();
	private static final boolean[][] adj = new boolean[30][30];
	private static final boolean[] active = new boolean[30];
	private static final int[] color = new int[30];
	private static final int WHITE = 0, GRAY = 1, BLACK = 2;
	private static boolean cyclePresent;
	private static int n;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		s.nextLine();

		while (tc-- > 0) {
			s.nextLine();
			String chars = s.nextLine();
			n = 0;

			for (int i = 0; i < active.length; i++) {
				active[i] = false;
				for (int j = 0; j < active.length; j++) {
					adj[i][j] = false;
				}
			}

			for (int i = 0; i < chars.length(); i++) {
				if (chars.charAt(i) != ' ') {
					active[chars.charAt(i) - 'A'] = true;
					n++;
				}
			}
			String[] constraints = s.nextLine().split(" ");
			for (String constraint : constraints) {
				adj[constraint.charAt(0) - 'A'][constraint.charAt(2) - 'A'] = true;
			}

			boolean cycle = checkCycle();
			setUnvisited();

			if (cycle) {
				System.out.println("NO");
			} else {
				orderings.clear();
				for (int i = 0; i < active.length; i++) {
					if (active[i]) {
						builder.add(i);
						color[i] = BLACK;
						getOrderings();
						color[i] = WHITE;
						builder.remove(builder.size() - 1);
					}
				}
				while (!orderings.isEmpty()) {
					System.out.println(orderings.pollFirst());
				}
			}
			if (tc != 0)
				System.out.println();
		}

	}

	private static void getOrderings() {
		if (builder.size() == n) {
			StringBuilder ordering = new StringBuilder((char) (builder.get(0) + 'A') + "");
			for (int i = 1; i < builder.size(); i++) {
				ordering.insert(0, (char) (builder.get(i) + 'A') + " ");
			}
			orderings.add(ordering.toString());
		} else {
			for (int i = 0; i < active.length; i++) {
				if (active[i] && color[i] == WHITE) {
					boolean valid = true;
					for (Integer integer : builder) {
						if (adj[integer][i]) {
							valid = false;
							break;
						}
					}
					if (valid) {
						builder.add(i);
						color[i] = BLACK;
						getOrderings();
						color[i] = WHITE;
						builder.remove(builder.size() - 1);
					}
				}
			}
		}
	}

	private static void setUnvisited() {
		for (int i = 0; i < active.length; i++) {
			color[i] = WHITE;
		}

	}

	private static boolean checkCycle() {
		cyclePresent = false;
		for (int i = 0; i < active.length; i++) {
			if (active[i]) {
				detectCycle(i);
				if (cyclePresent) {
					break;
				}
			}
		}
		return cyclePresent;
	}

	private static void detectCycle(int current) {
		color[current] = GRAY;
		for (int i = 0; i < active.length; i++) {
			if (active[i] && adj[current][i]) {
				if (color[i] == WHITE) {
					detectCycle(i);
				} else if (color[i] == GRAY) {
					cyclePresent = true;
				}
			}
		}
		color[current] = BLACK;
	}

}
