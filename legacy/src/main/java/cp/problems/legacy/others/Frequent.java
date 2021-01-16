package cp.problems.legacy.others;

import java.io.IOException;

import static java.lang.Math.max;

class node {
    int startNum, startCount, endNum, endCount, maxFrequency;
}

public class Frequent {


    private static final int[] arr = new int[100000];
    private static final node[] tree = new node[300000];

    private static void prepareNode(int curIdx, int x) {
        tree[curIdx] = new node();
        tree[curIdx].startNum = arr[x];
        tree[curIdx].endNum = arr[x];
        tree[curIdx].startCount = 1;
        tree[curIdx].endCount = 1;
        tree[curIdx].maxFrequency = 1;
    }

    private static void prepareTreeUtil(int left, int right, int curIdx) {
        if (left == right) {
            prepareNode(curIdx, left);
            return;
        }
        int mid = (left + right) / 2;
        int leftChild = 2 * curIdx + 1;
        int rightChild = 2 * curIdx + 2;
        prepareTreeUtil(left, mid, leftChild);
        prepareTreeUtil(mid + 1, right, rightChild);
        tree[curIdx] = new node();
        tree[curIdx].startNum = tree[leftChild].startNum;
        tree[curIdx].endNum = tree[rightChild].endNum;
        tree[curIdx].startCount = ((tree[leftChild].startNum == tree[rightChild].startNum) ?
                (tree[leftChild].startCount + tree[rightChild].startCount) : (tree[leftChild].startCount));
        tree[curIdx].endCount = ((tree[leftChild].endNum == tree[rightChild].endNum) ?
                (tree[leftChild].endCount + tree[rightChild].endCount) : (tree[rightChild].endCount));
        int midCount = 0;
        if (tree[leftChild].endNum == tree[rightChild].startNum)
            midCount = tree[leftChild].endCount + tree[rightChild].startCount;
        tree[curIdx].maxFrequency = max(midCount, max(tree[leftChild].maxFrequency, tree[rightChild].maxFrequency));
    }

    private static void prepareTree(int n) {
        prepareTreeUtil(0, n - 1, 0);
    }


    private static node maxFrequencyUtil(int left, int right, int lRange, int rRange, int curIdx) {
        if (left == lRange && right == rRange)
            return tree[curIdx];
        int mid = (left + right) / 2;
        int leftChild = 2 * curIdx + 1;
        int rightChild = 2 * curIdx + 2;
        if (mid >= rRange)
            return maxFrequencyUtil(left, mid, lRange, rRange, leftChild);
        if (mid + 1 <= lRange)
            return maxFrequencyUtil(mid + 1, right, lRange, rRange, rightChild);
        node leftMax = maxFrequencyUtil(left, mid, lRange, mid, leftChild);
        node rightMax = maxFrequencyUtil(mid + 1, right, mid + 1, rRange, rightChild);
        node result = new node();
        result.startNum = leftMax.startNum;
        result.endNum = rightMax.endNum;
        result.startCount = ((leftMax.startNum == rightMax.startNum) ?
                (leftMax.startCount + rightMax.startCount) : (leftMax.startCount));
        result.endCount = ((leftMax.endNum == rightMax.endNum) ?
                (leftMax.endCount + rightMax.endCount) : (rightMax.endCount));
        int midCount = 0;
        if (leftMax.endNum == rightMax.startNum)
            midCount = leftMax.endCount + rightMax.startCount;
        result.maxFrequency = max(midCount, max(leftMax.maxFrequency, rightMax.maxFrequency));
        return result;
    }

    private static int findFrequency(int x, int y, int n) {
        node result = maxFrequencyUtil(0, n - 1, x, y, 0);
        return result.maxFrequency;
    }

    public static void main(String[] args) throws Exception {
        int n, q, x, y;
        while (true) {
            n = readInt();
            if (n == 0)
                break;
            q = readInt();
            for (int i = 0; i < n; i++)
                arr[i] = readInt();
            prepareTree(n);
            //displayTree(n);
            while (q-- > 0) {
                x = readInt();
                y = readInt();
                System.out.printf("%d\n", findFrequency(x - 1, y - 1, n));
            }
        }
    }

    private static int readInt() throws IOException {
        int ret = 0;
        boolean dig = false;
        boolean neg = false;
        for (int c; (c = System.in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (c == '-')
                neg = true;
            else if (dig)
                break;
        }
        return neg ? -ret : ret;
    }
}