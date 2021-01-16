package cp.problems.legacy.facebook;

import java.util.Arrays;
import java.util.Scanner;

public class MrX {

	private enum Operator {
		AND, OR, XOR
	}

	private enum Operand {
		ZERO(0), ONE(1), X(2), XBAR(3), NO_OPERAND(4);

		private final int value;

		Operand(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private static class Expr {
		Expr left, right;
		Operator operator;
		Operand operand;
		int id;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int tc = s.nextInt();
		int c = 0;
		while (c++ < tc) {

			globId = 0;

			for (Integer[] integers : dp) {
				Arrays.fill(integers, null);
			}

			Expr magic = parse(s.next());
			int res = min(changes(Operand.ZERO, magic), changes(Operand.ONE, magic));
			System.out.printf("Case #%d: %d\n", c, res);
		}
	}

	private static final Integer[][] dp = new Integer[5][300];
	private static int globId;

	private static int changes(Operand NEEDED_RESULT, Expr current) {
		if (current.operand != Operand.NO_OPERAND) {
			return NEEDED_RESULT == current.operand ? 0 : 1;
		} else {

			if (dp[NEEDED_RESULT.getValue()][current.id] != null)
				return dp[NEEDED_RESULT.getValue()][current.id];

			if (current.operator == Operator.AND) {
				if (NEEDED_RESULT == Operand.ONE) {
					return dp[NEEDED_RESULT.getValue()][current.id] = changes(Operand.ONE, current.left)
							+ changes(Operand.ONE, current.right);
				} else if (NEEDED_RESULT == Operand.ZERO) {

					int poss1 = changes(Operand.ZERO, current.left);
					int poss2 = changes(Operand.ZERO, current.right);

					return dp[NEEDED_RESULT.getValue()][current.id] = min(poss1, poss2);
				} else if (NEEDED_RESULT == Operand.X) {
					return dp[NEEDED_RESULT.getValue()][current.id] = changes(Operand.X, current.left)
							+ changes(Operand.X, current.right);
				} else {
					return dp[NEEDED_RESULT.getValue()][current.id] = changes(Operand.XBAR, current.left)
							+ changes(Operand.XBAR, current.right);
				}

			} else if (current.operator == Operator.OR) {
				if (NEEDED_RESULT == Operand.ZERO) {
					return dp[NEEDED_RESULT.getValue()][current.id] = changes(Operand.ZERO, current.left)
							+ changes(Operand.ZERO, current.right);
				} else if (NEEDED_RESULT == Operand.ONE) {

					int poss1 = changes(Operand.ONE, current.left);
					int poss2 = changes(Operand.ONE, current.right);

					return dp[NEEDED_RESULT.getValue()][current.id] = min(poss1, poss2);
				} else if (NEEDED_RESULT == Operand.X) {
					int poss1 = changes(Operand.X, current.left) + changes(Operand.ONE, current.right);
					int poss2 = changes(Operand.ONE, current.left) + changes(Operand.X, current.right);

					return dp[NEEDED_RESULT.getValue()][current.id] = min(poss1, poss2);
				} else {
					int poss1 = changes(Operand.XBAR, current.left) + changes(Operand.ONE, current.right);
					int poss2 = changes(Operand.ONE, current.left) + changes(Operand.XBAR, current.right);

					return dp[NEEDED_RESULT.getValue()][current.id] = min(poss1, poss2);
				}

			} else if (current.operator == Operator.XOR) {

				if (NEEDED_RESULT == Operand.ZERO) {
					int poss1 = changes(Operand.X, current.left) + changes(Operand.X, current.right);
					int poss2 = changes(Operand.XBAR, current.left) + changes(Operand.XBAR, current.right);
					int poss3 = changes(Operand.ONE, current.left) + changes(Operand.ONE, current.right);
					int poss4 = changes(Operand.ZERO, current.left) + changes(Operand.ZERO, current.right);
					return dp[NEEDED_RESULT.getValue()][current.id] = min(poss1, poss2, poss3, poss4);
				} else if (NEEDED_RESULT == Operand.ONE) {
					int poss1 = changes(Operand.X, current.left) + changes(Operand.XBAR, current.right);
					int poss2 = changes(Operand.XBAR, current.left) + changes(Operand.X, current.right);
					int poss3 = changes(Operand.ONE, current.left) + changes(Operand.ZERO, current.right);
					int poss4 = changes(Operand.ZERO, current.left) + changes(Operand.ONE, current.right);
					return dp[NEEDED_RESULT.getValue()][current.id] = min(poss1, poss2, poss3, poss4);
				} else if (NEEDED_RESULT == Operand.X) {

					int poss1 = changes(Operand.X, current.left) + changes(Operand.ZERO, current.right);
					int poss2 = changes(Operand.ZERO, current.left) + changes(Operand.X, current.right);
					int poss3 = changes(Operand.XBAR, current.left) + changes(Operand.ONE, current.right);
					int poss4 = changes(Operand.ONE, current.left) + changes(Operand.XBAR, current.right);

					return dp[NEEDED_RESULT.getValue()][current.id] = min(poss1, poss2, poss3, poss4);
				} else {

					int poss1 = changes(Operand.XBAR, current.left) + changes(Operand.ZERO, current.right);
					int poss2 = changes(Operand.ZERO, current.left) + changes(Operand.XBAR, current.right);
					int poss3 = changes(Operand.X, current.left) + changes(Operand.ONE, current.right);
					int poss4 = changes(Operand.ONE, current.left) + changes(Operand.X, current.right);

					return dp[NEEDED_RESULT.getValue()][current.id] = min(poss1, poss2, poss3, poss4);
				}
			}
			throw new RuntimeException("Invalid Operator");
		}
	}

	private static int min(int... vars) {
		int min = Integer.MAX_VALUE;
		for (int var : vars) {
			min = Math.min(min, var);
		}
		return min;
	}

	private static Expr parse(String str) {

		return new Object() {

			int pos = 0;

			Operator nextOperator(int pos) {
				switch (str.charAt(pos)) {
				case '&':
					return Operator.AND;
				case '^':
					return Operator.XOR;
				case '|':
					return Operator.OR;

				}
				throw new RuntimeException("Unkown operation");
			}

			private Operand nextOperand() {
				switch (str.charAt(pos)) {
				case 'x':
					return Operand.X;
				case 'X':
					return Operand.XBAR;
				case '1':
					return Operand.ONE;
				case '0':
					return Operand.ZERO;
				}
				throw new RuntimeException("Unkown operand");
			}

			Expr parse() {
				if (str.charAt(pos) == '(') {
					return this.parseExpr();
				} else {
					Expr current = new Expr();
					current.id = globId++;
					current.operand = nextOperand();
					pos++;
					return current;
				}
			}

			Expr parseExpr() {
				pos++;
				Expr current = new Expr();
				current.id = globId++;
				current.operand = Operand.NO_OPERAND;
				current.left = parse();
				current.operator = nextOperator(pos);
				pos++;
				current.right = parse();
				pos++;
				return current;

			}

		}.parse();
	}

}
