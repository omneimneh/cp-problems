package cp.problems.legacy.week4;

import java.util.ArrayList;
import java.util.Scanner;

public class CallForwarding {

    private static final boolean[] vis = new boolean[10000];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();

        System.out.println("CALL FORWARDING OUTPUT");
        for (int i = 1; i <= tc; i++) {
            IntervalTree tree = new IntervalTree();
            while (true) {
                int forward = s.nextInt();
                if (forward == 0) break;
                int start = s.nextInt();
                int end = start + s.nextInt();
                int to = s.nextInt();
                Interval interval = new Interval();
                interval.start = start;
                interval.end = end;
                interval.forward = forward;
                interval.to = to;
                tree.insert(interval);
            }
            System.out.printf("SYSTEM %d\n", i);
            while (true) {
                int time = s.nextInt();
                if (time == 9000) break;
                int call = s.nextInt();
                ArrayList<Interval> result = tree.getContainingIntervals(time);
                int forwardTo = getForwardTo(call, result);
                System.out.printf("AT %s CALL TO %s RINGS %s\n", f(time), f(call), f(forwardTo));
            }
        }
        System.out.println("END OF OUTPUT");

    }

    private static String f(int time) {
        StringBuilder res = new StringBuilder(time + "");
        while (res.length() < 4) {
            res.insert(0, "0");
        }
        return res.toString();
    }

    private static int getForwardTo(int forwardTo, ArrayList<Interval> result) {
        for (int j = 0; j < result.size(); j++) {
            if (result.get(j).forward == forwardTo) {
                if (vis[forwardTo]) {
                    return 9999;
                }
                vis[forwardTo] = true;
                int tmp = getForwardTo(result.get(j).to, result);
                vis[forwardTo] = false;
                forwardTo = tmp;
                break;
            }
        }
        return forwardTo;
    }


}


class Interval {
    int start, end;
    int forward, to;
}

class IntervalNode {
    Interval i;
    int max, min;
}

class IntervalTree {
    private final IntervalNode[] arr = new IntervalNode[1000000];
    private final ArrayList<Interval> result = new ArrayList<>();

    void insert(Interval i) {
        insert(i, 0);
    }

    private void insert(Interval i, int node) {
        if (arr[node] == null) {
            arr[node] = new IntervalNode();
            arr[node].i = i;
            arr[node].max = i.end;
            arr[node].min = i.start;
            return;
        }

        int l = arr[node].i.start;
        if (i.start < l) {
            insert(i, 2 * node + 1);
        } else {
            insert(i, 2 * node + 2);
        }
        if (arr[node].max < i.end) {
            arr[node].max = i.end;
        }

        if (arr[node].min > i.start) {
            arr[node].min = i.start;
        }
    }

    ArrayList<Interval> getContainingIntervals(int k) {
        result.clear();
        getContainingIntervals(k, 0);
        return result;
    }

    private void getContainingIntervals(int k, int i) {
        if (arr[i] == null) return;

        if (arr[i].i.start <= k && arr[i].i.end >= k) {
            result.add(arr[i].i);
        }

        if (arr[2 * i + 1] != null && arr[2 * i + 1].max >= k) {
            getContainingIntervals(k, 2 * i + 1);
        }

        if (arr[2 * i + 2] != null && arr[2 * i + 2].min <= k) {
            getContainingIntervals(k, 2 * i + 2);
        }

    }
}