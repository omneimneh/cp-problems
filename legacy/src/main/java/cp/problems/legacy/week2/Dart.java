package cp.problems.legacy.week2;

import java.util.Scanner;

public class Dart {

    private static final int[] poss = new int[61];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int x;

        poss[0] = 1;
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 3; j++) {
                poss[i * j]++;
            }
        }
        poss[50]++;

        while ((x = s.nextInt()) > 0) {
            int cb = cb(x);
            if (cb == 0) {
                System.out.printf("THE SCORE OF %d CANNOT BE MADE WITH THREE DARTS.\n", x);
            } else {
                System.out.printf("NUMBER OF COMBINATIONS THAT SCORES %d IS %d.\n", x, cb);
                System.out.printf("NUMBER OF PERMUTATIONS THAT SCORES %d IS %d.\n", x, per(x));
            }

            System.out.println("**********************************************************************");
        }

        System.out.println("END OF OUTPUT");
    }

    private static int per(int x) {
        if (x > 180) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < poss.length; i++) {
            if (poss[i] == 0) {
                continue;
            }
            for (int j = 0; j < poss.length; j++) {
                if (poss[j] == 0 || x - i - j >= poss.length || i + j > x || x - i - j < 0) continue;
                if (poss[x - i - j] != 0)
                    count++;//= poss[x - i - j] * poss[i] * poss[j];
            }
        }
        return count;
    }

    private static int cb(int x) {
        if (x > 180) {
            return 0;
        }
        int count = 0;
        boolean[][][] mem = new boolean[poss.length][poss.length][poss.length];
        for (int i = 0; i < poss.length; i++) {
            if (poss[i] == 0) {
                continue;
            }
            for (int j = 0; j < poss.length; j++) {
                if (poss[j] == 0 || x - i - j >= poss.length || i + j > x || x - i - j < 0) continue;
                if (mem[j][i][x - i - j] || mem[i][x - i - j][j] || mem[j][x - i - j][i] || mem[x - i - j][i][j] || mem[x - i - j][j][i])
                    continue;
                if (poss[x - i - j] != 0) {
                    mem[i][j][x - i - j] = true;
                    count++;//= poss[x - i - j] * poss[i] * poss[j];
                }
            }
        }
        return count;
    }
}
