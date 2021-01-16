package cp.problems.legacy.team;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DIYLadder {

	static int[] wood = new int[(int) 1e5 + 1];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while (tc-- > 0) {
			int n = s.nextInt();
			int max = -1;
			int maxIndex = -1;
			for (int i = 0; i < n; i++) {
				wood[i] = s.nextInt();
				if (wood[i] > max) {
					max = wood[i];
					maxIndex = i;
				}
			}
			int secondMax = -1;
			for (int i = 0; i < n; i++) {
				if (wood[i] > secondMax && i != maxIndex) {
					secondMax = wood[i];
				}
			}
			int k = Math.min(n - 2, Math.min(max, secondMax) - 1);
			System.out.println(k);
		}

	}

	private static class Scanner {
		private final BufferedReader reader;
		private StringTokenizer st;

		public Scanner(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream));
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
