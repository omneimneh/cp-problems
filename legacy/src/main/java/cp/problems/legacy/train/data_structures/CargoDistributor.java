package cp.problems.legacy.train.data_structures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class CargoDistributor {

	@SuppressWarnings("unchecked")
	private static final Queue<Integer>[] queues = new Queue[100];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int tc = scan.nextInt();

		while (tc-- > 0) {
			int n, s, q;
			n = scan.nextInt();
			s = scan.nextInt();
			q = scan.nextInt();

			int left = 0;

			for (int i = 0; i < n; i++) {
				int size = scan.nextInt();
				left += size;
				queues[i] = new LinkedList<>();
				while (size-- > 0) {
					queues[i].add(scan.nextInt() - 1);
				}
			}

			int time = 0;
			int station = 0;

			Stack<Integer> stack = new Stack<>();

			while (left > 0) {
				while (!stack.isEmpty()) {
					if (stack.peek() == station) {
						stack.pop();
						time++;
						left--;
					} else {
						if (queues[station].size() < q) {
							((LinkedList<Integer>) queues[station]).addLast(stack.pop());
							time++;
						} else {
							break;
						}
					}
				}

				while (stack.size() < s && !queues[station].isEmpty()) {
					stack.push(queues[station].poll());
					time++;
				}
				station = (station + 1) % n;
				time += 2;
			}

			System.out.println(time - 2);
		}

	}

}
