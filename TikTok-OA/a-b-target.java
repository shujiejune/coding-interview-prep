int solution(int[] a, int[] b, int target) {
	Map<Integer, Integer> occur = new HashMap<>();
	int m = a.length, n = b.length;
	for (int i = 0; i < m; i++) {
		int sum = 0;
		for (int j = i; j < m; j++) {
			sum += a[j];
			occur.put(sum, occur.getOrDefault(sum, 0) + 1);
		}
	}
	int count = 0;
	for (int i = 0; i < n; i++) {
		int sum = 0;
		for (int j = i; j < n; j++) {
			sum += b[j];
			if (occur.containsKey(target - sum)) {
				count += occur.get(target - sum);
			}
		}
	}
	return count;
}
