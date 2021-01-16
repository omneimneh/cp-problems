package cp.problems.legacy.others;

import java.util.Scanner;

public class The3nPlus1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (s.hasNextInt()) {
            int first = s.nextInt();
            int second = s.nextInt();

            int upper = Math.max(first, second);
            int lower = Math.min(first, second);

            int max = 1;

            for (int i = lower; i <= upper; i++) {
                max = Math.max(max, cycleLength(i));
            }

            System.out.println(first + " " + second + " " + max);
        }
    }

    private static int cycleLength(int i) {
        int count = 1;
        while (i != 1) {
            if(i % 2 == 0) {
                i /= 2;
            } else {
                i = 3 * i + 1;
            }
            count++;
        }
        return count;
    }
}
