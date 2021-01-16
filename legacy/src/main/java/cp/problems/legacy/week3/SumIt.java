package cp.problems.legacy.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SumIt {
    private static final int[] array = new int[1000];
    private static int sum;
    private static final int[] result = new int[1000];
    private static int resSize;
    private static final ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            sum = s.nextInt();
            int size = s.nextInt();
            if (sum == 0 && size == 0) {
                break;
            }
            for (int i = 0; i < 1000; i++) {
                array[i] = 0;
            }
            for (int i = 0; i < size; i++) {
                array[s.nextInt()]++;
            }
            list.clear();
            System.out.printf("Sums of %d:\n", sum);
            rec(0, 999);
            list.sort((o1, o2) -> {
                int min = Math.min(o1.length, o2.length);
                for (int i = 0; i < min; i++) {
                    int x = -Integer.compare(o1[i], o2[i]);
                    if (x != 0) return x;
                }
                return 0;
            });
            if (list.isEmpty()) System.out.println("NONE");
            for (int[] arr : list) {
                for (int i = 0; i < arr.length - 1; i++) {
                    System.out.printf("%d+", arr[i]);
                }
                System.out.println(arr[arr.length - 1]);
            }
        }

    }

    private static void rec(int current, int pos) {
        if (current == sum) {
            list.add(Arrays.copyOf(result, resSize));
            return;
        }
        if (current > sum || pos == -1) {
            return;
        }

        rec(current, pos - 1);
        for (int i = array[pos]; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                result[resSize] = pos;
                resSize++;
            }
            rec(current + pos * i, pos - 1);
            resSize -= i;
        }
    }
}
