package cp.problems.legacy.week5;

import java.util.Arrays;
import java.util.Scanner;

class Unit implements Comparable<Unit> {
    Unit(int l, int r) {
        left = l;
        right = r;
    }

    int left;
    int right;

    @Override
    public int compareTo(Unit o) {
        int i = Integer.compare(left, o.left);
        if (i != 0) return i;
        return Integer.compare(right, o.right);
    }

    @Override
    public String toString() {
        return left + ":" + right;
    }
}

public class Foreign {

    private static final Unit[] org = new Unit[500000];
    private static final Unit[] inv = new Unit[500000];

    private static int n;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while ((n = s.nextInt()) != 0) {
            for (int i = 0; i < n; i++) {
                int l = s.nextInt();
                int r = s.nextInt();
                org[i] = new Unit(l, r);
                inv[i] = new Unit(r, l);
            }

            Arrays.sort(org, 0, n);
            Arrays.sort(inv, 0, n);

            boolean yes = true;
            for (int i = 0; i < n; i++) {
                if (org[i].left != inv[i].left || org[i].right != inv[i].right) {
                    yes = false;
                    break;
                }
            }
            if (yes) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

}
