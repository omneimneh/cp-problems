package cp.problems.legacy.contest.coach2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class I {

	private static final boolean[] vis = new boolean[(int) 1e5];
	private static final boolean[] color = new boolean[(int) 1e5];
	@SuppressWarnings("unchecked")
	private static final LinkedList<Integer>[] adj = new LinkedList[(int) 1e5];
	private static int m, n;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("cp.problems.uva.team.in")));
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}
		int tc = s.nextInt();
		while (tc-- > 0) {
			n = s.nextInt();
			m = s.nextInt();

			for (int i = 0; i < n; i++) {
				adj[i].clear();
				vis[i] = false;
			}

			for (int i = 0; i < m; i++) {
				int x = s.nextInt() - 1;
				int y = s.nextInt() - 1;
				adj[x].add(y);
				adj[y].add(x);
			}

			boolean bip = true;
			for (int i = 0; i < n; i++) {
				if (!vis[i]) {
					vis[i] = true;
					if (!bip(i, false)) {
						bip = false;
						break;
					}
				}
			}

			System.out.println(bip ? "YES" : "NO");
		}

	}

	private static boolean bip(int i, boolean b) {
		color[i] = b;
		boolean res = true;
		for (int v : adj[i]) {
			if (!vis[v]) {
				vis[v] = true;
				res &= bip(v, !b);
			} else {
				if (color[v] == b)
					return false;
			}
		}
		return res;
	}
}
