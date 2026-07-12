class Solution {
	public int[] dailyTemperatures(int[] temperatures) {
		int n = temperatures.length;
		int[] ans = new int[n];
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			if (stack.isEmpty()) {
				stack.offerLast(i);
			} else {
				while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peekLast()]) {
					ans[stack.peekLast()] = i - stack.peekLast();
					stack.pollLast();
				}
				stack.offerLast(i);
			}
		}
		return ans;
	}
}
