package cp.problems.legacy.week7;

import java.io.*;
import java.util.StringTokenizer;

public class Area {

    private static class Scanner {
        private final BufferedReader reader;
        private StringTokenizer st;

        Scanner(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
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

        long nextLong() {
            return Long.parseLong(next());
        }

    }


    private static final long[][] rect = new long[100][100];
    private static final long[][] sum = new long[100][100];

    public static void main(String[] args) {

        PrintWriter out = new PrintWriter(System.out);
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        int c = 0;
        while (c++ < tc) {
            int n = s.nextInt();
            int m = s.nextInt();
            long K = s.nextLong();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    rect[i][j] = s.nextInt();
                    if (i == 0 && j == 0) sum[i][j] = rect[i][j];
                    else if (i == 0) sum[i][j] = rect[i][j] + sum[i][j - 1];
                    else if (j == 0) sum[i][j] = rect[i][j] + sum[i - 1][j];
                    else sum[i][j] = rect[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                }
            }

            int maxArea = 0;
            long p = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    for (int k = i; k < n; k++) {

                        {
                            long price;

                            if (i == 0 && j == 0) price = sum[k][j];
                            else if (i == 0) price = sum[k][j] - sum[k][j - 1];
                            else if (j == 0) price = sum[k][j] - sum[i - 1][j];
                            else price = sum[k][j] - sum[i - 1][j] - sum[k][j - 1] + sum[i - 1][j - 1];

                            if (price > K) break;
                        }

                        for (int l = Math.max(maxArea / (k - i + 1) + j - 1, 0); l < m; l++) {

                            long price;

                            if (i == 0 && j == 0) price = sum[k][l];
                            else if (i == 0) price = sum[k][l] - sum[k][j - 1];
                            else if (j == 0) price = sum[k][l] - sum[i - 1][l];
                            else price = sum[k][l] - sum[i - 1][l] - sum[k][j - 1] + sum[i - 1][j - 1];
                            int curArea = (k - i + 1) * (l - j + 1);

                            if (price > K) break;

                            if (curArea >= maxArea) {
                                if (maxArea == curArea) {
                                    p = Math.min(price, p);
                                } else {
                                    p = price;
                                    maxArea = curArea;
                                }
                            }
                        }
                    }
                }
            }

            out.printf("Case #%d: %d %d\n", c, maxArea, p);
        }
        out.flush();
    }
}
