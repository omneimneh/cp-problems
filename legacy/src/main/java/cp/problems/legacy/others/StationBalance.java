package cp.problems.legacy.others;

import java.util.Arrays;
import java.util.Scanner;

public class StationBalance {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int set = 1;
        while (s.hasNextInt()) {

            int C = s.nextInt();
            int S = s.nextInt();
            int[] arr = new int[2 * C];
            double avg = 0.0;
            for (int i = 0; i < S; i++) {
                arr[i] = s.nextInt();
                avg += arr[i];
            }
            avg /= C;

            Arrays.sort(arr);
            System.out.printf("Set #%d\n", set++);

            double imbalance = 0;
            for (int i = 0; i < C; i++) {
                System.out.printf(" %d:", i);
                imbalance += Math.abs(arr[i] + arr[arr.length - 1 - i] - avg);
                if (arr[i] != 0) {
                    System.out.printf(" %d %d\n", arr[i], arr[arr.length - 1 - i]);
                } else if (arr[arr.length - 1 - i] != 0) {
                    System.out.printf(" %d\n", arr[arr.length - 1 - i]);
                } else {
                    System.out.println();
                }
            }
            System.out.printf("IMBALANCE = %.5f\n\n", imbalance);

        }
    }
}
