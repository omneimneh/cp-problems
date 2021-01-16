package cp.problems.legacy.week4;

import java.util.Scanner;

public class HammingDistance {

    private static int size, xor;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            size = s.nextInt();
            xor = s.nextInt();
            print(0, size - 1, 0);
            if (tc != 0) System.out.println();
        }
    }

    private static void print(int x, int bits, int result) {
        // if remaining xor are more than remaining bits or xor limit exceeded
        if (x > xor || xor - x > bits + 1) return;

        if (bits == -1) {
            System.out.println(pad(Integer.toBinaryString(result), size));
            return;
        }

        print(x, bits - 1, result);
        print(x + 1, bits - 1, result + (1 << bits));

    }

    private static String pad(String toBinaryString, int size) {
        StringBuilder toBinaryStringBuilder = new StringBuilder(toBinaryString);
        while (toBinaryStringBuilder.length() < size) {
            toBinaryStringBuilder.insert(0, "0");
        }
        toBinaryString = toBinaryStringBuilder.toString();
        return toBinaryString;
    }
}
