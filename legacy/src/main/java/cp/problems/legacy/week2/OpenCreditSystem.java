package cp.problems.legacy.week2;

import java.util.Scanner;

@SuppressWarnings("DuplicatedCode")
public class OpenCreditSystem {

    private static final int[] arr = new int[100000];
    private static int len;
    private static final int[] minQuery = new int[300000];

    private static void constructSTUtil(int left, int right, int index) {
        if (left == right) {
            minQuery[index] = arr[left];
            return;
        }
        int mid = left + (right - left) / 2;

        int leftSide = 2 * index + 1;
        int rightSide = 2 * index + 2;

        constructSTUtil(left, mid, leftSide);
        constructSTUtil(mid + 1, right, rightSide);

        minQuery[index] = Math.min(minQuery[leftSide], minQuery[rightSide]);

    }

    private static int STUtil(int left, int right, int qs, int qe, int index) {
        if (left >= qs && qe >= right) {
            return minQuery[index];
        }

        if (left > qe || right < qs) {
            return Integer.MAX_VALUE;
        }

        int mid = left + (right - left) / 2;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        int leftMin = STUtil(left, mid, qs, qe, leftChild);
        int rightMin = STUtil(mid + 1, right, qs, qe, rightChild);

        return Math.min(leftMin, rightMin);
    }

    private static int findMin(int from) {
        return STUtil(0, len - 1, from, len - 1, 0);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();

        while (tc-- > 0) {
            len = s.nextInt();
            for (int i = 0; i < len; i++) {
                arr[i] = s.nextInt();
            }
            constructSTUtil(0, len - 1, 0);

            int max = Integer.MIN_VALUE;

            for (int i = 0; i < len - 1; i++) {
                int senior = arr[i];
                int min = findMin(i + 1);
                if (senior - min > max) {
                    max = senior - min;
                }
            }

            System.out.println(max);
        }
    }
}
