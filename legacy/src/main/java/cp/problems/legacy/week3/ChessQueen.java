package cp.problems.legacy.week3;

import java.util.Scanner;

public class ChessQueen {

    private static final boolean[] cols = new boolean[8];
    private static final boolean[] dg1 = new boolean[16];
    private static final boolean[] dg2 = new boolean[16];

    private static final int[] slnCol = new int[8];

    private static int row, slnCount = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tc = s.nextInt();

        while (tc-- > 0) {

            for (int i = 0; i < cols.length; i++) {
                cols[i] = false;
                dg1[i] = false;
                dg2[i] = false;
            }
            for (int i = 8; i < dg1.length; i++) {
                dg1[i] = false;
                dg2[i] = false;
            }
            slnCount = 0;

            int col = s.nextInt() - 1;
            row = s.nextInt() - 1;

            slnCol[row] = col;

            cols[col] = true;
            dg1[row + col] = true;
            dg2[row - col + 8] = true;

            System.out.println("SOLN       COLUMN");
            System.out.println(" #      1 2 3 4 5 6 7 8\n");

            sln(0);
            if (tc != 0)
                System.out.println();
        }
    }

    private static void sln(int curRow) {
        if (curRow == 8) {
            System.out.printf("%2d      ", ++slnCount);
            for (int i = 0; i < 7; i++) {
                System.out.printf("%d ", slnCol[i] + 1);
            }
            System.out.println(slnCol[7] + 1);
            return;
        }

        if (row == curRow) {
            sln(curRow + 1);
        } else {
            for (int i = 0; i < 8; i++) {
                if (!cols[i] && !dg1[curRow + i] && !dg2[curRow - i + 8]) {
                    slnCol[curRow] = i;

                    cols[i] = true;
                    dg1[curRow + i] = true;
                    dg2[curRow - i + 8] = true;
                    sln(curRow + 1);
                    cols[i] = false;
                    dg1[i + curRow] = false;
                    dg2[curRow - i + 8] = false;
                }
            }
        }
    }
}
