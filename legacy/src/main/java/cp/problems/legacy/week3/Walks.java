package cp.problems.legacy.week3;

import java.util.Scanner;

public class Walks {
    private static final int[][] adj = new int[10][10];
    private static final boolean[] visited = new boolean[10];
    private static int len, size;
    private static final int[] walk = new int[10];
    private static boolean found;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        do {
            size = s.nextInt();
            len = s.nextInt() + 1;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    adj[i][j] = s.nextInt();
                }
            }

            found = false;
            printPaths(0, len);

            if (!found) {
                System.out.printf("no walk of length %d\n", len - 1);
            }

            if (s.hasNextInt() && (s.nextInt() == -9999)) {
                System.out.println();
            } else break;

        } while (true);
    }


    private static void printPaths(int currentNode, int walkLen) {
        if (walkLen == 1) {
            System.out.printf("(%d", walk[0] + 1);
            for (int i = 1; i < len - 1; i++) {
                System.out.printf(",%d", walk[i] + 1);
            }
            System.out.printf(",%d)\n", currentNode + 1);
            found = true;
            return;
        }

        visited[currentNode] = true;
        walk[len - walkLen] = currentNode;

        for (int i = 0; i < size; i++) {
            if (adj[currentNode][i] == 1 && !visited[i]) {
                printPaths(i, walkLen - 1);
            }
        }
        visited[currentNode] = false;
    }
}
