package cp.problems.legacy.others;

import java.util.Scanner;

public class WordScramble {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] words = line.split(" ");
            System.out.print(reverse(words[0]));
            for (int i = 1; i < words.length; i++) {
                System.out.print(" " + reverse(words[i]));
            }
            System.out.println();
        }
    }

    private static String reverse(String word) {
        StringBuilder retVal = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            retVal.append(word.charAt(i));
        }
        return retVal.toString();
    }
}
