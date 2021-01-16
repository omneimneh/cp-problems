package cp.problems.legacy.week6;

import java.util.Scanner;

public class GarbageHeap {

    private static final long[][][] garbage = new long[21][21][21];
    private static final long[][][] sum = new long[21][21][21];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long tc = s.nextLong();
        while (tc-- > 0) {
            long A = s.nextLong();
            long B = s.nextLong();
            long C = s.nextLong();

            for (int i = 0; i < A; i++) {
                for (int j = 0; j < B; j++) {
                    for (int k = 0; k < C; k++) {
                        garbage[i][j][k] = s.nextLong();
                        if (i == 0) {
                            if (j == 0) {
                                if (k == 0) {
                                    sum[i][j][k] = garbage[i][j][k];
                                } else {
                                    sum[i][j][k] = garbage[i][j][k] + sum[i][j][k - 1];
                                }
                            } else {
                                if (k == 0) {
                                    sum[i][j][k] = garbage[i][j][k] + sum[i][j - 1][k];
                                } else {
                                    sum[i][j][k] = garbage[i][j][k] + sum[i][j - 1][k] + sum[i][j][k - 1] - sum[i][j - 1][k - 1];
                                }
                            }
                        } else if (j == 0) {
                            if (k == 0) {
                                sum[i][j][k] = garbage[i][j][k] + sum[i - 1][j][k];
                            } else {
                                sum[i][j][k] = garbage[i][j][k] + sum[i - 1][j][k] + sum[i][j][k - 1] - sum[i - 1][j][k - 1];
                            }
                        } else if (k == 0) {
                            sum[i][j][k] = garbage[i][j][k] + sum[i - 1][j][k] + sum[i][j - 1][k] - sum[i - 1][j - 1][k];
                        } else {
                            sum[i][j][k] = garbage[i][j][k] + sum[i - 1][j][k] + sum[i][j - 1][k] + sum[i][j][k - 1]
                                    - sum[i - 1][j - 1][k] - sum[i - 1][j][k - 1] - sum[i][j - 1][k - 1] + sum[i - 1][j - 1][k - 1];
                            // WTF IDK EVEN KNOW HOW DID I COME UP WITH THIS BUT IT WORKS!
                        }
                    }
                }
            }

            long curSum = Long.MIN_VALUE;
            for (int i1 = 0; i1 < A; i1++) {
                for (int j1 = 0; j1 < B; j1++) {
                    for (int k1 = 0; k1 < C; k1++) {
                        for (int i2 = i1; i2 < A; i2++) {
                            for (int j2 = j1; j2 < B; j2++) {
                                for (int k2 = k1; k2 < C; k2++) {
                                    curSum = Math.max(curSum, sum(i1, j1, k1, i2, j2, k2));
                                }
                            }
                        }
                    }
                }
            }

//            for (int i = 0; i < A; i++) {
//                for (int j = 0; j < B; j++) {
//                    for (int k = 0; k < C; k++) {
//                        System.out.print(sum[i][j][k] + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println();
//            }

            System.out.println(curSum);
            if (tc != 0) System.out.println();
        }
    }

    private static long sum(int i1, int j1, int k1, int i2, int j2, int k2) {
        if (i1 == 0) {
            if (j1 == 0) {
                if (k1 == 0) {
                    return sum[i2][j2][k2];
                } else {
                    return sum[i2][j2][k2] - sum[i2][j2][k1 - 1];
                }
            } else if (k1 == 0) {
                return sum[i2][j2][k2] - sum[i2][j1 - 1][k2];
            } else {
                return sum[i2][j2][k2] - sum[i2][j2][k1 - 1] - sum[i2][j1 - 1][k2] + sum[i2][j1 - 1][k1 - 1];
            }
        } else if (j1 == 0) {
            if (k1 == 0) {
                return sum[i2][j2][k2] - sum[i1 - 1][j2][k2];
            } else {
                return sum[i2][j2][k2] - sum[i2][j2][k1 - 1] - sum[i1 - 1][j2][k2] + sum[i1 - 1][j2][k1 - 1];
            }
        } else if (k1 == 0) {
            return sum[i2][j2][k2] - sum[i2][j1 - 1][k2] - sum[i1 - 1][j2][k2] + sum[i1 - 1][j1 - 1][k2];
        } else {
            return sum[i2][j2][k2] - sum[i2][j2][k1 - 1] - sum[i2][j1 - 1][k2] - sum[i1 - 1][j2][k2]
                    + sum[i1 - 1][j1 - 1][k2] + sum[i2][j1 - 1][k1 - 1] + sum[i1 - 1][j2][k1 - 1] - sum[i1 - 1][j1 - 1][k1 - 1];
        }
    }
}
