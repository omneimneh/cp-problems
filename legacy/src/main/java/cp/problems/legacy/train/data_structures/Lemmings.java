package cp.problems.legacy.train.data_structures;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Lemmings {

	private static final Comparator<Integer> comp = (o1, o2) -> -Integer.compare(o1, o2);

	private static final PriorityQueue<Integer> green = new PriorityQueue<>(comp);
	private static final PriorityQueue<Integer> blue = new PriorityQueue<>(comp);

	private static final int[] gs = new int[100000];
	private static final int[] bs = new int[100000];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while (tc-- > 0) {
			int b = s.nextInt();
			int sg = s.nextInt();
			int sb = s.nextInt();
			for (int i = 0; i < sg; i++) {
				green.add(s.nextInt());
			}
			for (int i = 0; i < sb; i++) {
				blue.add(s.nextInt());
			}

			while (!green.isEmpty() && !blue.isEmpty()) {
				int battles = Math.min(b, Math.min(green.size(), blue.size()));
				for (int i = 0; i < battles; i++) {
					gs[i] = green.remove();
					bs[i] = blue.remove();
				}
				for (int i = 0; i < battles; i++) {
					int diff = gs[i] - bs[i];
					if (diff != 0) {
						if (diff > 0) {
							green.add(diff);
						} else {
							blue.add(-diff);
						}
					}
				}
			}

			if (green.isEmpty() && blue.isEmpty()) {
				System.out.println("green and blue died");
			} else if (green.isEmpty()) {
				System.out.println("blue wins");
				while (!blue.isEmpty()) {
					System.out.println(blue.poll());
				}
			} else {
				System.out.println("green wins");
				while (!green.isEmpty()) {
					System.out.println(green.poll());
				}
			}

			if (tc != 0)
				System.out.println();

		}

	}

}
