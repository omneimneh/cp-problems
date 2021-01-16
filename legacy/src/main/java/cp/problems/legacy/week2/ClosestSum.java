package cp.problems.legacy.week2;

import java.util.Scanner;

public class ClosestSum {

    private static final int[] arr = new int[1000];
    private static int n;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tc = 1;
        while ((n = s.nextInt()) != 0) {

            for (int i = 0; i < n; i++) {
                arr[i] = s.nextInt();
            }

            System.out.printf("Case %d:\n", tc++);

            int q = s.nextInt();
            for (int i = 0; i < q; i++) {
                int sum = s.nextInt();
                System.out.printf("Closest sum to %d is %d.\n", sum, closest(sum));

            }

        }
    }

    private static int closest(int sum) {
        int minDiff = Integer.MAX_VALUE;
        int foundSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentDiff = Math.abs(sum - (arr[i] + arr[j]));
                if (currentDiff < minDiff) {
                    minDiff = currentDiff;
                    foundSum = arr[i] + arr[j];
                }
            }
        }
        return foundSum;
    }

}