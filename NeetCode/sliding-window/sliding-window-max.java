class Solution {
	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		int[] ans = new int[n - k + 1];
		Deque<Integer> monostack = new ArrayDeque<>();
		int i = 0;
		for (int j = 0; j < nums.length; j++) {
			while (!monostack.isEmpty() && nums[monostack.peekLast()] < nums[j]) {
				monostack.pollLast();
			}
			monostack.offerLast(j);
			if (i > monostack.peekFirst()) {
				monostack.pollFirst();
			}
			if (j + 1 >= k) {
				ans[i++] = nums[monostack.peekFirst()];
			}
		}
		return ans;
	}
}
