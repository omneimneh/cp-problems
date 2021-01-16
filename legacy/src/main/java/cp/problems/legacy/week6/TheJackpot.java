package cp.problems.legacy.week6;

import java.util.Scanner;

public class TheJackpot {
    private static final int[] arr = new int[10001];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        while ((n = s.nextInt()) != 0) {
            boolean losing = true;
            for (int i = 0; i < n; i++) {
                arr[i] = s.nextInt();
                if (arr[i] > 0) {
                    losing = false;
                }
            }

            if (losing) {
                System.out.println("Losing streak.");
            } else {
                int maxSum = 0;
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += arr[i];
                    if (sum < 0) {
                        sum = 0;
                    }

                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }
                System.out.printf("The maximum winning streak is %d.\n", maxSum);
            }
        }
    }
}
