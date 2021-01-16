package cp.problems.legacy.week3;

import java.util.Scanner;

public class PermutationsArray {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int len = s.nextInt();

        s.nextLine();
        for (int i = 0; i < len; i++) {
            s.nextLine();

            String[] indexes = s.nextLine().split(" ");
            String[] values = s.nextLine().split(" ");

            String[] array = new String[indexes.length];
            for (int j = 0; j < indexes.length; j++) {
                array[Integer.parseInt(indexes[j]) - 1] = values[j];
            }

            for (String value : array) {
                System.out.println(value);
            }
            if (i != len - 1)
                System.out.println();
        }
    }
}
