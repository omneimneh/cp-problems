package cp.problems.legacy.contest.coach2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

public class L {

	private static char[] str1, str2;
	private static Integer[][] dp;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("strings.in")));
		int tc = s.nextInt();
		while (tc-- > 0) {
			str1 = s.next().toCharArray();
			str2 = s.next().toCharArray();
			dp = new Integer[str1.length][str2.length];
			System.out.println(edist(str1.length - 1, str2.length - 1));
		}
	}

	private static int edist(int i, int j) {
		if (i == -1)
			return j + 1;
		if (j == -1)
			return i + 1;
		if (dp[i][j] != null) {
			return dp[i][j];
		}
		int min = Integer.MAX_VALUE;
		if (str1[i] == str2[j]) {
			min = Math.min(min, edist(i - 1, j - 1));
		} else {
			min = 1 + Math.min(edist(i - 1, j), edist(i, j - 1));
		}
		return dp[i][j] = min;
	}

	private static class Scanner {
		public BufferedReader reader;
		public StringTokenizer st;

		public Scanner(FileReader stream) {
			reader = new BufferedReader(stream);
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
