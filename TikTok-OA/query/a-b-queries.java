int[] solution(int[] a, int[] b, int[][] queries) {
	Map<Integer, Integer> aFreq = new HashMap<>();
	Map<Integer, Integer> bFreq = new HashMap<>();
	List<Integer> ansList = new ArrayList<>();
	for (int num : a) {
		aFreq.put(num, aFreq.getOrDefault(num, 0) + 1);
	}
	for (int num : b) {
		bFreq.put(num, bFreq.getOrDefault(num, 0) + 1);
	}
	for (int[] q : queries) {
		if (q[0] == 0) {
			int key = a[q[1]];
			aFreq.put(key, aFreq.get(key) - 1);
			if (aFreq.get(key) == 0) {
				aFreq.remove(key);
			}
			aFreq.put(q[2], aFreq.getOrDefault(q[2], 0) + 1);
			a[q[1]] = q[2];
		}
		if (q[0] == 1) {
			int target = q[1];
			int pairs = 0;
			for (int key : aFreq.keySet()) {
				if (bFreq.containsKey(target - key)) {
					pairs += aFreq.get(key) * bFreq.get(target - key);
				}
			}
			ansList.add(pairs);
		}
	}
	int[] ans = new int[ansList.size()];
	for (int i = 0; i < ansList.size(); i++) {
		ans[i] = ansList.get(i);
	}
	return ans;
}
