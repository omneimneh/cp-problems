package cp.problems.legacy.contest.coach2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

public class A {

	private static final int[] girls = new int[(int) 1e5];

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("girls.in")));
		int tc = s.nextInt();
		while (tc-- > 0) {
			int n = s.nextInt();
			for (int i = 0; i < n; i++) {
				girls[i] = s.nextInt();
			}
			int max = girls[0];
			System.out.print(1);
			for (int i = 1; i < n; i++) {
				if (max < girls[i]) {
					max = girls[i];
					System.out.print(" " + (i + 1));
				}
			}
			System.out.println();
		}

	}

	private static class Scanner {
		public BufferedReader reader;
		public StringTokenizer st;

		public Scanner(FileReader fileReader) {
			reader = new BufferedReader(fileReader);
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
