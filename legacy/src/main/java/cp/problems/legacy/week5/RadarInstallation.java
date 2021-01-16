package cp.problems.legacy.week5;

import java.util.Arrays;
import java.util.Scanner;

class Coordinate implements Comparable<Coordinate> {
    int x, y;
    static int d;

    @Override
    public int compareTo(Coordinate o) {
        if (x == o.x) {
            return -Integer.compare(y, o.y);
        }
        if (Math.abs(x - o.x) >= d) {
            return Integer.compare(x, o.x);
        }

        if (y > d) return 0;
        if (o.y > d) return 0;
        if (y < 0 || o.y < 0) return 0;

        if (x < o.x) {
            double x1 = x + Math.sqrt(d * d - y * y);
            double d1 = Math.sqrt((o.x - x1) * (o.x - x1) + (o.y) * (o.y));
            if (d1 <= d) {
                return -1;
            } else {
                return 1;
            }
        } else {
            double x1 = o.x + Math.sqrt(d * d - o.y * o.y);
            double d1 = Math.sqrt((x - x1) * (x - x1) + (y) * (y));
            if (d1 <= d) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}

public class RadarInstallation {

    public static void main(String[] args) {
        try {

            Scanner s = new Scanner(System.in);
            int tc = 1;
            Coordinate[] coordinates = new Coordinate[101];
            for (int i = 0; i < coordinates.length; i++) {
                coordinates[i] = new Coordinate();
            }
            while (true) {
                int n = s.nextInt();
                int d = s.nextInt();
                Coordinate.d = d;
                if (n == 0 && d == 0) break;

                for (int i = 0; i < n; i++) {

                    coordinates[i].x = s.nextInt();
                    coordinates[i].y = s.nextInt();

                }

                if (n > 0 && n <= coordinates.length)
                    Arrays.sort(coordinates, 0, n);


                double x1 = Double.NaN;
                int y1 = 0;
                int radarCount = 0;

                for (int i = 0; i < n; i++) {

                    int x0 = coordinates[i].x;
                    int y0 = coordinates[i].y;

                    if (d < y0 || y0 < 0) {
                        radarCount = -1;
                        break;
                    }

                    if (!Double.isNaN(x1)) {
                        double d1 = Math.sqrt((x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1));
                        if (d1 <= d) {
                            continue;
                        }
                    }

                    x1 = x0 + Math.sqrt(d * d - y0 * y0);
                    radarCount++;

                }
                System.out.printf("Case %d: %d\n", tc++, radarCount);
            }
        } catch (IndexOutOfBoundsException t) {
            System.out.println("HOLY SHIT");
        }
    }
}
