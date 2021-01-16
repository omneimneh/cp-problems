package cp.problems.legacy.week2;

import java.util.HashMap;
import java.util.Scanner;

class ArrayHolder {
    int baseAddress;
    int elemSize;
    int dim;
    int[] lower;
    int[] upper;

    int c(int j) {
        return 0;
    }
}

public class MapMaker {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int defCount = s.nextInt();
        int refCount = s.nextInt();

        HashMap<String, ArrayHolder> arrays = new HashMap<>();
        for (int i = 0; i < defCount; i++) {
            ArrayHolder current = new ArrayHolder();
            String name = s.next();
            current.baseAddress = s.nextInt();
            current.elemSize = s.nextInt();
            current.dim = s.nextInt();
            current.lower = new int[current.dim];
            current.upper = new int[current.dim];
            for (int j = 0; j < current.upper.length; j++) {
                current.lower[j] = s.nextInt();
                current.upper[j] = s.nextInt();

            }
            arrays.put(name, current);
        }

        for (int i = 0; i < refCount; i++) {
            String name = s.next();
            ArrayHolder current = arrays.get(name);
            int[] indexes = new int[current.dim];
            for (int j = 0; j < current.dim; j++) {
                indexes[j] = s.nextInt();
            }
            int result = 0;
            int cdLdSum = 0;
            int cd = current.elemSize;
            cdLdSum += cd * current.lower[current.dim - 1];
            result += cd * indexes[indexes.length - 1];
            for (int j = current.dim - 2; j >= 0; j--) {
                cd = cd * (current.upper[j + 1] - current.lower[j + 1] + 1);
                cdLdSum += cd * current.lower[j];
                result += cd * indexes[j];
            }
            result += current.baseAddress - cdLdSum;
            System.out.print(name + "[" + indexes[0]);
            for (int j = 1; j < indexes.length; j++) {
                System.out.print(", " + indexes[j]);
            }
            System.out.println("] = " + result);
        }
    }
}
