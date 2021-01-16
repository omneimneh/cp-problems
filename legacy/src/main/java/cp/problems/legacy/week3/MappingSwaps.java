package cp.problems.legacy.week3;

import java.util.Scanner;

public class MappingSwaps {

    private static final int[] arr = new int[5];
    private static int len, k;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int c = 1;
        while ((len = s.nextInt()) != 0) {
            for (int i = 0; i < len; i++) {
                arr[i] = s.nextInt();
            }
            k = 0;
            rec();
            System.out.printf("There are %d swap maps for input data set %d.\n", numberOfSwaps() == 0 ? 0 : k, c++);
        }
    }

    private static void rec() {
        int count = 0;
        for (int i = 0; i < len - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
                rec();
                arr[i + 1] = arr[i];
                arr[i] = tmp;

                count++;
            }
        }
        if (count == 0) {
            k++;
        }
    }

    private static int numberOfSwaps() {
        int swaps = 0;

        for (int i = 0; i < len - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                // count
                swaps++;

            }
        }

        return swaps;

    }
}
