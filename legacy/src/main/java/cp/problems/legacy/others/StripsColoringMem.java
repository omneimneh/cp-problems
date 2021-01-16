package cp.problems.legacy.others;

import java.util.Scanner;

public class StripsColoringMem {
    private static final int[][] dp = new int[100001][2];
    private static final char[][] lastDigit = new char[100001][2];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            int N = s.nextInt();
            int K = s.nextInt();
            char[] digits = s.next().toCharArray();

            for (int len = 0; len < N; len++) {
                for (int k = 0; k <= K; k++) {
                    if (len == 0) {

                        dp[k][1 & len] = 0;

                        lastDigit[k][1 & len] = digits[len];

                    } else {

                        char currentDigit = digits[len];

                        if (k == 0) {

                            if (currentDigit != digits[len - 1]) {
                                dp[k][1 & len] = dp[k][1 ^ len % 2] + 1;
                            } else {
                                dp[k][1 & len] = dp[k][1 ^ len % 2];
                            }
                            lastDigit[k][1 & len] = currentDigit;

                        } else {

                            if (currentDigit == lastDigit[k][1 ^ len % 2]) {
                                dp[k][1 & len] = dp[k][1 ^ len % 2];
                                lastDigit[k][1 & len] = currentDigit;
                            } else {
                                if (currentDigit == '1') {
                                    lastDigit[k][1 & len] = '1';
                                    dp[k][1 & len] = dp[k][1 ^ len % 2] + 1;
                                } else if (currentDigit == '0') {
                                    int changedLast = dp[k - 1][1 ^ len % 2];
                                    int didNotChange = dp[k][1 ^ len % 2] + 1; // nb of cuts++
                                    if (changedLast < didNotChange) {
                                        lastDigit[k][1 & len] = '1';
                                        dp[k][1 & len] = changedLast;
                                    } else {
                                        lastDigit[k][1 & len] = '0';
                                        dp[k][1 & len] = didNotChange;
                                    }
                                }
                            }

                        }
                    }

                }
            }


            System.out.println(dp[K][1 ^ (N - 1) % 2]);

        }
    }
}
