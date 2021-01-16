package cp.problems.legacy.v2;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ByElevatorOrStairs {

    private static final int[] a = new int[(int) 2e5];
    private static final int[] b = new int[(int) 2e5];
    private static int n, c;

    private static final int[] dist = new int[(int) 2e5];
    private static final int[] elev = new int[(int) 2e5];

    private static final PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        n = readInt();
        c = readInt();

        for (int i = 0; i < n - 1; i++) {
            a[i] = readInt();
        }

        elev[0] = 0;
        for (int i = 0; i < n - 1; i++) {
            b[i] = readInt();
            elev[i + 1] = elev[i] + b[i];
        }

        dijkstra();

        for (int i = 0; i < n; i++) {
            out.print(dist[i] + " ");
        }
        out.println();
        out.flush();
    }

    private static void dijkstra() {
        PriorityQueue<Point> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.y));
        q.add(new Point(0, 0));
        for (int i = 1; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[0] = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.x > 0) {
                if (dist[cur.x - 1] > cur.y + a[cur.x - 1])
                    q.add(new Point(cur.x - 1, dist[cur.x - 1] = cur.y + a[cur.x - 1]));
            }
            if (cur.x < n - 1) {
                if (dist[cur.x + 1] > cur.y + a[cur.x])
                    q.add(new Point(cur.x + 1, dist[cur.x + 1] = cur.y + a[cur.x]));
            }
            for (int j = 0; j < n; j++) {
                if (cur.x != j) {
                    if (dist[j] > c + cur.y + elev(cur.x, j))
                        q.add(new Point(j, dist[j] = c + cur.y + elev(cur.x, j)));
                }
            }
        }
    }

    private static int elev(int i, int j) {
        return elev[max(i, j)] - elev[min(i, j)];
    }

    private static int readInt() throws IOException {
        int ret = 0;
        boolean dig = false;
        for (int c; (c = System.in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (dig)
                break;
        }
        return ret;
    }
}
