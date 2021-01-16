package cp.problems.legacy.week1;

import java.io.IOException;

public class Grapevine {
    public static void main(String[] args) throws Exception {
        while (true) {
            int N = readInt();
            int M = readInt();
            if (N == 0 && M == 0) {
                break;
            }

            int[][] matrix = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    matrix[i][j] = readInt();
                }
            }

            int Q = readInt();

            while (Q-- > 0) {

                // start end indexes
                int[][] horizontal = new int[N][2]; // 0 index for start and 1 index for end

                int min = readInt();
                int max = readInt();

                for (int i = 0; i < N; i++) {

//                    if (matrix[i][0] > max || matrix[i][M - 1] < min) {
//                        horizontal[i][0] = M + 1;
//                        continue;
//                    }

                    // find start index of variables inside range
                    int low = -1;
                    int hi = M;
                    boolean assigned = false;
                    while (hi - low > 1) {
                        int mid = (low + hi) / 2;
                        if (matrix[i][mid] >= min) {
                            horizontal[i][0] = mid;
                            hi = mid;
                            assigned = true;
                        } else {
                            low = mid;
                        }
                    }

                    if (!assigned) {
                        horizontal[i][0] = M;
                    }

                    // find end index of variables inside range
                    low = -1;
                    hi = M;
                    assigned = false;
                    while (hi - low > 1) {
                        int mid = (low + hi) / 2;
                        if (matrix[i][mid] <= max) {
                            horizontal[i][1] = mid;
                            low = mid;
                            assigned = true;
                        } else {
                            hi = mid;
                        }
                    }
                    if(!assigned) {
                        horizontal[i][1] = -1;
                    }
                }
                int maxSide = 0;

//                System.out.println(Arrays.deepToString(horizontal));
//                for (int[] aMatrix : matrix) {
//                    for (int anAMatrix : aMatrix) {
//                        System.out.print(anAMatrix <= max && anAMatrix >= min ? "+ " : "- ");
//                    }
//                    System.out.println();
//                }

                for (int i = 0; i < N; i++) {
                    int start = horizontal[i][0];
                    int end = horizontal[i][1];
                    int distance = end - start + 1;
                    if (distance < 1) {
                        continue;
                    }
                    label:
                    for (int j = maxSide + 1; j <= distance && i + j - 1 < N; j++) {
                        for (int k = 1; k < j; k++) {
                            if (horizontal[i + k][0] > start || horizontal[i + k][1] < start + j - 1) {
                                break label;
                            }
                        }
                        maxSide = j;
                    }
                }
                System.out.println(maxSide);


            }
            System.out.println("-");

        }
    }

    @SuppressWarnings("DuplicatedCode")
    private static int readInt() throws IOException {
        int ret = 0;
        boolean dig = false;
        boolean neg = false;
        for (int c; (c = System.in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (c == '-')
                neg = true;
            else if (dig)
                break;
        }
        return neg ? -ret : ret;
    }
}
