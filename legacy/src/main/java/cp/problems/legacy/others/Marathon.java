package cp.problems.legacy.others;

import java.util.LinkedList;
import java.util.Scanner;

public class Marathon {

    @SuppressWarnings("unchecked")
    private static final LinkedList<Integer>[] tree = new LinkedList[100000];
    private static final int[] parent = new int[100000];
    private static final int[] values = new int[100000];
    private static final int[][] bitCounter = new int[100000][32];
    private static final int[] xorFromRoot = new int[100000];
    private static final int[] sumFromLeaves = new int[100000];
    private static final int[] subTreeCount = new int[100000];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();

        for (int i = 0; i < tree.length; i++) {
            tree[i] = new LinkedList<>();
        }

        while (tc-- > 0) {
            int cities = s.nextInt();
            for (int i = 0; i < cities; i++) {
                values[i] = s.nextInt();
                tree[i].clear();
            }

            for (int i = 1; i < cities; i++) {
                int next = s.nextInt() - 1;
                tree[next].add(i);
                parent[i] = next;
            }
            constructTree(0, 0);
            for (int i = 0; i < cities; i++) {
                System.out.println(xorFromRoot[i] + "|" + sumFromLeaves[i] + "|" + subTreeCount[i]);
            }
            System.out.println();
            for (int i = 0; i < cities; i++) {
                for (int j = 0; j < 32; j++) {
                    System.out.print(bitCounter[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println(beautyQuery(1)); // node 2

        }

    }

    private static int beautyQuery(int node) {

        if (node == 0) return sumFromLeaves[node];

        int sum = sumFromLeaves[node];
        int toXorWith = xorFromRoot[parent[node]];
        int[] bitCount = bitCounter[node];
        int count = subTreeCount[node];

        for (int i = 0; i < 32; i++) {
            if (((toXorWith >> i) & 1) == 1) {
                int onesCount = bitCount[i];
                int zerosCount = count - onesCount;

                sum -= onesCount * (1 << i);
                sum += zerosCount * (1 << i);
            }
        }
        return sum;
    }

    private static int constructTree(int curNode, int xor) {
        xorFromRoot[curNode] = xor ^ values[curNode];
        int sum = xorFromRoot[curNode];

        subTreeCount[curNode] = 1;
        for (int i = 0; i < 32; i++) {
            bitCounter[curNode][i] = (xorFromRoot[curNode] >> i) & 1;
        }

        for (Integer node : tree[curNode]) {
            sum += constructTree(node, xorFromRoot[curNode]);

            subTreeCount[curNode] += subTreeCount[node];
            for (int i = 0; i < 32; i++) {
                bitCounter[curNode][i] += bitCounter[node][i];
            }

        }

        return sumFromLeaves[curNode] = sum;
    }
}
