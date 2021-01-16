package cp.problems.legacy.others;

import java.util.Scanner;

public class TheSnail {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int H, U, D, F;
        for (; ; ) {
            H = s.nextInt();
            if (H == 0) {
                break;
            }
            U = s.nextInt();
            D = s.nextInt();
            F = s.nextInt();

            int days = 1;
            double currentDistance = 0;
            for (; ; ) {
                double increase = U - (U * (days - 1) * F / 100.0);

                if (increase > 0) {
                    currentDistance += increase;
                }

                if (currentDistance > H) {
                    System.out.println("success on day " + days);
                    break;
                }

                currentDistance -= D;

                if (currentDistance < 0) {
                    System.out.println("failure on day " + days);
                    break;
                }
                days++;
            }
        }
    }
}
