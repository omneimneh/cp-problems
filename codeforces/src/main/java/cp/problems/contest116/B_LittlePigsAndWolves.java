package cp.problems.contest116;

import java.util.Scanner;

// PROBLEM: https://codeforces.com/contest/116/problem/B
public class B_LittlePigsAndWolves {

    public enum Cell {Pig, Wolf, None}

    public static final Cell[][] cells = new Cell[10][10];
    public static final boolean[][] hasAte = new boolean[10][10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == 'P') {
                    cells[i][j] = Cell.Pig;
                } else if (line.charAt(j) == 'W') {
                    cells[i][j] = Cell.Wolf;
                } else if (line.charAt(j) == '.') {
                    cells[i][j] = Cell.None;
                } else {
                    throw new RuntimeException();
                }
                hasAte[i][j] = false;
            }
        }

        int eaten = 0;
        for (; ; ) {
            boolean action = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (cells[i][j] == Cell.Pig && !hasAte[i][j]) {
                        int wolfI = -1, wolfJ = -1;
                        if (i > 0 && cells[i - 1][j] == Cell.Wolf && !hasAte[i - 1][j]) {
                            wolfI = i - 1;
                            wolfJ = j;
                        } else if (j > 0 && cells[i][j - 1] == Cell.Wolf && !hasAte[i][j - 1]) {
                            wolfI = i;
                            wolfJ = j - 1;
                        } else if (j < m - 1 && cells[i][j + 1] == Cell.Wolf && !hasAte[i][j + 1]) {
                            wolfI = i;
                            wolfJ = j + 1;
                        } else if (i < n - 1 && cells[i + 1][j] == Cell.Wolf && !hasAte[i + 1][j]) {
                            wolfI = i + 1;
                            wolfJ = j;
                        }
                        if (wolfI != -1) {
                            hasAte[wolfI][wolfJ] = hasAte[i][j] = true;
                            eaten++;
                            action = true;
                        }
                    }
                }
            }
            if (!action) {
                break;
            }
        }
        System.out.println(eaten);
    }
}
