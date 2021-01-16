package cp.problems.legacy.week2;

import java.util.Scanner;

public class MaxProduct {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int c = 1;
        while (s.hasNextInt()) {
            int len = s.nextInt();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = s.nextInt();
            }
            long maxProduct = 0;
            for (int i = 0; i < len; i++) {
                long currentProduct = arr[i];
                if (currentProduct > maxProduct) {
                    maxProduct = currentProduct;
                }
                for (int j = i + 1; j < len; j++) {
                    currentProduct *= arr[j];
                    if (currentProduct > maxProduct) {
                        maxProduct = currentProduct;
                    }
                }
            }
            System.out.printf("Case #%d: The maximum product is %d.\n\n", c++, maxProduct);
        }
    }
}
