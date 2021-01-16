package cp.problems.legacy.week3;

import java.util.Scanner;

public class BundlingNewspaper {

    private static final String[] newspapers = new String[15];
    private static final boolean[] res = new boolean[15];
    private static int size;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        s.nextLine();
        s.nextLine();
        while (tc-- > 0) {
            String line = s.nextLine();

            size = 0;
            while (s.hasNextLine() && !(newspapers[size] = s.nextLine()).isEmpty()) {
                size++;
            }
            if (line.charAt(0) == '*') {
                for (int i = 1; i <= size; i++) {
                    System.out.printf("Size %d\n", i);
                    subset(0, i);
                    System.out.println();
                }
            } else {
                int space = line.indexOf(' ');
                if (space > -1) {
                    int a = Integer.parseInt(line.substring(0, space));
                    int b = Integer.parseInt(line.substring(space + 1));
                    for (int i = a; i <= b; i++) {
                        System.out.printf("Size %d\n", i);
                        subset(0, i);
                        System.out.println();
                    }
                } else {
                    int i = Integer.parseInt(line);
                    System.out.printf("Size %d\n", i);
                    subset(0, i);
                    System.out.println();
                }
            }
            if(tc != 0) {
                System.out.println();
            }
        }
    }

    private static void subset(int index, int curSize) {
        if (curSize == 0) {
            for (int i = 0; i < index - 1; i++) {
                if (res[i]) {
                    System.out.print(newspapers[i] + ", ");
                }
            }
            System.out.println(newspapers[index - 1]);
            return;
        }

        if (index == size) return;

        res[index] = true;
        subset(index + 1, curSize - 1);
        res[index] = false;
        subset(index + 1, curSize);
    }
}
