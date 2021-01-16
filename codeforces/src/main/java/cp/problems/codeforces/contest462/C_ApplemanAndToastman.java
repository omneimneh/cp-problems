package cp.problems.codeforces.contest462;

import java.util.Arrays;
import java.util.Scanner;

// PROBLEM: https://codeforces.com/contest/462/problem/C
public class C_ApplemanAndToastman {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        Arrays.sort(arr);
        long sum = n > 1 ? n * arr[n - 1] : arr[n - 1];
        for (int i = n; i > 1; i--) {
            sum += arr[i - 2] * i;
        }
        System.out.println(sum);
    }
}
