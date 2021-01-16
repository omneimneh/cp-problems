package cp.problems.legacy.others;

import java.util.Scanner;

public class StripsColoring {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            int N = s.nextInt();
            int K = s.nextInt();
            char[] digits = s.next().toCharArray();

            int[][] dp = new int[K + 1][N];
            char[][] lastDigit = new char[K + 1][N];

            for (int len = 0; len < N; len++) {
                for (int k = 0; k <= K; k++) {
                    if (len == 0) {

                        dp[k][len] = 0;

                        lastDigit[k][len] = digits[len];

                    } else {

                        char currentDigit = digits[len];

                        if (k == 0) {

                            if (currentDigit != digits[len - 1]) {
                                dp[k][len] = dp[k][len - 1] + 1;
                            } else {
                                dp[k][len] = dp[k][len - 1];
                            }
                            lastDigit[k][len] = currentDigit;

                        } else {

                            if (currentDigit == lastDigit[k][len - 1]) {
                                dp[k][len] = dp[k][len - 1];
                                lastDigit[k][len] = currentDigit;
                            } else {
                                if (currentDigit == '1') {
                                    lastDigit[k][len] = '1';
                                    dp[k][len] = dp[k][len - 1] + 1;
                                } else if (currentDigit == '0') {
                                    int changedLast = dp[k - 1][len - 1];
                                    int didNotChange = dp[k][len - 1] + 1; // nb of cuts++
                                    if (changedLast < didNotChange) {
                                        lastDigit[k][len] = '1';
                                        dp[k][len] = changedLast;
                                    } else {
                                        lastDigit[k][len] = '0';
                                        dp[k][len] = didNotChange;
                                    }
                                }
                            }

                        }
                    }

                }
            }


            System.out.println(dp[K][N - 1]);

        }
    }
}
