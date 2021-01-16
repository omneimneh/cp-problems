package cp.problems.legacy.week6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Trip {

    private static class Scanner {
        public BufferedReader reader;
        public StringTokenizer st;

        public Scanner(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            st = null;
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = reader.readLine();
                    if (line == null)
                        return null;
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
    }

    private static String s1, s2;
    private static final int[][] dp = new int[1001][1001];
    private static final TreeSet<String> pq = new TreeSet<>();


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int tc = s.nextInt();


        while (tc-- > 0) {
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    dp[i][j] = -1;
                }
            }
            s1 = s.next();
            s2 = s.next();
            build(s1.length() - 1, s2.length() - 1, new StringBuilder());

            for (String str : pq) {
                out.println(str);
            }
            if (tc != 0) out.println();
        }
        out.flush();

    }

    private static void build(int last1, int last2, StringBuilder sb) {
        if (last1 == -1 || last2 == -1) {
            String r = sb.reverse().toString();
            pq.add(r);
            return;
        }
        int res = lcs(last1, last2);
        if (s1.charAt(last1) == s2.charAt(last2)) {
            sb.append(s1.charAt(last1));
            build(last1 - 1, last2 - 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            if (res == lcs(last1 - 1, last2)) {
                build(last1 - 1, last2, sb);
            }
            if (res == lcs(last1, last2 - 1)) {
                build(last1, last2 - 1, sb);
            }
        }
    }

    private static int lcs(int last1, int last2) {
        if (last1 == -1 || last2 == -1) return 0;

        if (dp[last1][last2] != -1) return dp[last1][last2];

        int res;
        if (s1.charAt(last1) == s2.charAt(last2)) {
            res = lcs(last1 - 1, last2 - 1) + 1;
        } else {
            res = Math.max(lcs(last1 - 1, last2), lcs(last1, last2 - 1));
        }

        return dp[last1][last2] = res;
    }

}
