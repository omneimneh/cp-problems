package cp.problems.legacy.train.graph;

import java.util.HashMap;
import java.util.Scanner;

public class ConcurrentEvents {

	private static final boolean[][] adj = new boolean[200][200];

	private static final HashMap<String, Integer> hashmap = new HashMap<>();
	private static final String[] inverse = new String[1000];
	private static int counter;

	private static int nc;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int tc = 1;
		while (true) {

			nc = s.nextInt();

			counter = 0;
			hashmap.clear();
			for (int i = 0; i < adj.length; i++) {
				for (int j = 0; j < adj.length; j++) {
					adj[i][j] = false;
				}
			}

			if (nc == 0)
				break;
			for (int i = 0; i < nc; i++) {
				int ne = s.nextInt();
				String before = s.next();
				inverse[counter] = before;
				hashmap.put(before, counter++);
				if (ne > 1) {
					for (int j = 1; j < ne; j++) {
						String after = s.next();
						inverse[counter] = after;
						hashmap.put(after, counter++);
						addEdge(before, after);
						before = after;
					}
				}
			}
			int nm = s.nextInt();
			for (int i = 0; i < nm; i++) {
				String s1 = s.next();
				String s2 = s.next();
				addEdge(s1, s2);
			}

			for (int k = 0; k < counter; k++) {
				for (int i = 0; i < counter; i++) {
					for (int j = 0; j < counter; j++) {
						adj[i][j] = adj[i][j] || adj[i][k] && adj[k][j];
					}
				}
			}

			int count = 0;
			System.out.printf("Case %d, ", tc++);
			StringBuilder resultString = new StringBuilder();
			for (int i = 0; i < counter; i++) {
				for (int j = i + 1; j < counter; j++) {
					if (!adj[i][j] && !adj[j][i]) {
						if (count < 2) {
							resultString.append(String.format("(%s,%s) ", inverse[i], inverse[j]));
						}
						count++;
					}
				}
			}
			if (count == 0)
				System.out.println("no concurrent events.");
			else
				System.out.printf("%d concurrent events:\n%s\n", count,
						resultString.toString());

		}

	}

	private static void addEdge(String from, String to) {
		if (!hashmap.containsKey(from)) {
			inverse[counter] = from;
			hashmap.put(from, counter++);
		}
		if (!hashmap.containsKey(to)) {
			inverse[counter] = to;
			hashmap.put(to, counter++);
		}
		adj[hashmap.get(from)][hashmap.get(to)] = true;
	}
}
