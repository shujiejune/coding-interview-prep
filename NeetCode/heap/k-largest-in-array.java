class Solution {
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (int num : nums) {
			if (minHeap.size() < k) {
				minHeap.offer(num);
			} else {
				if (minHeap.peek() < num) {
					minHeap.poll();
					minHeap.offer(num);
				}
			}
		}
		return minHeap.peek();
	}
}

// Quick Select (Optimal)
class Solution {
	public int findKthLargest(int[] nums, int k) {
		return quickSelect(nums, k - 1);
	}

	private int quickSelect(int[] nums, int k) {
		int left = 0, right = nums.length - 1;
		while (true) {
			if (right <= left + 1) {
				if (right == left + 1 && nums[right] > nums[left]) {
					swap(nums, left, right);
				}
				return nums[k];
			}
			int j = partition(nums, left, right);
			if (j >= k) right = j - 1;
			if (j <= k) left = j + 1;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private int partition(int[] nums, int left, int right) {
		int mid = left + (right - left) / 2;
		swap(nums, mid, left + 1);

		if (nums[left] < nums[right]) {
			swap(nums, left, right);
		}
		if (nums[left + 1] < nums[right]) {
			swap(nums, left + 1, right);
		}
		if (nums[left] < nums[left + 1]) {
			swap(nums, left, left + 1);
		}

		int pivot = nums[left + 1];
		int i = left + 1, j = right;

		while (true) {
			while (nums[++i] > pivot);
			while (nums[--j] < pivot);
			if (i > j) break;
			swap(nums, i, j);
		}

		nums[left + 1] = nums[j];
		nums[j] = pivot;
		return j;
	}
}
