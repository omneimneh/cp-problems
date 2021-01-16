package cp.problems.legacy.week5;

import java.util.*;

public class Bridge {
    private static final int[] people = new int[1001];
    private static final boolean[] passed = new boolean[1001];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        while (tc-- > 0) {
            int n = s.nextInt();

            for (int i = 0; i < n; i++) {
                people[i] = s.nextInt();
            }

            Arrays.sort(people, 0, n);
            for (int i = 0; i < n; i++) {
                passed[i] = false;
            }

            int left = n;
            int total = 0;
            ArrayList<String> res = new ArrayList<>();
            while (left > 3) {
                int A = 0;
                while (passed[A]) {
                    A++;
                }
                int B = A + 1;
                while (passed[B]) {
                    B++;
                }

                int X = n - 1;
                while (passed[X]) {
                    X--;
                }

                int Y = X - 1;
                while (passed[Y]) {
                    Y--;
                }

                int opt1 = people[X] + people[Y] + 2 * people[A];
                int opt2 = 2 * people[B] + people[A] + people[X];

                if (opt1 < opt2) {
                    res.add(people[A] + " " + people[Y]);
                    res.add(people[A] + "");
                    res.add(people[A] + " " + people[X]);
                    res.add(people[A] + "");
                } else {
                    res.add(people[A] + " " + people[B]);
                    res.add(people[A] + "");
                    res.add(people[X] + " " + people[Y]);
                    res.add(people[B] + "");
                }
                passed[X] = passed[Y] = true;
                left -= 2;
                total += Math.min(opt1, opt2);
            }
            if (left == 3) {
                int A = 0;
                while (passed[A]) {
                    A++;
                }
                int B = A + 1;
                while (passed[B]) {
                    B++;
                }
                int X = n - 1;
                while (passed[X]) {
                    X--;
                }
                res.add(people[A] + " " + people[B]);
                res.add(people[A] + "");
                res.add(people[A] + " " + people[X]);
                total += people[X] + people[A] + people[B];
            } else if (left == 2) {
                int A = 0;
                while (passed[A]) {
                    A++;
                }
                int B = A + 1;
                while (passed[B]) {
                    B++;
                }
                res.add(people[A] + " " + people[B]);
                total += people[B];
            } else if (left == 1) {
                res.add(people[0] + "");
                total += people[0];
            }

            System.out.println(total);
            for (String re : res) {
                System.out.println(re);
            }

            if (tc != 0)
                System.out.println();

        }
    }
}
