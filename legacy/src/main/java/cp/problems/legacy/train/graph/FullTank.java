package cp.problems.legacy.train.graph;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class FullTank {
	private static final int INF = 1000000000;

	private static class Item implements Comparable<Item> {
		int node, cost, fuel;

		Item(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		Item(int node, int cost, int fuel) {
			this.node = node;
			this.cost = cost;
			this.fuel = fuel;
		}

		@Override
		public int compareTo(Item o) {
			return Integer.compare(cost, o.cost);
		}
	}

	@SuppressWarnings("unchecked")
	private static final ArrayList<Item>[] adj = new ArrayList[1000];
	private static int n;
	private static int st;
	private static int end;
	private static int c;
	private static final int[] p = new int[1000];
	private static final int[][] dist = new int[1000][101];
	private static final PrintWriter out = new PrintWriter(System.out);
	private static final PriorityQueue<Item> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		n = readInt();
		int m = readInt();
		for (int i = 0; i < n; i++) {
			p[i] = readInt();
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			int x = readInt();
			int y = readInt();
			int w = readInt();
			adj[x].add(new Item(y, w));
			adj[y].add(new Item(x, w));
		}
		int q = readInt();
		while (q-- > 0) {
			c = readInt();
			st = readInt();
			end = readInt();
			int res = dijkestra();
			out.println(res == INF ? "impossible" : res);
		}
		out.flush();
	}

	private static int dijkestra() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 100; j++) {
				dist[i][j] = INF;
			}
		}
		pq.clear();
		pq.offer(new Item(st, 0, 0));
		dist[0][0] = 0;
		while (!pq.isEmpty()) {
			Item i = pq.poll();

			if (i.node == end)
				return i.cost;

			if (i.fuel < c && dist[i.node][i.fuel + 1] > i.cost + p[i.node]) {
				dist[i.node][i.fuel + 1] = i.cost + p[i.node];
				pq.offer(new Item(i.node, dist[i.node][i.fuel + 1], i.fuel + 1));
			}

			for (Item j : adj[i.node]) {
				if (i.fuel >= j.cost) {
					if (dist[j.node][i.fuel - j.cost] > i.cost) {
						dist[j.node][i.fuel - j.cost] = i.cost;
						pq.offer(new Item(j.node, i.cost, i.fuel - j.cost));
					}
				}
			}
		}
		return INF;
	}

	@SuppressWarnings("DuplicatedCode")
	public static int readInt() throws IOException {
		int ret = 0;
		boolean dig = false;
		for (int c; (c = System.in.read()) != -1;) {
			if (c >= '0' && c <= '9') {
				dig = true;
				ret = ret * 10 + c - '0';
			} else if (dig)
				break;
		}
		return ret;
	}

}
