package cp.problems.legacy.week2;

import java.util.Scanner;

public class LargestSquare {

    private static char[][] arr;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tc = s.nextInt();

        while (tc-- > 0) {
            int M = s.nextInt();
            int N = s.nextInt();
            int Q = s.nextInt();

            arr = new char[M][];

            for (int i = 0; i < M; i++) {
                arr[i] = s.next().toCharArray();
            }

            System.out.println(M + " " + N + " " + Q);
            for (int i = 0; i < Q; i++) {
                int y = s.nextInt();
                int x = s.nextInt();

                int side = 1;
                char target = arr[y][x];
                //System.out.println(target + ": " + y + ", " + x);
                label:
                while (y + side < M && y - side >= 0 && x + side < N && x - side >= 0) {
                    for (int j = x - side; j <= x + side; j++) {
                        if (arr[y - side][j] != target) {
                            break label;
                        }

                        if (arr[y + side][j] != target) {
                            break label;
                        }
                    }

                    for (int j = y - side; j <= y + side; j++) {
                        if (arr[j][x - side] != target) {
                            break label;
                        }

                        if (arr[j][x + side] != target) {
                            break label;
                        }
                    }

                    side++;
                }

                System.out.println(2 * side - 1);
            }

        }
    }
}
