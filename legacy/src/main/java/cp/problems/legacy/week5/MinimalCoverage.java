package cp.problems.legacy.week5;

import java.util.Scanner;

class Interval {
    Interval(int low, int hi) {
        this.low = low;
        this.hi = hi;
    }

    int low, hi;

    Interval copy() {
        return new Interval(low, hi);
    }
}

public class MinimalCoverage {

    private static final Interval[] intervals = new Interval[100000];
    private static final Interval[] results = new Interval[100000];
    private static int M, count, resultCount;
    private static boolean found;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            M = s.nextInt();
            count = 0;
            while (true) {
                int l = s.nextInt();
                int h = s.nextInt();
                if (l == 0 && h == 0) {
                    break;
                }
                intervals[count++] = new Interval(l, h);
            }
            resultCount = 0;
            found = true;
            findMaxFrom(0);

            if (found) {
                System.out.println(resultCount);
                for (int i = 0; i < resultCount; i++) {
                    System.out.println(results[i].low + " " + results[i].hi);
                }
            } else {
                System.out.println(0);
            }
            if (tc != 0)
                System.out.println();
        }
    }

    private static void findMaxFrom(int from) {
        int max = 0;
        int maxIndex = -1;
        boolean done = false;
        for (int i = 0; i < count; i++) {

            if (intervals[i] == null) continue;

            int cover = -1;
            if (intervals[i].hi <= from) {
                intervals[i] = null;
                continue;
            } else if (intervals[i].low <= from && intervals[i].hi > from) {
                cover = intervals[i].hi - from;
            }

            if (max < cover) {
                max = cover;
                if (maxIndex != -1)
                    intervals[maxIndex] = null;
                maxIndex = i;
                if (from + max >= M) {
                    done = true;
                    break;
                }
            }
        }
        if (maxIndex == -1) {
            found = false;
            return;
        }
        results[resultCount++] = intervals[maxIndex].copy();
        intervals[maxIndex] = null;
        if (!done) {
            findMaxFrom(results[resultCount - 1].hi);
        }
    }
}
