package cp.problems.legacy.others;

import java.util.Scanner;

public class TEXQuote {

    private static boolean opened = false;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            String line = s.nextLine();
            String replace = replaceTEX(line);

            System.out.println(replace);
        }
    }

    private static String replaceTEX(String line) {
        for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '\"') {
                if (opened) {
                    line = line.substring(0, i) + "''" + line.substring(i + 1);
                } else {
                    line = line.substring(0, i) + "``" + line.substring(i + 1);
                }
                opened = !opened;
            }
        }
        return line;
    }
}
