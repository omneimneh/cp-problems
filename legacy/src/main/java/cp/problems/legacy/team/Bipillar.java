package cp.problems.legacy.team;

import java.util.Scanner;

public class Bipillar {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] pillars = new int[n];
		int max = 0, maxIndex = -1;
		for (int i = 0; i < pillars.length; i++) {
			pillars[i] = s.nextInt();
			if (pillars[i] > max) {
				max = pillars[i];
				maxIndex = i;
			}
		}

		int left = maxIndex - 1;
		int right = maxIndex + 1;

		boolean working = true;

		while (true) {
			int newMax = -1;
			if (left >= 0) {
				newMax = pillars[left];
			}
			if (right < pillars.length) {
				if (pillars[right] > newMax) {
					newMax = pillars[right];
					right++;
				} else if (pillars[right] == newMax) {
					working = false;
					break;
				} else {
					left--;
				}
			} else {
				left--;
			}
			if (newMax == -1) {
				break;
			}
			if (newMax >= max) {
				working = false;
				break;
			}
			max = newMax;
		}

		System.out.println(working ? "YES" : "NO");

	}

}
