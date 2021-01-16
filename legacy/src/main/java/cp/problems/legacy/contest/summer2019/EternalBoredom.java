package cp.problems.legacy.contest.summer2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EternalBoredom {

	private static int n;
	private static final int[] words = new int[1000];
	private static final long mod = 1000000007L;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("eternal.in"));
		int tc = s.nextInt();
		while (tc-- > 0) {
			n = s.nextInt();
			int h = s.nextInt();
			int w = s.nextInt();

			for (int i = 0; i < n; i++) {
				words[i] = s.nextInt();
			}
			ArrayList<Integer> list = new ArrayList<>();
			list.add(0);
			long singleLine = poss(0, w, 0, list);
			long finalResult = pow(singleLine, h);
			System.out.println(finalResult);
		}
	}

	private static long pow(long p, int h2) {
		long result = p;
		for (int i = 1; i < h2; i++) {
			result = (p * result) % mod;
		}
		return result;
	}

	private static long poss(int currentWord, int w_left, int count, ArrayList<Integer> repeated) {

		if (currentWord == n) {
			if (w_left == -1) {
				long ret = fact(count);
				for (Integer integer : repeated) {
					ret /= fact(integer);
				}
				return ret;
			} else {
				return 0;
			}
		}

		if (w_left < -1) {
			return 0;
		}

		incLast(repeated, 1);
		long possib1 = poss(currentWord, w_left - words[currentWord] - 1, count + 1, repeated);
		incLast(repeated, -1);

		repeated.add(0);
		long possib2 = poss(currentWord + 1, w_left, count, repeated);
		repeated.remove(repeated.size() - 1);

		return (possib1 + possib2) % mod;
	}

	private static void incLast(ArrayList<Integer> repeated, int val) {
		repeated.set(repeated.size() - 1, repeated.get(repeated.size() - 1) + val);
	}

	private static long fact(long n) {
		if (n == 0)
			return 1;
		return (n * fact(n - 1)) % mod;
	}

	@SuppressWarnings("DuplicatedCode")
	private static class Scanner {
		public BufferedReader reader;
		public StringTokenizer st;

		public Scanner(File file) throws FileNotFoundException {
			reader = new BufferedReader(new FileReader(file));
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