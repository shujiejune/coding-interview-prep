class Solution {
	public int largestRectangleArea(int[] heights) {
		int n = heights.length;
		Deque<Integer> stack = new ArrayDeque<>();
		int maxArea = 0;
		for (int i = 0; i <= n; i++) {
			while (!stack.isEmpty() && (i == n || heights[stack.peekLast()] >= heights[i])) {
				int h = heights[stack.pollLast()];
				int w = stack.isEmpty() ? i : i - stack.peekLast() - 1;
				maxArea = Math.max(maxArea, h * w);

			}
			stack.offerLast(i);
		}
		return maxArea;
	}
}
