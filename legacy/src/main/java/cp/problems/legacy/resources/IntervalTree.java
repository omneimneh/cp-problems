package cp.problems.legacy.resources;

import java.util.ArrayList;

class Interval {
    int start, end;
    // custom variables
}

class IntervalNode {
    Interval i;
    int max, min;
}

public class IntervalTree {
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