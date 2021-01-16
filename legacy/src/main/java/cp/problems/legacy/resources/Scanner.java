package cp.problems.legacy.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Scanner {
    private final BufferedReader reader;
    private StringTokenizer st;

    public Scanner(InputStream stream) {
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
