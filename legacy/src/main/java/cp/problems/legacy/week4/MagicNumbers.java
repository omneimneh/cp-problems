package cp.problems.legacy.week4;

import java.util.Arrays;
import java.util.Scanner;

public class MagicNumbers {

    private static final boolean[] digits = new boolean[10];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            long n = s.nextLong();
            long current = n;
            int d = 1;
            while (!stop(current)) {
                if (notRepeated(current) && notRepeated(d)) {
                    System.out.printf("%d / %d = %d\n", current, d, n);
                }
                d++;
                current += n;
            }
            if (tc != 0)
                System.out.println();
        }
    }

    private static boolean notRepeated(long k) {
        Arrays.fill(digits, false);
        while (k != 0) {
            if (digits[(int) (k % 10)]) {
                return false;
            }
            digits[(int) (k % 10)] = true;
            k /= 10;
        }
        return true;
    }

    private static boolean stop(long k) {
        return k > 9999999999L;
    }

}
