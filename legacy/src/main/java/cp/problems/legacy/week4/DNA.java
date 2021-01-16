package cp.problems.legacy.week4;

import java.util.Scanner;

public class DNA {

    private static int N, K, M;
    private static char[] seq;
    private static final char[][] results = new char[1000000][];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tc = s.nextInt();

        while (tc-- > 0) {
            N = s.nextInt();
            K = s.nextInt();
            seq = s.next().toCharArray();
            M = 0;
            rec(0, 0);
            System.out.println(M);
            for (int i = 0; i < M; i++) {
                System.out.println(results[i]);
            }
        }
    }

    private static void rec(int changed, int current) {

        if (changed > K) return;

        if (current == N) {
            results[M] = new char[N];
            System.arraycopy(seq, 0, results[M], 0, N);
            M++;
            return;
        }


        char tmp = seq[current];
        if (tmp != 'A') {
            seq[current] = 'A';
            rec(changed + 1, current + 1);
            seq[current] = tmp;
        } else {
            rec(changed, current + 1);
        }

        if (tmp != 'C') {
            seq[current] = 'C';
            rec(changed + 1, current + 1);
            seq[current] = tmp;
        } else {
            rec(changed, current + 1);
        }

        if (tmp != 'G') {
            seq[current] = 'G';
            rec(changed + 1, current + 1);
            seq[current] = tmp;
        } else {
            rec(changed, current + 1);
        }

        if (tmp != 'T') {
            seq[current] = 'T';
            rec(changed + 1, current + 1);
            seq[current] = tmp;
        } else {
            rec(changed, current + 1);
        }
    }
}
