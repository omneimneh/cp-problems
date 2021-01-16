package cp.problems.legacy.week8;

import java.util.Scanner;

public class Largest {

	private static class Block {
		int x1, y1, x2, y2;

		Block(int r1, int c1, int r2, int c2) {
			x1 = r1;
			y1 = c1;
			x2 = r2;
			y2 = c2;
		}

		boolean overlaps(int r1, int c1, int r2, int c2) {
			return ((r1 >= x1 && r1 <= x2) || (r2 >= x1 && r2 <= x2) || (x1 >= r1 && x1 <= r2)
					|| (x2 >= r1 && x2 <= r2))
					&& ((c1 >= y1 && c1 <= y2) || (c2 >= y1 && c2 <= y2) || (y1 >= c1 && y1 <= c2)
							|| (y2 >= c1 && y2 <= c2));
		}

	}

	private static final Block[] blocks = new Block[10000];
	private static int n, s;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int p = sc.nextInt();
		while (p-- > 0) {
			s = sc.nextInt();
			n = sc.nextInt();

			for (int i = 0; i < n; i++) {
				blocks[i] = new Block(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1);
			}

			int maxArea = 0;
			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					for (int i2 = i; i2 < s; i2++) {
						for (int j2 = j; j2 < s; j2++) {
							int newArea = (i2 - i + 1) * (j2 - j + 1);
							if (newArea > maxArea) {
								boolean valid = true;
								for (int k = 0; k < n; k++) {
									if (blocks[k].overlaps(i, j, i2, j2)) {
										valid = false;
										break;
									}
								}
								if (valid) {
									maxArea = newArea;
								}
							}
						}
					}
				}
			}
			System.out.println(maxArea);
		}
	}

}
