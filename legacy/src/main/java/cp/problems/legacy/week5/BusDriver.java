package cp.problems.legacy.week5;

import java.util.Arrays;
import java.util.Scanner;

public class BusDriver {

    private static final int[] morning = new int[101];
    private static final int[] evening = new int[101];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            int n = s.nextInt();
            int d = s.nextInt();
            int r = s.nextInt();
            if (n == 0 && d == 0 && r == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                morning[i] = s.nextInt();
            }

            for (int i = 0; i < n; i++) {
                evening[i] = s.nextInt();
            }

            Arrays.sort(morning, 0, n);
            Arrays.sort(evening, 0, n);

            long overpay = 0;
            for (int m = 0; m < n; m++) {
                overpay += r * Math.max(0, morning[m] + evening[n - 1 - m] - d);
            }

            System.out.println(overpay);

        }
    }
}
