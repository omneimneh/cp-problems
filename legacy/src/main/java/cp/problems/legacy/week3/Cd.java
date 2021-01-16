package cp.problems.legacy.week3;

import java.util.Scanner;

public class Cd {

    private static int N, len;
    private static int[] tracks;

    private static int maxSum;
    private static boolean[] result;
    private static StringBuilder res;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            N = s.nextInt();
            len = s.nextInt();

            tracks = new int[len];
            result = new boolean[len];

            for (int i = 0; i < len; i++) {
                tracks[i] = s.nextInt();
            }

            maxSum = Integer.MIN_VALUE;
            findTracks(0, 0);

            System.out.print(res.toString());

            System.out.printf("sum:%d\n", maxSum);
        }
    }

    private static void findTracks(int currentSum, int pos) {

        if (currentSum > N) {
            return;
        }

        if (pos == len) {
            if (currentSum > maxSum) {
                res = new StringBuilder();
                for (int i = 0; i < len; i++) {
                    if (result[i]) {
                        res.append(tracks[i]).append(" ");
                    }
                }
                maxSum = currentSum;
            }
            return;
        }

        result[pos] = true;
        findTracks(currentSum + tracks[pos], pos + 1);
        result[pos] = false;
        findTracks(currentSum, pos + 1);

    }
}
