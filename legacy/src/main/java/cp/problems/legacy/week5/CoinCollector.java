package cp.problems.legacy.week5;

import java.util.Scanner;

public class CoinCollector {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {

            int len = s.nextInt();

            int[] c = new int[len];
            for (int i = 0; i < len; i++) {
                c[i] = s.nextInt();
            }

            int sum = 0;
            int res = 1;

            for (int i = 0; i < len - 1; i++) {
                if (sum + c[i] < c[i + 1]) {
                    res++;
                    sum += c[i];
                }
            }
            System.out.println(res);
        }
    }
}
