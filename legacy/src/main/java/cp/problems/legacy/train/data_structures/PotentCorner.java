package cp.problems.legacy.train.data_structures;

import java.util.Scanner;

public class PotentCorner {

	private static final int[] cornersWeight = new int[1 << 17];
	private static int N;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			N = s.nextInt();
			int cornersCount = 1 << N;
			for (int i = 0; i < cornersCount; i++) {
				cornersWeight[i] = s.nextInt();
			}

			int max = 0;
			for (int i = 0; i < cornersCount; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(potencies(i) + potencies(i ^ (1 << j)), max);
				}
			}
			System.out.println(max);
		}

	}
	
	private static int potencies(int i) {
		int sum = 0;
		for (int j = 0; j < N; j++) {
			sum += cornersWeight[i ^ (1 << j)];
		}
		return sum;
	}

}
