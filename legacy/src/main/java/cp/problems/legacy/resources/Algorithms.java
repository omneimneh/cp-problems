package cp.problems.legacy.resources;

import java.util.Arrays;

@SuppressWarnings({"unused"})
public
class Algorithms {

    public static void heapPermutation(int[] array, int n, int size) {
        if (n == 1) {
            System.out.println(Arrays.toString(array));
            return;
        }
        for (int i = 0; i < n; i++) {
            heapPermutation(array, n - 1, size);
            if (n % 2 != size % 2) {
                int tmp = array[i];
                array[i] = array[n - 1];
                array[n - 1] = tmp;
            } else {
                int tmp = array[0];
                array[0] = array[n - 1];
                array[n - 1] = tmp;
            }
        }
    }

    private static int gcd(int a, int b) {
        return (b == 0 ? a : gcd(b, a % b));
    }

    private static int lcm(int a, int b) {
        return (a * (b / gcd(a, b)));
    }

    private static int x, y, d; // xa + yb = gcd(a,b) = d;

    private static void extendedEuclid(int a, int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            d = a;
            return;
        }
        extendedEuclid(b, a % b);
        int x1 = x; // old x
        int old = y; // old y
        x = old;
        y = x1 - old * (a / b);
    }

    public static void main(String[] args) {
        extendedEuclid(7, 4);
        System.out.println(d);
        System.out.println(x + " " + y);
    }
}
