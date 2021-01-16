package cp.problems.legacy.contest.coach2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class F {
	private static int n;
	private static final int[] arr = new int[(int) 1e5];
	private static final HashMap<Integer, Boolean> map = new HashMap<>();
	private static final HashMap<Integer, Integer> current = new HashMap<>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(new File("smallest.in")));
		int tc = sc.nextInt();
		while (tc-- > 0) {
			n = sc.nextInt();
			map.clear();
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
				if (!map.containsKey(arr[i])) {
					map.put(arr[i], true);
				}
			}
			int distinctCount = map.keySet().size();

			current.clear();
			int start = 0;
			int end = 1;
			current.put(arr[0], 0);
			int totalValues = 1;

			int min = Integer.MAX_VALUE;

			while (start < end) {
				while (end < n && totalValues < distinctCount) {
					if (!current.containsKey(arr[end]))
						totalValues++;
					current.put(arr[end], end);
					end++;
				}

				while (current.get(arr[start]) != start) {
					start++;
				}

				if (totalValues == distinctCount) {
					min = Math.min(min, end - start);
				}

				current.remove(arr[start]);
				totalValues--;
				start++;
			}

			System.out.println(min);

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
