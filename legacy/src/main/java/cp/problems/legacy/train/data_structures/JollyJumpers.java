package cp.problems.legacy.train.data_structures;

import java.util.Scanner;

public class JollyJumpers {

	private static final int[] array = new int[3001];
	private static final boolean[] jump = new boolean[3000];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			int n = s.nextInt();

			for (int i = 0; i < n; i++) {
				array[i] = s.nextInt();
			}

			for (int i = 0; i < n; i++) {
				jump[i] = false;
			}

			boolean flag = false;

			for (int i = 0; i < n - 1; i++) {
				int abs = Math.abs(array[i + 1] - array[i]);
				if (abs < n && !jump[abs]) {
					jump[abs] = true;
				} else {
					flag = true;
					break;
				}
			}

			if (flag) {
				System.out.println("Not jolly");
			} else {
				System.out.println("Jolly");
			}
		}
	}

}
