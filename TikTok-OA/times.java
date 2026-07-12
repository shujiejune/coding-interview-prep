int solution(int[] times) {
	int n = times.length;
	int[] completedAt = new int[n];
	int[][] people = new int[n][2];
	for (int i  = 0; i < n; i++) {
		people[i][0] = times[0];
		people[i][1] = i;
	}
	Queue<int[]> q = new ArrayDeque<>();
	int currCompletedAt = 0;
	for (int p : people) {
		int arriveAt = p[0];
		while (!q.isEmpty() && currCompletedAt <= arriveAt) {
			int[] frontier = q.poll();
			completedAt[frontier[1]] = Math.max(frontier[0], currCompletedAt) + 300;
			currCompletedAt = completedAt[frontier[1]];
		}
		if (q.size() > 10) {
			completedAt[p[1]] = arriveAt;
		} else {
			q.offer(p);
		}
	}
	while (!q.isEmpty()) {
		int[] frontier = q.poll();
		completedAt[frontier[1]] = Math.max(frontier[0], currCompletedAt) + 300;
		currCompletedAt = completedAt[frontier[1]];
	}
	return completedAt;
}
