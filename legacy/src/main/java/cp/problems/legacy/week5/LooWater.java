package cp.problems.legacy.week5;

import java.util.Arrays;
import java.util.Scanner;

public class LooWater {
    private static int n, m;
    private static final int[] heads = new int[20001];
    private static final int[] knights = new int[20001];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            n = s.nextInt(); // heads
            m = s.nextInt(); // knights

            if (n == 0 && m == 0) break;

            for (int i = 0; i < n; i++) {
                heads[i] = s.nextInt();
            }

            for (int i = 0; i < m; i++) {
                knights[i] = s.nextInt();
            }


            Arrays.sort(heads, 0, n);
            Arrays.sort(knights, 0, m);

            if (n > m) {
                System.out.println("Loowater is doomed!");
                break;
            }

            int h = 0;
            int k = 0;

            int coins = 0;
            boolean flag = true;
            while (h != n) {
                if (k == m) {
                    flag = false;
                    break;
                }

                if (knights[k] >= heads[h]) {
                    coins += knights[k];
                    k++;
                    h++;
                } else {
                    k++;
                }
            }

            if (flag) {
                System.out.println(coins);
            } else {
                System.out.println("Loowater is doomed!");
            }

        }
    }
}
