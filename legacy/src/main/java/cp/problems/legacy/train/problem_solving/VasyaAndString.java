package cp.problems.legacy.train.problem_solving;

import java.util.Scanner;

public class VasyaAndString {

	private static class Gap {
		int l, r;
		char character;

		private int len() {
			return r - l + 1;
		}
	}

	private static final Gap[] gaps = new Gap[100000];
	private static int n, k;
	private static char[] str;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		k = s.nextInt();

		str = s.next().toCharArray();

		char prevChar = str[0];
		int index = 0;
		gaps[index] = new Gap();
		gaps[index].l = 0;
		gaps[index].character = prevChar;
		for (int i = 0; i < n; i++) {
			char currentChar = str[i];
			if (currentChar != prevChar) {
				gaps[index].r = i - 1;
				++index;
				gaps[index] = new Gap();
				gaps[index].l = i;
				gaps[index].character = currentChar;
			}
			prevChar = currentChar;
		}
		gaps[index].r = n - 1;
		++index;

		int result = Math.max(slidingWindow('a', index), slidingWindow('b', index));
		System.out.println(result);

	}

	private static int slidingWindow(char ch, int glen) {
		int start = 0;
		int end;

		int kleft = k;

		int curr = -1;

		if (gaps[0].character != ch) {
			if (gaps[0].len() <= kleft) {
				kleft -= gaps[0].len();
				end = 1;
				curr++;
			} else {
				if (glen == 1) {
					return 0;
				} else {
					start = end = 1;
					curr++;
				}
			}
		} else {
			end = 1;
		}

		while (++curr < glen) {
			if (gaps[curr].character == ch) {
				end = curr + 1;
			} else {
				if (kleft >= gaps[curr].len()) {
					kleft -= gaps[curr].len();
					end = curr + 1;
				} else {
					break;
				}
			}
		}

		int best = Math.min(n, gaps[end - 1].r - gaps[start].l + 1 + kleft);

		while (end < glen) {
			if (gaps[start].character != ch) {
				kleft += gaps[start].len();
				start++;
			} else {
				start++;
				kleft += gaps[start].len();
				start++;
			}

			if (end < start) {
				end = start;
				kleft = k;
			}

			if (end == glen)
				break;

			while (true) {
				if (end == glen) {
					break;
				}
				if (gaps[end].character != ch) {
					if (gaps[end].len() <= kleft) {
						kleft -= gaps[end].len();
						end++;
						if (end < glen) {
							end++;
						}
					} else {
						break;
					}
				} else {
					end++;
				}
			}
			best = Math.max(best, Math.min(n, gaps[end - 1].r - gaps[start].l + 1 + kleft));
		}

		return best;
	}

}
