#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <vector>
#include <map>
#include <set>
#include <queue>
#include <stack>
#include <cstdlib>
#include <cmath>
#include <bitset>
#include <fstream>

using namespace std;

class Interval {
public:
	double start, end;
};

bool compare(Interval i1, Interval i2) {
	return i1.end == i2.end ? i1.start < i2.start : i1.end < i2.end;
}

int main() {

	Interval inter[1001];

	long double xs[1001];
	long double ys[1001];

	int tc = 1;

	while (true) {


		int n, d;
		scanf("%d", &n);
		scanf("%d", &d);

		if (n == 0 && d == 0) {
			break;
		}

		bool cannot = false;

		for (int i = 0; i < n; i++)
		{
			scanf("%Lf", &xs[i]);
			scanf("%Lf", &ys[i]);

			if (ys[i] > d) {
				cannot = true;
				continue;
			}

			long double dist = sqrt(d*d - ys[i] * ys[i]);

			inter[i].start = xs[i] - dist;
			inter[i].end = xs[i] + dist;
		}

		if (cannot) {
			printf("Case %d: -1\n", tc++);
			continue;
		}

		sort(inter, inter + n, compare);

		int count = 0;
		double last = -INFINITY;

		for (int i = 0; i < n; i++)
		{
			if (last >= inter[i].start && last <= inter[i].end) {
				continue;
			}
			else {
				last = inter[i].end;
				count++;
			}
		}

		printf("Case %d: %d\n", tc++, count);
	}

}
