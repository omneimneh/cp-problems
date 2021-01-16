package cp.problems.legacy.week3;

import java.util.Scanner;

public class OutOf5 {

    private static final int[] numbers = new int[5];
    private static final int[] current = new int[5];
    private static final boolean[] perm = new boolean[5];
    private static boolean possible;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            boolean exit = true;
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = s.nextInt();
                if (numbers[i] != 0) {
                    exit = false;
                }
            }
            if (exit) {
                break;
            }
            possible = false;
            solve(0);
            if (possible) {
                System.out.println("Possible");
            } else {
                System.out.println("Impossible");
            }
        }
    }

    private static void solve(int pos) {
        if (possible) return;
        if (pos == current.length) {
            rec(1, current[0]);
            return;
        }
        for (int i = 0; i < perm.length; i++) {
            if (!perm[i]) {
                current[pos] = numbers[i];
                perm[i] = true;
                solve(pos + 1);
                perm[i] = false;
            }
        }
    }

    private static void rec(int pos, int result) {
        if (pos == current.length) {
            if (result == 23) {
                possible = true;
            }
            return;
        }

        rec(pos + 1, result + current[pos]);
        if (possible) return;
        rec(pos + 1, result - current[pos]);
        if (possible) return;
        rec(pos + 1, result * current[pos]);
    }
}
