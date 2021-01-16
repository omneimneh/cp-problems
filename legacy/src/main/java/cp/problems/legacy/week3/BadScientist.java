package cp.problems.legacy.week3;

import java.util.Scanner;

public class BadScientist {

    private static final int[] conf1 = new int[10000];
    private static final int[] conf2 = new int[10000];
    private static int k, m;
    private static final boolean[] exclude = new boolean[10000];


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();

        while (tc-- > 0) {
            s.nextInt();

            k = s.nextInt();
            m = s.nextInt();

            for (int i = 0; i < m; i++) {
                conf1[i] = s.nextInt();
                conf2[i] = s.nextInt();
            }

            int min = min(0, 0);
            if (min != Integer.MAX_VALUE) {
                System.out.println(min);
            } else {
                System.out.println("IMPOSSIBLE");
            }

        }
    }

    private static int min(int index, int curSum) {

        if (curSum > k) {
            return Integer.MAX_VALUE;
        }

        if (index == m) {
            return curSum;
        }
        if (exclude[conf1[index]] || exclude[conf2[index]]) return min(index + 1, curSum);

        exclude[conf1[index]] = true;
        int a = min(index + 1, curSum + 1);
        exclude[conf1[index]] = false;

        exclude[conf2[index]] = true;
        int b = min(index + 1, curSum + 1);
        exclude[conf2[index]] = false;

        return Math.min(a, b);
    }


}
