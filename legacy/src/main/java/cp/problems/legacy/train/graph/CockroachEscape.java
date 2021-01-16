package cp.problems.legacy.train.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CockroachEscape {

	private static class Nest {
		int deep;
		int index;

		Nest(int index, int deep) {
			this.index = index;
			this.deep = deep;
		}
	}

	private static final int[][] adj = new int[25][25];
	private static final int[][] newAdj = new int[25][25];
	private static final boolean[] visited = new boolean[25];
	private static int n, m;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int tc = s.nextInt();
		int t = 0;

		while (t++ < tc) {
			n = s.nextInt();
			for (int i = 0; i < n; i++) {
				visited[i] = false;
				adj[i][i] = newAdj[i][i] = 0;
				for (int j = i + 1; j < n; j++) {
					adj[j][i] = newAdj[j][i] = adj[i][j] = newAdj[i][j] = Integer.MAX_VALUE / 2;
				}
			}
			m = s.nextInt();
			for (int i = 0; i < m; i++) {
				int a1 = s.nextInt();
				int a2 = s.nextInt();
				adj[a1][a2] = adj[a2][a1] = 1;
			}

			int MIN = Integer.MAX_VALUE;

			for (int z = 0; z < n; z++) {

				for (int i = 0; i < n; i++) {
					visited[i] = false;
					newAdj[i][i] = 0;
					for (int j = i + 1; j < n; j++) {
						newAdj[i][j] = newAdj[j][i] = Integer.MAX_VALUE / 2;
					}
				}

				Queue<Nest> q = new LinkedList<>();
				q.add(new Nest(z, 0));
				visited[z] = true;

				while (!q.isEmpty()) {
					Nest current = q.poll();
					for (int i = 0; i < n; i++) {
						if (adj[current.index][i] == 1 && !visited[i]) {
							visited[i] = true;
							newAdj[i][current.index] = newAdj[current.index][i] = 1;
							q.add(new Nest(i, current.deep + 1));
						}
					}
				}

				for (int k = 0; k < n; k++) {
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							newAdj[i][j] = Math.min(newAdj[i][j], newAdj[i][k] + newAdj[k][j]);
						}
					}
				}

				int MAX = 0;
				for (int i = 0; i < n; i++) {
					for (int j = i + 1; j < n; j++) {
						MAX = Math.max(newAdj[i][j], MAX);
					}
				}

				MIN = Math.min(MIN, MAX);

			}

			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					
					if (adj[a][b] == 1) {
						
						for (int i = 0; i < n; i++) {
							visited[i] = false;
							newAdj[i][i] = 0;
							for (int j = i + 1; j < n; j++) {
								newAdj[i][j] = newAdj[j][i] = Integer.MAX_VALUE / 2;
							}
						}

						Queue<Nest> q = new LinkedList<>();
						q.add(new Nest(a, 0));
						q.add(new Nest(b, 0));
						visited[a] = visited[b] = true;
						newAdj[a][b] = newAdj[b][a] = 1;

						while (!q.isEmpty()) {
							Nest current = q.poll();
							for (int i = 0; i < n; i++) {
								if (adj[current.index][i] == 1 && !visited[i]) {
									visited[i] = true;
									newAdj[i][current.index] = newAdj[current.index][i] = 1;
									q.add(new Nest(i, current.deep + 1));
								}
							}
						}

						for (int k = 0; k < n; k++) {
							for (int i = 0; i < n; i++) {
								for (int j = 0; j < n; j++) {
									newAdj[i][j] = Math.min(newAdj[i][j], newAdj[i][k] + newAdj[k][j]);
								}
							}
						}

						int MAX = 0;
						for (int i = 0; i < n; i++) {
							for (int j = i + 1; j < n; j++) {
								MAX = Math.max(newAdj[i][j], MAX);
							}
						}

						MIN = Math.min(MIN, MAX);
					}
				}
			}

			System.out.printf("Case #%d:\n%d\n", t, MIN);
			System.out.println();

		}

	}

}
