package cp.problems.legacy.week4;

import java.util.Scanner;

public class HanoiTroubles {

    private static final int[] towersTop = new int[1000];
    private static int N;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            N = s.nextInt();
            System.out.println(hanoi(1));
        }
    }

    private static boolean square(int k) {
        double sqrt = Math.sqrt(k);
        return sqrt == (int) sqrt;
    }

    private static int hanoi(int current) {
        int max = current - 1;
        for (int i = 0; i < N; i++) {
            if (towersTop[i] == 0) {
                int prev = towersTop[i];
                towersTop[i] = current;
                int x = hanoi(current + 1);
                towersTop[i] = prev;
                return x;
            }

            if (square(towersTop[i] + current)) {
                int prev = towersTop[i];
                towersTop[i] = current;
                int x = hanoi(current + 1);
                //max = Math.max(max, hanoi(current + 1));
                towersTop[i] = prev;
                return x;
            }
        }

        return max;
    }
}
