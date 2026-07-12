/* Split an array into two non-empty sets such that the 
 * greatest common divisor (GCD) of the product of the elements 
 * in the two sets is 1. Find the number of ways to perform this split.
 */

// Disjoint Set Union class
static class DSU {
	int[] parent;
	int count;

	public DSU(int n) {
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		count = n;
	}

	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	public void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX != rootY) {
			parent[rootX] = rootY;
			count--;
		}
	}
}

public int split(int[] array) {
	int n = array.length;
	DSU dsu = new DSU(n);

	Map<Integer, Integer> primeToFirstIndex = new HashMap<>();
	for (int i = 0; i < n; i++) {
		int val = array[i];
		for (int d = 2; d * d <= val; d++) {
			if (val % d == 0) {
				if (primeToFirstIndex.containsKey(d)) {
					dsu.union(i, primeToFirstIndex.get(d));
				} else {
					primeToFirstIndex.put(d, i);
				}
				while (val % d == 0) {
					val /= d;
				}
			}
		}
		if (val > 1) {
			if (primeToFirstIndex.containsKey(val)) {
				dsu.union(i, primeToFirstIndex.get(val));
			} else {
				primeToFirstIndex.put(val, i);
			}
		}
	}
	int k = dsu.count;
	//2^k - 2
	int ans = 1;
	for (int i = 0; i < k; i++) {
		ans *= 2;
	}
	ans -= 2;
	return ans;
}
