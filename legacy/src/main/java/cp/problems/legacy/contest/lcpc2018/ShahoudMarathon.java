package cp.problems.legacy.contest.lcpc2018;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ShahoudMarathon {

	private static final int RANGE = 30;
	private static final int INPUT_SIZE = (int) 1e5 + 1;
	@SuppressWarnings("unchecked")
	private static final LinkedList<Integer>[] adj = new LinkedList[INPUT_SIZE];
	private static final int[] sizeOfSubtree = new int[INPUT_SIZE];
	private static final int[] parent = new int[INPUT_SIZE];
	private static final int[][] RMQ = new int[3 * INPUT_SIZE][RANGE];
	private static final int[] A = new int[INPUT_SIZE];
	private static final int[][] XOR = new int[INPUT_SIZE][RANGE];
	private static final int[] order = new int[INPUT_SIZE];
	private static final boolean[][] lazy = new boolean[3 * INPUT_SIZE][RANGE];
	private static int N, count;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("cp.problems.uva.train.in")));

		int tc = s.nextInt();

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}

		while (tc-- > 0) {

			N = s.nextInt();
			for (int i = 0; i < N; i++) {
				A[i] = s.nextInt();
				adj[i].clear();
				for (int j = 0; j < RANGE; j++) {
					lazy[i][j] = false;
				}
			}
			for (int i = 1; i < N; i++) {
				int u = s.nextInt() - 1;
				adj[u].add(i);
				parent[i] = u;
			}

			count = 0;
			sizeOfSubtree[0] = dfs(0, A[0]);

			for (int i = 0; i < RANGE; i++) {
				constructRMQ(0, N - 1, 0, i);
			}

			int q = s.nextInt();
			while (q-- > 0) {
				if (s.nextInt() == 1) {
					int root = s.nextInt() - 1;
					int start = order[root];
					int end = start + sizeOfSubtree[root] - 1;
					int sum = 0;
					for (int i = 0; i < RANGE; i++) {
						int tmp = sum(start, end, 0, N - 1, 0, i);
						if (root == 0 || XOR[parent[root]][i] == 0) {
							sum += tmp * (1 << i);
						} else {
							sum += (sizeOfSubtree[root] - tmp) * (1 << i);
						}
					}
					System.out.println(sum);
				} else {
					int root = s.nextInt() - 1;
					int value = s.nextInt();

					int start = order[root];
					int end = start + sizeOfSubtree[root] - 1;

					for (int i = 0; i < RANGE; i++) {
						if (((value >> i) & 1) != ((A[root] >> i) & 1)) {
							updateRange(start, end, 0, N - 1, 0, i);
						}
					}
					A[root] = value;
				}
			}
		}
	}

	private static int sum(int s, int e, int left, int right, int index, int bit) {

		if (lazy[index][bit]) {
			RMQ[index][bit] = (right - left + 1) - RMQ[index][bit];
			if (right != left) {
				lazy[2 * index + 1][bit] = !lazy[2 * index + 1][bit];
				lazy[2 * index + 2][bit] = !lazy[2 * index + 2][bit];
			} else {
				XOR[left][bit] = 1 ^ XOR[left][bit];
			}
			lazy[index][bit] = false;
		}

		if (left > e || right < s)
			return 0;

		if (left >= s && right <= e)
			return RMQ[index][bit];

		int mid = (left + right) / 2;

		return sum(s, e, left, mid, 2 * index + 1, bit) + sum(s, e, mid + 1, right, 2 * index + 2, bit);
	}

	private static int constructRMQ(int s, int e, int index, int bit) {

		if (s == e) {
			return RMQ[index][bit] = XOR[s][bit];
		}

		int mid = (s + e) / 2;

		return RMQ[index][bit] = constructRMQ(s, mid, 2 * index + 1, bit)
				+ constructRMQ(mid + 1, e, 2 * index + 2, bit);
	}

	private static void updateRange(int s, int e, int left, int right, int index, int bit) {

		if (lazy[index][bit]) {
			RMQ[index][bit] = (right - left + 1) - RMQ[index][bit];
			if (left != right) {
				lazy[2 * index + 1][bit] = !lazy[2 * index + 1][bit];
				lazy[2 * index + 2][bit] = !lazy[2 * index + 2][bit];
			} else {
				XOR[left][bit] = 1 ^ XOR[left][bit];
			}
			lazy[index][bit] = false;
		}

		if (right < s || left > e)
			return;

		if (left >= s && right <= e) {
			RMQ[index][bit] = (right - left + 1) - RMQ[index][bit];

			if (left != right) {
				lazy[2 * index + 1][bit] = !lazy[2 * index + 1][bit];
				lazy[2 * index + 2][bit] = !lazy[2 * index + 2][bit];
			} else {
				XOR[left][bit] = 1 ^ XOR[left][bit];
			}
			return;
		}

		int mid = (left + right) / 2;
		updateRange(s, e, left, mid, 2 * index + 1, bit);
		updateRange(s, e, mid + 1, right, 2 * index + 2, bit);

		RMQ[index][bit] = RMQ[index * 2 + 1][bit] + RMQ[index * 2 + 2][bit];
	}

	private static int dfs(int i, int xor) {
		int sum = 1;
		for (int bit = 0; bit < RANGE; bit++) {
			XOR[i][bit] = (xor >> bit) & 1;
		}
		order[i] = count++;
		for (int j : adj[i]) {
			sum += dfs(j, xor ^ A[j]);
		}
		return sizeOfSubtree[i] = sum;
	}

	private static class Scanner {
		private final BufferedReader reader;
		private StringTokenizer st;

		public Scanner(FileReader stream) {
			reader = new BufferedReader(stream);
		}

		public String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					String line = reader.readLine();
					if (line == null)
						return null;
					st = new StringTokenizer(line);
				} catch (Exception e) {
					throw (new RuntimeException());
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}
	}

}