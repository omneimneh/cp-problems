package cp.problems.legacy.week3;

import java.util.Scanner;
import java.util.Stack;

public class Permuted {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            String str = s.next();
            int ith = s.nextInt() - 1;

            String result = str.charAt(0) + "";
            Stack<Integer> stack = new Stack<>();
            for (int i = str.length(); i >= 2; i--) {
                stack.push(ith % i);
                ith /= i;
            }
            int index = 1;
            while (!stack.isEmpty()) {
                int insertAt = stack.pop();
                result = result.substring(0, insertAt) + str.charAt(index) + result.substring(insertAt);
                index++;
            }
            System.out.println(result);
        }
    }
}
