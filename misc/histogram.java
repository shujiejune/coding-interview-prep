/* Given a non-negative integer array representing the heights of 
 * a list of adjacent bars. Suppose each bar has a width of 1. Find 
 * the largest rectangular area that can be formed in the histogram.
 * Assumptions: The given array is not null or empty.
 */
public class Solution {
	public int largest(int[] array) {
		Deque<Integer> stack = new ArrayDeque<>();
		int n = array.length;
		if (n == 1) {
			return array[0];
		}
		int globalMax = 0;
		stack.offerLast(0);
		for (int i = 1; i <= n; i++) {
			int rightBoundHeight = i == n ? 0 : array[i];
			while (!stack.isEmpty() && array[stack.peekLast()] >= rightBoundHeight) {
				int height = array[stack.pollLast()];
				int width = stack.isEmpty() ? i : i - stack.peekLast() - 1;
				globalMax = Math.max(globalMax, height * width);
			}
			stack.offerLast(i);
		}
		return globalMax;
	}
}

/* TC: O(n)
 * SC: O(n)
 */
