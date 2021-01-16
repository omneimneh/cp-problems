package cp.problems.legacy.week1;

import java.util.Arrays;
import java.util.Scanner;

public class Aggressive {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
        for (int i = 0; i < k; i++) {
            int n = s.nextInt();
            int c = s.nextInt();
            int[] x = new int[n];
            for (int j = 0; j < n; j++) {
                x[j] = s.nextInt();
            }
            Arrays.sort(x);
            System.out.println(search(x, c));
        }
    }

    private static int search(int[] x, int c) {
        int hi = (int)1e9 + 7, low = 0, mid;
        while(hi > low + 1) {
            mid = (hi + low) / 2;
            if(can(c, mid, x)) {
                low = mid;
            } else {
                hi = mid;
            }
        }
        if(can(c, hi, x)) {
            return hi;
        } else {
            return low;
        }
    }

    private static boolean can(int c, int step, int[] x) {
        int counter = 1;
        int lastLocation = x[0];
        for (int i = 1; i < x.length; i++) {
            if(x[i] - lastLocation >= step) {
                counter++;
                lastLocation = x[i];
            }
        }
        return counter >= c;
    }
}
