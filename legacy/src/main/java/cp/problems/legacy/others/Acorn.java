package cp.problems.legacy.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Acorn {

    private static int t, h, f;
    private static final int[][] acorn = new int[2001][2001];
    private static final Integer[] mem = new Integer[2001];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int c = s.nextInt();
        while (c-- > 0) {

            for (int i = 0; i < t; i++) {
                Arrays.fill(acorn[i], 0);
                mem[i] = null;
            }

            t = s.nextInt();
            h = s.nextInt();
            f = s.nextInt();

            for (int i = 0; i < t; i++) {
                int num = s.nextInt();

                for (int j = 0; j < num; j++) {
                    acorn[i][s.nextInt()]++;
                }
            }


            System.out.println(dp());

        }
        s.nextInt();
    }

    private static int dp() {
        mem[h] = 0;
        for (int i = 0; i < t; i++) {
            mem[h] = Math.max(mem[h], acorn[i][h]);
        }

        for (int height = h - 1; height >= 0; height--) {
            mem[height] = 0;
            for (int i = 0; i < t; i++) {
                acorn[i][height] += Math.max(acorn[i][height + 1],
                        height + f <= h ? mem[height + f] : -1);
                mem[height] = Math.max(mem[height], acorn[i][height]);
            }
        }
        return mem[0];
    }

    @SuppressWarnings({"unused", "DuplicatedCode"})
    private static class Scanner {
        private final BufferedReader reader;
        private StringTokenizer st;

        Scanner(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public static int readInt() throws IOException {
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

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = reader.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (Exception e) {
                    throw (new RuntimeException());
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
