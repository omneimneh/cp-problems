package cp.problems.legacy.others;

import java.util.ArrayList;
import java.util.Scanner;

public class Neighbors {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tc = s.nextInt();

        while (tc-- > 0) {
            int cnt = s.nextInt();
            s.next();

            int min = Integer.MAX_VALUE;
            ArrayList<Integer> results = new ArrayList<>();
            for (int i = 0; i < cnt; i++) {
                String nextLine = s.nextLine();
                int neighbors = nextLine.split(" ").length;
                if(neighbors < min) {
                    min = neighbors;
                    results.clear();
                    results.add(i + 1);
                } else if(neighbors == min) {
                    results.add(i + 1);
                }
            }
            System.out.print(results.get(0));
            for (int i = 1; i < results.size(); i++) {
                System.out.print(" " + results.get(i));
            }
            System.out.println();
        }
    }
}
