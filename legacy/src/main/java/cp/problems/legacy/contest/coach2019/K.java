package cp.problems.legacy.contest.coach2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class K {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader(new File("floor.in")));
		int tc = s.nextInt();
		PrintWriter out = new PrintWriter(System.out);
		while (tc-- > 0) {
			double w = s.nextDouble() / 2;
			double h = s.nextDouble();
			double a = Math.toRadians(s.nextDouble());
			double r = Math.sin(a) * h / Math.sin(Math.PI / 2 - a);
			double area = Math.PI * r * r;
			if (r <= w) {
				out.printf("%.4f\n", area);
			} else {
				double sideTri = Math.sqrt(r * r - w * w);
				double triArea = sideTri * w;
				double sectorAngle = Math.asin(sideTri / r) * 2;
				double sectorArea = sectorAngle * area / (2 * Math.PI);
				out.printf("%.4f\n", area - 2 * (sectorArea - triArea));
			}
		}
		out.flush();
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

		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}
}
