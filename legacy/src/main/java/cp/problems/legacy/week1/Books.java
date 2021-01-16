package cp.problems.legacy.week1;

import java.util.Scanner;

public class Books {

    private static final int[] arr = new int[10000000];
    private static int m, k, remainingK;
    private static boolean[] res;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            m = s.nextInt();
            k = s.nextInt();
            for (int i = 0; i < m; i++) {
                arr[i] = s.nextInt();
            }
            remainingK = k;
            res = new boolean[m];
            res[0] = true;

            exec(0, m);
            //System.out.println(Arrays.toString(res));
            //System.out.println(remainingK);
            System.out.print(arr[0]);
            for (int i = 1; i < m; i++) {
                if (res[i]) {
                    System.out.print(" / " + arr[i]);
                } else {
                    System.out.print(" " + arr[i]);
                }
            }
            System.out.println();
        }
    }

    private static void exec(int from, int to) {
        if (remainingK == 1 || to - from == 0) {
            return;
        }
        int sum = 0;
        long min = getMin(from, to, remainingK);
        //System.out.println(from + ":" + to);
        //System.out.println("min: " + min);
        int counter = to - 1;
        while (counter >= from) {
            sum += arr[counter];
            if (sum > min) {
                res[++counter] = true;
                remainingK--;
                sum = 0;
            }
            counter--;
        }

//        int lastTrue = from;
//        for (int i = from + 1; i < to; i++) {
//            if (res[i]) {
//                exec(lastTrue, i);
//                lastTrue = i;
//            }
//        }
        for (int i = 0; i < m && remainingK > 1; i++) {
            if (!res[i]) {
                res[i] = true;
                remainingK--;
            }
        }

    }

    private static long getMin(int from, int to, int k) {
        long low = -1, hi = 5000000000L;
        long res = -1;
        boolean found = false;
        while (hi - low > 1) {
            long mid = (hi + low) / 2;
            int ok = test(mid, from, to, k);
            if (ok == 0) {
                hi = mid;
                res = mid;
                found = true;
            } else if (ok > 0) {
                low = mid;
            } else {
                hi = mid;
                if (!found) {
                    res = mid;
                }
            }

        }
        return res;

    }

    private static int test(long t, int from, int to, int k) {
        long sum = 0;
        int counter = from;
        int slashes = 1;

        while (counter < to) {
            sum += arr[counter];

            if (arr[counter] > t) {
                return 1;
            }

            if (sum > t) {
                sum = 0;
                counter--;
                slashes++;
            }

            counter++;
        }

        if (slashes < k) {
            return -1;
        } else if (slashes > k) {
            return 1;
        }
        return 0;
    }

}
