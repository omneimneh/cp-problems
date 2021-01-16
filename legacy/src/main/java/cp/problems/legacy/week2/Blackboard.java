package cp.problems.legacy.week2;

import java.util.Scanner;

public class Blackboard {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tc = s.nextInt();
        while (tc-- > 0) {

            int wordCount = s.nextInt();
            String[] words = new String[wordCount];

            for (int i = 0; i < wordCount; i++) {
                words[i] = s.next();
            }

            int maxCandies = 0;
            for (int i = 0; i < wordCount; i++) {
                String word = words[i];
                for (int j = 0; j < wordCount; j++) {
                    if (j != i) {
                        int candies = countBest(word, words[j]);
                        if (maxCandies < candies) {
                            maxCandies = candies;
                        }
                    }
                }
            }

            System.out.println(maxCandies);
        }
    }

    private static int countBest(String word1, String word2) {
        int bestCount = 0;

        if (word1.length() < word2.length()) {
            String tmp = word1;
            word1 = word2;
            word2 = tmp;
        }
        for (int i = -word1.length(); i < word1.length(); i++) {
            int currentCount = 0;
            for (int j = 0; j < word1.length(); j++) {
                if (i + j > -1 && i + j < word2.length()) {
                    if (word1.charAt(j) == word2.charAt(i + j)) {
                        currentCount++;
                    }
                }
            }
            if (currentCount > bestCount) {
                bestCount = currentCount;
            }
        }
        return bestCount;
    }
}
