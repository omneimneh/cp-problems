package cp.problems.legacy.train.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class KingsPath {

    private static class Block {
        int row, c1, c2;

        Block(int row, int c1, int c2) {
            this.row = row;
            this.c1 = c1;
            this.c2 = c2;
        }
    }

    private static class Point {
        int x, y;
        int deep;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x + y * 100000;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
    }

    private static Point start, end;
    private static final HashMap<Point, Boolean> allowed = new HashMap<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        start = new Point(s.nextInt() - 1, s.nextInt() - 1);
        end = new Point(s.nextInt() - 1, s.nextInt() - 1);
        int blocksCount = s.nextInt();
        for (int i = 0; i < blocksCount; i++) {
            Block b = new Block(s.nextInt() - 1, s.nextInt() - 1, s.nextInt() - 1);
            for (int j = b.c1; j <= b.c2; j++) {
                allowed.put(new Point(b.row, j), true);
            }
        }

        if (Math.max(Math.abs(start.x - end.x), Math.abs(start.y - end.y)) > 100001) {
            System.out.println(-1);
        } else {
            System.out.println(bfs());
        }
    }

    private static boolean allowed(Point p) {
        if (p.x < 0 || p.y < 0)
            return false;
        return allowed.containsKey(p) && allowed.get(p);
    }

    private static int bfs() {

        int[] moveX = new int[]{0, 0, 1, 1, 1, -1, -1, -1};
        int[] moveY = new int[]{1, -1, 1, -1, 0, -1, 1, 0};
        Queue<Point> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.equals(end))
                return p.deep;

            for (int i = 0; i < moveY.length; i++) {
                Point newP = new Point(p.x + moveX[i], p.y + moveY[i]);
                newP.deep = p.deep + 1;
                if (allowed(newP)) {
                    allowed.put(newP, false);
                    q.add(newP);
                }
            }
        }

        return -1;
    }
}
