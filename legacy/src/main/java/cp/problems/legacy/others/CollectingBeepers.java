package cp.problems.legacy.others;

import java.util.Scanner;

public class CollectingBeepers {

    private static class Point {
        int x, y;
        int index;

        Point(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    private static final Point[] arr = new Point[10];
    private static int min;
    private static Point start;
    private static final int[][] eachPairDist = new int[10][10];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tc = s.nextInt();
        while (tc-- > 0) {
            s.nextInt();
            s.nextInt();
            start = new Point(s.nextInt(), s.nextInt(), -1);
            int b = s.nextInt();
            for (int i = 0; i < b; i++) {
                arr[i] = new Point(s.nextInt(), s.nextInt(), i);
            }

            for (int i = 0; i < b; i++) {
                for (int j = i; j < b; j++) {
                    eachPairDist[i][j] = eachPairDist[j][i] = Math.abs(arr[j].x - arr[i].x) + Math.abs(arr[j].y - arr[i].y);
                }
            }

            min = Integer.MAX_VALUE;
            heapPermutation(arr, b, b);
            System.out.printf("The shortest path has length %d\n", min);

        }
    }

    private static void heapPermutation(Point[] array, int n, int size) {
        if (n == 1) {
            int dist = Math.abs(array[0].x - start.x) + Math.abs(array[0].y - start.y);
            for (int i = 0; i < size - 1; i++) {
                dist += eachPairDist[array[i].index][array[i + 1].index];
            }
            dist += Math.abs(array[size - 1].x - start.x) + Math.abs(array[size - 1].y - start.y);
            min = Math.min(min, dist);
            return;
        }
        for (int i = 0; i < n; i++) {
            heapPermutation(array, n - 1, size);
            if (n % 2 != size % 2) {
                Point tmp = array[i];
                array[i] = array[n - 1];
                array[n - 1] = tmp;
            } else {
                Point tmp = array[0];
                array[0] = array[n - 1];
                array[n - 1] = tmp;
            }
        }
    }
}
