package cp.problems.legacy.others;

import java.util.Arrays;
import java.util.Scanner;


class Box {
    int[] dim = new int[10];
    int index;
}

public class StackingBoxes {

    private static int boxCount, dim, last;
    private static final Box[] boxes = new Box[30];
    private static final int[] LIS = new int[30];
    private static final int[] back = new int[30];

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box();
        }
        while (s.hasNext()) {
            boxCount = s.nextInt();
            dim = s.nextInt();
            for (int i = 0; i < boxCount; i++) {
                for (int j = 0; j < dim; j++) {
                    boxes[i].dim[j] = s.nextInt();
                    boxes[i].index = i + 1;
                }
                Arrays.sort(boxes[i].dim, 0, dim);
            }
            Arrays.sort(boxes, 0, boxCount, (o1, o2) -> {
                for (int i = 0; i < dim; i++) {
                    int x = Integer.compare(o1.dim[i], o2.dim[i]);
                    if (x != 0) {
                        return x;
                    }
                }
                return 0;
            });
            System.out.println(LIS());
            int k = last;
            StringBuilder reversed = new StringBuilder(boxes[k].index + "");
            for (k = back[k]; k != -1; k = back[k]) {
                reversed.insert(0, boxes[k].index + " ");
            }
            System.out.println(reversed);

        }
    }

    private static int LIS() {
        for (int i = 0; i < boxCount; i++) {
            LIS[i] = 1;
            back[i] = -1;
        }
        int m = 0;
        last = boxCount - 1;
        for (int i = 0; i < boxCount; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nests(j, i)) {
                    if (1 + LIS[j] > max) {
                        max = 1 + LIS[j];
                        back[i] = j;
                        if (max > m) {
                            last = i;
                        }
                    }
                }
            }
            LIS[i] = max;
            m = Math.max(m, max);
        }
        return m;
    }


    private static boolean nests(int b1, int b2) {
        for (int i = 0; i < dim; i++) {
            if (boxes[b1].dim[i] >= boxes[b2].dim[i]) return false;
        }
        return true;
    }
}
