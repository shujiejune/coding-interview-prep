int[] solution(int[] queries) {
	int n = queries.length;
	int[] segments = new int[n];
	Map<Integer, Integer> parents = new HashMap<>();
	int num = 0;
	for (int i = 0; i < n; i++) {
		int q = queries[i];
		boolean leftOccupied = parents.containsKey(q - 1);
		boolean rightOccupied = parents.containsKey(q + 1);
		if (leftOccupied && rightOccupied) { continue; }
		parents.put(q, q);
		num++;
		if (leftOccupied) {
			unionSets(q, q - 1, parents);
			num--;
		}
		if (rightOccupied) {
			unionSets(q, q + 1, parents);
			num--;
		}
		segments[i] = num;
	}
	return segments;
}

void unionSets(int a, int b, Map<Integer, Integer> parents) {
	int rootA = findRoot(parents, a);
	int rootB = findRoot(parents, b);
	if (rootA != rootB) {
		parents.put(rootA, rootB);
	}
}

int findRoot(Map<Integer, Integer> parents, int h) {
	if (parents.get(h) == h) {
		return h;
	}
	int root = findRoot(parents, parents.get(h));
	parents.put(h, root);
	return root;
}
