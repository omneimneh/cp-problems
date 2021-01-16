package cp.problems.legacy.others;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class ProblemSet {
    int numOfTrials;
    Integer time;
    int teamNumber;
}

public class Scoreboard {

    static int[] ranks;
    static int PENALTY;
    static Comparator<ProblemSet[]> comparator;
    static int T, P;
    static ProblemSet[][] arr;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            T = s.nextInt();
            P = s.nextInt();
            if (T == 0 && P == 0) break;
            ranks = new int[T];
            arr = new ProblemSet[T][P];
            for (int i = 0; i < T; i++) {
                for (int j = 0; j < P; j++) {
                    String[] tmp = s.next().split("/");
                    ProblemSet ps = new ProblemSet();
                    ps.numOfTrials = Integer.parseInt(tmp[0]);
                    ps.teamNumber = i;
                    if (!tmp[1].equals("-")) {
                        ps.time = Integer.parseInt(tmp[1]);
                    }
                    arr[i][j] = ps;
                }
            }

            comparator = (o1, o2) -> {
                int psolved1 = 0;
                int penalty1 = 0;
                for (ProblemSet problemSet : o1) {
                    if (problemSet.time != null) {
                        psolved1++;
                        penalty1 += problemSet.time + problemSet.numOfTrials * PENALTY;
                    }
                }
                int psolved2 = 0;
                int penalty2 = 0;
                for (ProblemSet problemSet : o2) {
                    if (problemSet.time != null) {
                        psolved2++;
                        penalty2 += problemSet.time + problemSet.numOfTrials * PENALTY;
                    }
                }

                if (psolved1 > psolved2) {
                    return -1;
                } else if (psolved2 > psolved1) {
                    return 1;
                }

                return penalty1 - penalty2;
            };

            Arrays.sort(arr, comparator);

            for (int k = 0; k < arr.length; k++) {
                ProblemSet[] set = arr[k];
                ranks[set[0].teamNumber] = k + 1;
            }

            System.out.println(binarySearch1());
            System.out.println(binarySearch2());
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public static int binarySearch1() {
        int start = 1, end = (int) 1e9 + 7;
        int mid = (start + end) / 2;
        while (end - start > 1) {
            mid = (start + end) / 2;
            if (isOK(mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return mid;
    }

    @SuppressWarnings("DuplicatedCode")
    public static int binarySearch2() {
        int start = 1, end = (int) 1e9 + 7;
        int mid = (start + end) / 2;
        while (end - start > 1) {
            mid = (start + end) / 2;
            if (isOK(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return mid;
    }

    public static boolean isOK(int penalty) {
        PENALTY = penalty;
        Arrays.sort(arr, comparator);
        int[] newRanks = new int[T];
        for (int k = 0; k < arr.length; k++) {
            ProblemSet[] set = arr[k];
            newRanks[set[0].teamNumber] = k + 1;
        }
        return compare(newRanks);
    }

    static boolean compare(int[] a1) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != ranks[i])
                return false;
        }
        return true;
    }
}
