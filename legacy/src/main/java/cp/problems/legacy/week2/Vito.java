package cp.problems.legacy.week2;

import java.util.Arrays;
import java.util.Scanner;

public class Vito {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            int r = s.nextInt();
            int[] d = new int[r];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < r; i++) {
                d[i] = s.nextInt();
                if (d[i] > max) {
                    max = d[i];
                }
                if (d[i] < min) {
                    min = d[i];
                }
            }
            Arrays.sort(d);
            int j = d[r / 2];
            int res = 0;
            for (int i = 0; i < r; i++) {
                res += Math.abs(j - d[i]);
            }
            System.out.println(res);
        }

    }
}
