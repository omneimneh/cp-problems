package cp.problems.legacy.week6;

import java.util.Scanner;

public class SumOfDigits {

    private static final long[] mem = new long[100000];

    private static final long[] ssu = new long[]{0, 1, 3, 6, 10, 15, 21, 28, 36, 45};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            int st = s.nextInt();
            int en = s.nextInt();

            if (st == -1 && en == -1) break;

            System.out.println(d(en) - d(st - 1));
        }
    }

    private static long d(long u) {

        if (u < 10) {
            if (u < 0) return 0;
            return ssu[(int) u];
        }

        if (u < mem.length && mem[(int) u] != 0) return mem[(int) u];

        String str = u + "";
        long firstDigit = str.charAt(0) - '0';
        long modMinus1 = firstDigit * (long) Math.pow(10, str.length() - 1) - 1;
        long remain = u - modMinus1 - 1;

        //System.out.println(modMinus1 + " " + remain);
        if (u < mem.length)
            return mem[(int) u] = d(modMinus1) + firstDigit * (remain + 1) + d(remain);
        else return d(modMinus1) + firstDigit * (remain + 1) + d(remain);

    }

}
