package cp.problems.legacy.week6;

import java.util.Scanner;

public class FlightPlanner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();

        int[][] wind = new int[10][1000];
        int[][] min = new int[10][1001];
        while (tc-- > 0) {

            int x = s.nextInt() / 100;

            for (int h = 9; h >= 0; h--) {
                for (int r = 0; r < x; r++) {
                    wind[h][r] = s.nextInt();
                    min[h][r] = Integer.MAX_VALUE >> 1;
                }
            }


            for (int i = 0; i < 10; i++) {
                min[i][x] = Integer.MAX_VALUE >> 1;
            }

            min[0][x] = 0;

            for (int r = x - 1; r >= 0; r--) {
                for (int h = 0; h < 10; h++) {
                    int m = 30 - wind[h][r] + min[h][r + 1];
                    if (h < 9) m = Math.min(m, 60 - wind[h][r] + min[h + 1][r + 1]);
                    if (h > 0) m = Math.min(m, 20 - wind[h][r] + min[h - 1][r + 1]);
                    //System.out.println(m);
                    min[h][r] = m;
                }
            }

//            for (int i = 9; i >= 0; i--) {
//                for (int j = 0; j < x; j++) {
//                    System.out.print(min[i][j] + " ");
//                }
//                System.out.println();
//            }

            System.out.println(min[0][0]);
            //if (tc != 0)
            System.out.println();

        }
    }
}
