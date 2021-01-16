package cp.problems.legacy.week4;

import java.util.Scanner;

public class KnuthPermutation {

    private static String str;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            str = s.next();
            rec("" + str.charAt(0), 1);
            if (s.hasNext())
                System.out.println();
        }
    }

    private static void rec(String current, int index) {
        if (index == str.length()) {
            System.out.println(current);
            return;
        }
        for (int i = 0; i < current.length() + 1; i++) {
            rec(current.substring(0, i) + str.charAt(index) + current.substring(i), index + 1);
        }
    }
}
