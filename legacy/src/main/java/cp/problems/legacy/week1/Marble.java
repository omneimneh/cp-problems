package cp.problems.legacy.week1;

import java.io.IOException;
import java.util.Arrays;

public class Marble {
    private static int[] marbles;

    public static void main(String[] args) throws Exception {
        int c = 1;
        while (true) {
            int N = readInt();
            int Q = readInt();
            if (N == 0 && Q == 0) {
                break;
            }
            marbles = new int[N];
            for (int i = 0; i < N; i++) {
                marbles[i] = readInt();
            }

            Arrays.sort(marbles);

            System.out.println("CASE# " + c + ":");

            for (int i = 0; i < Q; i++) {
                int x = readInt();
                int res = bs(x);
                if (res > 0) {
                    System.out.printf("%s found at %s\n", x, res);
                } else {
                    System.out.printf("%s not found\n", x);
                }
            }
            c++;
        }
    }

    private static int bs(int x) {
        int higher = marbles.length;
        int lower = -1;
        int res = -1;
        while (higher - lower > 1) {
            int mid = (higher + lower) / 2;
            if (marbles[mid] == x) {
                res = mid;
                higher = mid;
            } else if (marbles[mid] > x) {
                higher = mid;
            } else {
                lower = mid;
            }
        }
        return res + 1;
    }

    private static int readInt() throws IOException {
        int ret = 0;
        boolean dig = false;
        boolean neg = false;
        for (int c; (c = System.in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (c == '-')
                neg = true;
            else if (dig)
                break;
        }
        return neg ? -ret : ret;
    }
}
