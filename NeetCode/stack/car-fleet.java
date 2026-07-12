class Solution {
	public int carFleet(int target, int[] position, int[] speed) {
		int n = position.length;
		int[][] pair = new int[n][2];
		for (int i = 0; i < n; i++) {
			pair[i][0] = position[i];
			pair[i][1] = speed[i];
		}
		Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));
		Deque<Double> stack = new ArrayDeque<>();
		for (int[] p : pair) {
			double currTime = (double) (target - p[0]) / p[1];
			if (!stack.isEmpty() && currTime <= stack.peekLast()) {
				continue;
			} else {
				stack.offerLast(currTime);
			}
		}
		return stack.size();
	}
}
