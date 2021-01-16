package cp.problems.legacy.facebook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Tourist2018 {

	private static class City implements Comparable<City> {
		String name;
		int visits;
		int index;

		City(String name, int visits, int index) {
			this.name = name;
			this.visits = visits;
			this.index = index;
		}

		@Override
		public int compareTo(City o) {
			if (visits != o.visits)
				return Integer.compare(visits, o.visits);
			return Integer.compare(index, o.index);
		}
	}

	private static final City[] cities = new City[51];

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("out.txt"));
		int tc = s.nextInt();
		int c = 0;
		while (c++ < tc) {
			int n = s.nextInt();
			int k = s.nextInt();
			BigInteger v = s.nextBigInteger().subtract(BigInteger.ONE);
			for (int i = 0; i < n; i++) {
				cities[i] = new City(s.next(), 0, i);
			}

			int counter = 0;

			while (true) {

				for (int i = 0; i < k; i++) {
					cities[i].visits++;
				}

				Arrays.sort(cities, 0, n);

				counter++;
				boolean repeatBase = true;
				int visits = cities[0].visits;
				for (int i = 0; i < n; i++) {
					if (cities[i].visits != visits || cities[i].index != i) {
						repeatBase = false;
						break;
					}
				}

				if (repeatBase) {
					break;
				}
			}

			//System.out.println(counter);
			int v2 = v.mod(new BigInteger("" + counter)).intValue();

			while (v2-- > 0) {

				for (int i = 0; i < k; i++) {
					cities[i].visits++;
				}

				Arrays.sort(cities, 0, n);

			}

			Arrays.sort(cities, 0, k, Comparator.comparingInt(arg0 -> arg0.index));

			System.out.printf("Case #%d: ", c);
			for (int i = 0; i < k - 1; i++) {
				System.out.print(cities[i].name + " ");
			}
			System.out.print(cities[k - 1].name + "\n");
		}
		fw.close();
	}

}
