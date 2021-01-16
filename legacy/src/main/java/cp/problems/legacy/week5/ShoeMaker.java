package cp.problems.legacy.week5;

import java.util.Arrays;
import java.util.Scanner;

class Job implements Comparable<Job> {
    Job(int i, int d, int f) {
        index = i;
        days = d;
        fine = f;
    }

    int days, fine, index;

    @Override
    public int compareTo(Job j) {
        return Double.compare((double) days / fine, (double) j.days / j.fine);
    }

}

public class ShoeMaker {

    private static final Job[] jobs = new Job[1000];
    private static int jobCount;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            jobCount = s.nextInt();
            for (int i = 0; i < jobCount; i++) {
                jobs[i] = new Job(i + 1, s.nextInt(), s.nextInt());
            }
            Arrays.sort(jobs, 0, jobCount);
            for (int i = 0; i < jobCount - 1; i++) {
                System.out.printf("%d ", jobs[i].index);
            }
            System.out.println(jobs[jobCount - 1].index);

            if (tc != 0) System.out.println();
        }
    }
}
