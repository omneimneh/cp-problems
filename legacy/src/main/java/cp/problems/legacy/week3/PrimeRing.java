package cp.problems.legacy.week3;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class PrimeRing {

    private static final int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
    private static final int[] result = new int[16];
    private static int size;
    private static final boolean[] used = new boolean[17];
    private static PrintWriter out;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        out = new PrintWriter(System.out);
        int tc = 1;
        result[0] = 1;
        used[1] = true;
        while (s.hasNextInt()) {
            size = s.nextInt();

            if (tc != 1)
                out.println();
            out.printf("Case %d:\n", tc++);
            rec(1);
        }
        out.flush();
    }

    private static void rec(int pos) {
        if (pos == size - 1) {
            for (int prime : primes) {
                int chosen = prime - result[pos - 1];
                if (chosen <= size && chosen > 0 && !used[chosen]
                        && Arrays.binarySearch(primes, chosen + result[0]) >= 0) {
                    for (int j = 0; j < size - 1; j++) {
                        out.printf("%d ", result[j]);
                    }
                    out.println(chosen);
                }
            }
            return;
        }
        //System.out.println(Arrays.toString(result));
        for (int prime : primes) {
            result[pos] = prime - result[pos - 1];
            if (result[pos] <= size && result[pos] > 0 && !used[result[pos]]) {
                used[result[pos]] = true;
                rec(pos + 1);
                used[result[pos]] = false;
            }
        }
    }
}
