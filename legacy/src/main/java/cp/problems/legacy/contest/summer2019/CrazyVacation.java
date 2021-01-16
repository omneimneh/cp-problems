package cp.problems.legacy.contest.summer2019;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CrazyVacation {

	private static int n, m;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int tc = s.nextInt();
		while (tc-- > 0) {
			n = s.nextInt();
			m = s.nextInt();

			String bestCity = "-1";
			int maxDiff = Integer.MIN_VALUE;
			int largestMidday = Integer.MIN_VALUE;

			for (int i = 0; i < n; i++) {
				String currentCity = s.next();
				int max = Integer.MIN_VALUE;
				for (int j = 0; j < m; j++) {
					max = Math.max(max, s.nextInt());
				}
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < m; j++) {
					min = Math.min(min, s.nextInt());
				}
				if (Math.abs(max - min) > maxDiff) {
					maxDiff = Math.abs(max - min);
					bestCity = currentCity;
					largestMidday = max;
				} else if (Math.abs(max - min) == maxDiff
						&& max > largestMidday) {
					largestMidday = max;
					bestCity = currentCity;
				}
			}
			System.out.println(bestCity);
			// out.flush();
		}

	}

	private static class Scanner {
		public BufferedReader reader;
		public StringTokenizer st;

		public Scanner(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream));
			st = null;
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
