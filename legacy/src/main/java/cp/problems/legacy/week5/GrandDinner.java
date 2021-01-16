package cp.problems.legacy.week5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Table implements Comparable<Table> {
    int capacity;
    int index;

    @Override
    public int compareTo(Table o) {
        return Integer.compare(capacity, o.capacity);
    }
}

public class GrandDinner {

    private static final Integer[] teams = new Integer[71];
    private static final Table[] tables = new Table[51];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        for (int i = 0; i < tables.length; i++) {
            tables[i] = new Table();
        }

        while (true) {
            int M = s.nextInt();
            int N = s.nextInt();

            if (M == 0 && N == 0) {
                break;
            }

            for (int i = 0; i < M; i++) {
                teams[i] = s.nextInt();
            }

            for (int i = 0; i < N; i++) {
                tables[i].capacity = s.nextInt();
                tables[i].index = i + 1;
            }

            //Arrays.sort(teams, 0, M, Collections.reverseOrder());
            Arrays.sort(tables, 0, N, Collections.reverseOrder());

            int[][] results = new int[M][];

            int curTeam = 0;
            boolean cannot = false;
            label:
            while (curTeam < M) {

                results[curTeam] = new int[teams[curTeam]];

                if (teams[curTeam] > N) {
                    cannot = true;
                    break;
                }

                int last = tables[0].capacity;
                int lastIndex = 0;
                for (int i = 0; i < teams[curTeam]; i++) {
                    if (tables[i].capacity != last) {
                        last = tables[i].capacity;
                        lastIndex = i;
                    }
                    results[curTeam][i] = tables[i].index;
                    tables[i].capacity--;
                    if (tables[i].capacity < 0) {
                        cannot = true;
                        break label;
                    }
                }
                Arrays.sort(tables, lastIndex, N, Collections.reverseOrder());
                Arrays.sort(results[curTeam]);
                curTeam++;
            }

            if (cannot) {
                System.out.println(0);
            } else {
                System.out.println(1);
                for (int[] result : results) {
                    for (int j = 0; j < result.length - 1; j++) {
                        System.out.print(result[j] + " ");
                    }
                    System.out.println(result[result.length - 1]);
                }
            }
        }
    }
}
