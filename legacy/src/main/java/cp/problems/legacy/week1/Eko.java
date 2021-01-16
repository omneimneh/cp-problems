package cp.problems.legacy.week1;

import java.io.IOException;

public class Eko {
    private static long[] trees;
    private static long M;

    public static void main(String[] args) throws Exception {
        long N = readLong();
        M = readLong();
        trees = new long[(int) N];
        for (int i = 0; i < N; i++) {
            trees[i] = readLong();
        }
        System.out.println(bs());
    }

    private static long bs() {
        long lower = 0L;
        long higher = (long) 1e9;
        while (higher - lower > 1) {
            long mid = (higher + lower) / 2L;
            if (ok(mid)) {
                lower = mid;
            } else {
                higher = mid;
            }
        }
        return ok(higher) ? higher : lower;
    }

    private static boolean ok(long height) {
        long sum = 0L;
        for (long tree : trees) {
            if (tree > height) {
                sum += tree - height;
            }
        }
        return sum >= M;
    }

    @SuppressWarnings("DuplicatedCode")
    private static long readLong() throws IOException {
        long ret = 0L;
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
