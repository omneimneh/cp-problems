package cp.problems.legacy.team;

import java.util.Scanner;

public class XORinacci {

	private static final byte[][][] dp = new byte[60][2][2];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		for (byte a = 0; a < 2; a++) {
			for (byte b = 0; b < 2; b++) {
				dp[0][a][b] = a;
				dp[1][a][b] = b;
				for (int i = 2; i < dp.length; i++) {
					dp[i][a][b] = (byte) ((dp[i - 1][a][b] ^ dp[i - 2][a][b]) & 1);
				}
			}
		}

		int tc = s.nextInt();

		while (tc-- > 0) {
			int a = s.nextInt();
			int b = s.nextInt();
			int n = s.nextInt();
			int res = 0;
			for (int i = 0; i < 32; i++) {
				int da = (a >> i) & 1;
				int db = (b >> i) & 1;
				res |= (dp[n % 60][da][db]) << i;
			}
			System.out.println(res);
		}

	}

}
