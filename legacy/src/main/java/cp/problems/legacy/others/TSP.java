package cp.problems.legacy.others;

import java.util.Scanner;

public class TSP {

    private static int m, n, st;
    private static final int[][] matrix = new int[11][101];
    private static final Integer[][] mem = new Integer[11][101];
    private static final int[][] path_sln = new int[11][101];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (s.hasNext()) {
            m = s.nextInt();
            n = s.nextInt();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = s.nextInt();
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    mem[i][j] = null;
                }
            }

            int sln = dp(-1, 0);

            int c = st;
            System.out.print(c + 1);
            for (int i = 0; i < n - 1; i++) {
                c = path_sln[c][i];
                System.out.printf(" %d", c + 1);
            }
            System.out.println();
            System.out.println(sln);
        }

    }

    private static int dp(int col, int row) {
        if (col == n) {
            return 0; // path finished
        }


        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        if (col == -1) {
            for (int i = 0; i < m; i++) {
                int d = dp(0, i);
                if (d < min) {
                    min = d;
                    minIndex = i;
                }
            }
            st = minIndex;
            return min;
        } else if (mem[row][col] != null) {
            return mem[row][col];
        } else {
            for (int i = -1; i <= 1; i++) {
                int path = matrix[row][col] + dp(col + 1, (row + i + m) % m);
                if (path < min) {
                    min = path;
                    path_sln[row][col] = (row + i + m) % m;
                } else if(path == min && (row + i + m) % m < path_sln[row][col]) {
                    path_sln[row][col] = (row + i + m) % m;
                }
            }
        }
        return mem[row][col] = min;
    }
}
