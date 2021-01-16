package cp.problems.legacy.week1;

import java.util.Scanner;

public class SternBrocot {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {
			int m = s.nextInt();
			int n = s.nextInt();
			if (m == 1 && n == 1) {
				break;
			}
			long M = 1;
			long N = 1;
			long leftM = 0;
			long leftN = 1;
			long rightM = 1;
			long rightN = 0;
			while (N != n || M != m) {
				if (N * m < M * n) {
					System.out.print("L");
					rightM = M;
					rightN = N;
					M = leftM + M;
					N = leftN + N;
				} else {
					System.out.print("R");
					leftM = M;
					leftN = N;
					M = rightM + M;
					N = rightN + N;
				}
			}
			System.out.println();
		}

	}

}
