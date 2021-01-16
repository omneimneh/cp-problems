package cp.problems.legacy.week3;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Code implements Comparable<Code> {
    int code;
    char character;

    Code(char character, int code) {
        this.character = character;
        this.code = code;
    }


    @Override
    public String toString() {
        return "[" + character + ": " + code + "]";
    }

    @Override
    public int compareTo(Code o) {
        return (code + "").compareTo(o.code + "");
    }
}

public class BadCode {

    private static int size;
    private static Code[] codes;
    private static String str;
    private static final char[] result = new char[100];
    private static final PrintWriter out = new PrintWriter(System.out);
    private static final PriorityQueue<String> pq = new PriorityQueue<>();


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = 1;
        while ((size = s.nextInt()) != 0) {
            codes = new Code[size];
            for (int i = 0; i < size; i++) {
                codes[i] = new Code(s.next().charAt(0), s.nextInt());
            }
            Arrays.sort(codes);
            out.printf("Case #%d\n", tc++);
            str = s.next();
            pq.clear();
            decrypt(0, 0);
            int c = 100;
            while (!pq.isEmpty() && c-- > 0) {
                out.println(pq.remove());
            }
            out.println();
            //out.println();
            out.flush();
        }

    }

    private static int lower_bound_if_exist(String strCode) {
        if (strCode.isEmpty()) return -1;
        int low = -1;
        int hi = codes.length;
        int mid;
        int res = -1;
        while (hi - low > 1) {
            mid = low + (hi - low) / 2;
            if ((codes[mid].code + "").startsWith(strCode)) {
                hi = mid;
                res = mid;
            } else if ((codes[mid].code + "").compareTo(strCode) > 0) {
                hi = mid;
            } else {
                low = mid;
            }
        }
        return res;
    }

    private static void decrypt(int pos, int iteration) {
        //if (counter == 100) return;
        if (pos == str.length()) {
            StringBuilder x = new StringBuilder();
            for (int i = 0; i < iteration; i++) {
                x.append(result[i]);
            }
            pq.add(x.toString());
            return;
        }

        int j = 1;

        while (pos + j <= str.length() /*&& j < 3*/) {
            String toCompare = str.substring(pos, pos + j);

            if (Integer.parseInt(toCompare) == 0) {
                pos++;
                continue;
            }

            int lowerBound;
            if ((lowerBound = lower_bound_if_exist(toCompare)) == -1) {
                break;
            }
            if ((codes[lowerBound].code + "").equals(toCompare)) {
                result[iteration] = codes[lowerBound].character;
                decrypt(pos + j, iteration + 1);
            }
            j++;
        }
    }

}
