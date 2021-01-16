package cp.problems.legacy.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class SafeSide {

    private static class Point {
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    }

    private static int N, W;
    private static final boolean[][] ug = new boolean[1000][1000];
    private static final int[][] mem = new int[1000][1000];
    private static final int[][] pathLen = new int[1000][1000];
    private static final boolean[][] vis = new boolean[1000][1000];

    private static final int[] moveX = new int[]{-1, 0};
    private static final int[] moveY = new int[]{0, -1};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();

        while (tc-- > 0) {
            W = s.nextInt();
            N = s.nextInt();

            for (int i = 0; i < W; i++) {
                for (int j = 0; j < N; j++) {
                    ug[i][j] = false;
                    pathLen[i][j] = Integer.MAX_VALUE;
                }
            }

            s.nextLine();
            for (int i = 0; i < W; i++) {
                String[] line = s.nextLine().split(" ");
                for (int j = 1; j < line.length; j++) {
                    ug[i][Integer.parseInt(line[j]) - 1] = true;
                }
            }

            for (int i = 0; i < W; i++) {
                for (int j = 0; j < N; j++) {
                    vis[i][j] = false;
                    mem[i][j] = 0;
                }
            }

            mem[W - 1][N - 1] = 1;
            pathLen[W - 1][N - 1] = 0;
            Queue<Point> q = new LinkedList<>();
            q.add(new Point(W - 1, N - 1));
            while (!q.isEmpty()) {
                Point u = q.remove();
                //System.out.println("Current point " + u.x + " " + u.y);


                for (int i = 0; i < moveX.length; i++) {
                    Point v = new Point(u.x + moveX[i], u.y + moveY[i]);
                    if (valid(v)) {
                        mem[v.x][v.y] += pathLen[u.x][u.y] < pathLen[v.x][v.y] ? mem[u.x][u.y] : 0;
                        if (!vis[v.x][v.y]) {
                            pathLen[v.x][v.y] = pathLen[u.x][u.y] + 1;
                            q.add(v);
                        }

                        vis[v.x][v.y] = true;

                    }
                }

            }

//            for (int i = 0; i < W; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.printf("%1s %-8d", ug[i][j] ? "x" : "y", mem[i][j]);
//                }
//                System.out.println();
//            }

            System.out.println(mem[0][0]);
            if (tc != 0) System.out.println();

        }

    }

    private static boolean valid(Point p) {
        return p.x < W && p.x >= 0 && p.y < N && p.y >= 0
                && !ug[p.x][p.y];
    }
}
