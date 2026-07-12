/* Given an integer array A and a sliding window of size K, 
* find the maximum value of each window as it slides from left to right.
* Assumptions:
* The given array is not null and is not empty
* K >= 1, K <= A.length
*/
public class Solution {
	public List<Integer> maxWindows(int[] array, int k) {
		int n = array.length;
		int[] ans = new int[n - k + 1];
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			int curr = array[i];
			while (!stack.isEmpty() && array[stack.peekLast()] <= curr) {
				stack.pollLast();
			}
			stack.offerLast(i);
			if (i >= k && stack.peekFirst() == i - k) {
				stack.pollFirst();
			}
			if (i >= k - 1) {
				ans[i - k + 1] = array[stack.peekFirst()];
			}
		}
		return ans;
	}
}
