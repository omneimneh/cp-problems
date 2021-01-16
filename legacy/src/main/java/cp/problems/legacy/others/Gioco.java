package cp.problems.legacy.others;

import java.util.Scanner;

public class Gioco {

    private static int N;
    // private static final boolean WHITE = true;
    // private static final boolean BLACK = false;
    private static final boolean[][] matrix = new boolean[200][200];
    private static final boolean[][] visited = new boolean[200][200];
    private static boolean win;

    private static final int[] moveI = new int[]{-1, -1, 0, 0, 1, 1};
    private static final int[] moveJ = new int[]{-1, 0, -1, 1, 0, 1};


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = 1;
        while ((N = s.nextInt()) != 0) {
            for (int i = 0; i < N; i++) {
                String ln = s.next();
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = ln.charAt(j) == 'w';
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited[i][j] = false;
                }
            }
            win = false;
            int i = 0;
            while (!win && i < N) {
                if (matrix[i][0]) {
                    logic(i, 0);
                }
                i++;
            }
            System.out.printf("%d %s\n", tc++, win ? "W" : "B");
        }
    }

    private static void logic(int i, int j) {
        if (win) {
            return;
        }
        visited[i][j] = true;
        for (int k = 0; k < moveI.length; k++) {
            int newI = moveI[k] + i;
            int newJ = moveJ[k] + j;
            if (newI < N && newI >= 0 && newJ < N && newJ >= 0 && !visited[newI][newJ] && matrix[newI][newJ]) {
                if (newJ == N - 1) {
                    win = true;
                    break;
                } else {
                    logic(newI, newJ);
                }
            }
        }
    }
}
