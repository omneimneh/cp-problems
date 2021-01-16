package cp.problems.legacy.others;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Loc {
    int x, y;
    int route;

    Loc(int x, int y, int route) {
        this.x = x;
        this.y = y;
        this.route = route;
    }
}


public class KnightMoves {

    private static final boolean[][] visited = new boolean[8][8];
    private static final int[] movesX = new int[]{2, 2, 1, 1, -2, -2, -1, -1};
    private static final int[] movesY = new int[]{1, -1, 2, -2, 1, -1, 2, -2};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            String s1 = s.next();
            String s2 = s.next();

            int col1 = s1.charAt(0) - 'a';
            int row1 = s1.charAt(1) - '1';
            int col2 = s2.charAt(0) - 'a';
            int row2 = s2.charAt(1) - '1';

            Queue<Loc> q = new LinkedList<>();
            q.add(new Loc(row1, col1, 0));

            for (boolean[] booleans : visited) {
                Arrays.fill(booleans, false);
            }

            while (!q.isEmpty()) {
                Loc current = q.poll();
                if (current.x == row2 && current.y == col2) {
                    System.out.printf("To get from %s to %s takes %d knight moves.\n", s1, s2, current.route);
                    break;
                }

                for (int i = 0; i < movesX.length; i++) {
                    if (movesX[i] + current.x > -1 && movesX[i] + current.x < 8
                            && movesY[i] + current.y > -1 && movesY[i] + current.y < 8) {

                        if (!visited[movesX[i] + current.x][movesY[i] + current.y]) {
                            visited[movesX[i] + current.x][movesY[i] + current.y] = true;
                            q.add(new Loc(movesX[i] + current.x, movesY[i] + current.y, current.route + 1));
                        }
                    }
                }

            }
        }
    }
}
