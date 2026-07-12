/* HashMap */
int[] solution(int[] houses, int[] queries) {
	Arrays.sort(houses);
	int n = houses.length;
	int count = 1, start = houses[0];
	Map<Integer, Integer> startToEnd = new HashMap<>();
	startToEnd.put(houses[0], houses[0]);
	for (int i = 1; i < n; i++) {
		if (houses[i] == houses[i - 1] + 1) {
			startToEnd.put(start, houses[i]);
		} else {
			start = houses[i];
			startToEnd.put(start, start);
			count++;
		}
	}
	int[] ans = new int[queries.length];
	for (int i = 0; i < queries.length; i++) {
		int q = queries[i], largest = 0;
		for (int s : startToEnd.keySet()) {
			if (s <= q) {
				largest = Math.max(largest, s);
			}
		}
		int start = largest, end = startToEnd.get(largest);
		if (start == end) {
			startToEnd.remove(start);
			count--;
			ans[i] = count;
			continue;
		}
		if (q == end) {
			startToEnd.put(start, end - 1);
		} else if (q == start) {
			startToEnd.put(start + 1, end);
			startToEnd.remove(start);
		} else {
			startToEnd.put(start, q - 1);
			startToEnd.put(q + 1, end);
			count++;
		}
		ans[i] = count;
	}
	return ans;
}

/* TreeMap */
int[] solution(int[] houses, int[] quesries) {
	TreeMap<Integer, Integer> segments = new TreeMap<>();
	Arrays.sort(houses);
	int start = houses[0], n = houses.length;
	for (int i = 1; i < n; i++) {
		if (houses[i] > houses[i - 1] + 1) {
			segments.put(start, houses[i - 1]);
			start = houses[i];
		}
	}
	segments.put(start, houses[n - 1]);
	int[] ans = new int[queries.length];
	for (int i = 0; i < queries.length; i++) {
		int q = queries[i];
		Integer start = segments.floorKey(q);
		if (start != null) {
			int end = segments.get(start);
			if (q <= end) {
				segments.remove(start);
				if (q > start) {
					segments.put(start, q - 1);
				}
				if (q < end) {
					segments.put(q + 1, end);
				}
			}
		}
		ans[i] = segments.size();
	}
	return ans;
}

/* Disjoint Set Union */
int[] solution(int[] houses, int[] queries) {
	Set<Integer> remainHouses = new HashSet<>();
	for (int h : houses) {
		remainHouses.add(h);
	}
	for (int q : queries) {
		remainHouses.remove(q);
	}

	Map<Integer, Integer> parents = new HashMap<>();
	for (int h : remainHouses) {
		parents.put(h, h);
	}

	int count = remainHouses.size();
	for (int h : remainHouses) {
		if (parents.containsKey(h - 1)) {
			if (unionSets(h, h - 1, parents)) {
				count--;
			}
		}
	}

	int[] ans = new int[queries.length];
	for (int i = queries.length - 1; i >= 0; i--) {
		ans[i] = count;
		int q = queries[i];
		parents.put(q, q);
		count++;
		if (parents.containsKey(q - 1)) {
			if (unionSets(q, q - 1, parents)) {
				count--;
			}
		}
		if (parents.containsKey(q + 1)) {
			if (unionSets(q, q + 1, parents)) {
				count--;
			}
		}
	}
	return ans;
}

boolean unionSets(int h1, int h2, Map<Integer, Integer> parents) {
	int root1 = findRoot(h1, parents);
	int root2 = findRoot(h2, parents);
	if (root1 != root2) {
		parents.put(root1, root2);
		return true;
	}
	return false;
}

int findRoot(int h, Map<Integer, Integer> parents) {
	if (parents.get(h) == h) {
		return h;
	}
	int root = findRoot(parents.get(h), parents);
	parents.put(h, root);
	return root;
}
