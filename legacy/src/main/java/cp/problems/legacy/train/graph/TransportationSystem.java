package cp.problems.legacy.train.graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TransportationSystem {

	private static class City implements Comparable<City> {

		int index;
		double dist;

		City(int index, double dist) {
			this.index = index;
			this.dist = dist;
		}

		@Override
		public int compareTo(City o) {
			return Double.compare(dist, o.dist);
		}

	}

	private static class State implements Comparable<State> {

		int index;
		double dist;
		ArrayList<Integer> cities;

		State(int index, double dist, ArrayList<Integer> cities) {
			this.index = index;
			this.dist = dist;
			this.cities = cities;
		}

		@Override
		public int compareTo(State o) {
			return Double.compare(dist, o.dist);
		}

	}

	private static int n, r;
	private static final Point[] cities = new Point[(int) 1e4];
	private static final boolean[] mstSet = new boolean[(int) 1e4];
	private static final boolean[] mstSet2 = new boolean[(int) 1e4];
	private static final boolean[] vis = new boolean[(int) 1e4];
	private static PriorityQueue<City> queue = new PriorityQueue<>();

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		int c = 0;
		while (c++ < tc) {
			n = s.nextInt();
			r = s.nextInt();
			for (int i = 0; i < n; i++) {
				cities[i] = new Point(s.nextInt(), s.nextInt());
				mstSet2[i] = mstSet[i] = vis[i] = false;
			}

			double res = 0.0;
			int stateCount = 0;

			PriorityQueue<State> states = new PriorityQueue<>();
			for (int i = 0; i < n; i++) {
				if (!vis[i]) {
					ArrayList<Integer> arr = new ArrayList<>();
					State st = new State(stateCount, stateCount == 0 ? 0 : Integer.MAX_VALUE, arr);

					vis[i] = true;
					stateCount++;

					queue.clear();
					queue.add(new City(i, 0));
					dfs(i, arr);
					res += prims();

					states.add(st);
				}
			}

			System.out.printf("Case #%d: %d %d %d\n", c, stateCount, round(res), round(prims2(states)));

		}

	}

	private static int round(double res) {
		return (int) (res + 0.5);
	}

	private static void dfs(int u, ArrayList<Integer> arr) {
		arr.add(u);
		for (int i = 0; i < n; i++) {
			if (u != i && !vis[i] && sameState(i, u)) {
				vis[i] = true;
				queue.add(new City(i, Double.MAX_VALUE));
				dfs(i, arr);
			}
		}
	}

	private static double prims() {

		double res = 0.0;

		City current = queue.poll();
		assert current != null;
		mstSet[current.index] = true;

		while (!queue.isEmpty()) {

			PriorityQueue<City> tmp = new PriorityQueue<>();
			while (!queue.isEmpty()) {
				City c = queue.poll();
				if (!mstSet[c.index]) {
					c.dist = Math.min(c.dist, dist(current.index, c.index));
					tmp.add(c);
				}
			}
			queue = tmp;
			current = tmp.poll();
			assert current != null;
			res += current.dist;
			mstSet[current.index] = true;

		}

		return res;
	}

	private static double prims2(PriorityQueue<State> state) {

		double res = 0.0;

		State current = state.poll();
		assert current != null;
		mstSet2[current.index] = true;

		while (!state.isEmpty()) {

			PriorityQueue<State> tmp = new PriorityQueue<>();
			while (!state.isEmpty()) {
				State s = state.poll();
				if (!mstSet2[s.index]) {
					double dist = Double.MAX_VALUE;
					for (int city : s.cities) {
						for (int city2 : current.cities) {
							dist = Math.min(dist, dist(city, city2));
						}
					}
					s.dist = Math.min(s.dist, dist);
					tmp.add(s);
				}
			}
			state = tmp;
			current = tmp.poll();
			assert current != null;
			res += current.dist;
			mstSet2[current.index] = true;

		}

		return res;
	}

	private static boolean sameState(int i, int j) {
		return dist(i, j) < r;
	}

	private static double dist(int i, int j) {
		return Math.sqrt((cities[i].x - cities[j].x) * (cities[i].x - cities[j].x)
				+ (cities[i].y - cities[j].y) * (cities[i].y - cities[j].y));
	}

}
