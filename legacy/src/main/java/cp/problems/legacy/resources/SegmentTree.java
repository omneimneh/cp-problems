package cp.problems.legacy.resources;

@SuppressWarnings({"unused"})
class SegmentTree {
    public static int[] arr = new int[100000];
    public static int len = 100000;
    public static int[] minQuery = new int[300000];

    private static void constructSTUtil(int left, int right, int index) {
        if (left == right) {
            minQuery[index] = arr[left];
            return;
        }
        int mid = left + (right - left) / 2;

        int leftSide = 2 * index + 1;
        int rightSide = 2 * index + 2;

        constructSTUtil(left, mid, leftSide);
        constructSTUtil(mid + 1, right, rightSide);

        minQuery[index] = Math.min(minQuery[leftSide], minQuery[rightSide]);

    }

    private static int STUtil(int left, int right, int qs, int qe, int index) {
        if (left >= qs && qe >= right) {
            return minQuery[index];
        }

        if (left > qe || right < qs) {
            return Integer.MAX_VALUE;
        }

        int mid = left + (right - left) / 2;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        int leftMin = STUtil(left, mid, qs, qe, leftChild);
        int rightMin = STUtil(mid + 1, right, qs, qe, rightChild);

        return Math.min(leftMin, rightMin);
    }

    private static int updateST(int newValIndex, int newVal, int index, int left, int right) {
        if (left == newValIndex && right == newValIndex) {
            return minQuery[index] = newVal;
        }

        if (newValIndex < left || newValIndex > right) {
            return minQuery[index];
        }

        int mid = left + (right - left) / 2;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        int updateLeft = updateST(newValIndex, newVal, leftChild, left, mid);
        int updateRight = updateST(newValIndex, newVal, rightChild, mid + 1, right);

        return minQuery[index] = Math.min(updateLeft, updateRight);
    }

    private static void constructST() {
        constructSTUtil(0, len - 1, 0);
    }

    private static void updateST(int index, int val) {
        updateST(index, val, 0, 0, len - 1);
    }

    private static int findMin(int from, int to) {
        return STUtil(0, len - 1, from, to, 0);
    }
}
