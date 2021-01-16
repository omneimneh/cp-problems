package cp.problems.legacy.others;

import java.util.Scanner;

public class LittleEndian {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (s.hasNextInt()) {
            int n = s.nextInt();

            if (n >= 0) {

                int p1 = n % 256;
                int p2 = (n >> 8) % 256;
                int p3 = (n >> 16) % 256;
                int p4 = (n >> 24) % 256;

                System.out.println(n + " converts to " + (p1 * 16777216 + p2 * 65536 + p3 * 256 + p4));

            } else {
                int[] arr = new int[32];
                for (int i = 0; i < 32; i++) {
                    arr[i] = (n >> i) & 1;
                }

                int p1 = 0;
                int pow2 = 128;
                for (int i = 7; i >= 0; i--) {
                    p1 += pow2 * arr[i];
                    pow2 /= 2;
                }

                int p2 = 0;
                pow2 = 128;
                for (int i = 15; i >= 8; i--) {
                    p2 += pow2 * arr[i];
                    pow2 /= 2;
                }

                int p3 = 0;
                pow2 = 128;
                for (int i = 23; i >= 16; i--) {
                    p3 += pow2 * arr[i];
                    pow2 /= 2;
                }

                int p4 = 0;
                pow2 = 128;
                for (int i = 31; i >= 24; i--) {
                    p4 += pow2 * arr[i];
                    pow2 /= 2;
                }

                System.out.println(n + " converts to " + (p1 * 16777216 + p2 * 65536 + p3 * 256 + p4));

            } // -2147483648 => 128

        }
    }

}
