class Solution {
	public int search(int[] nums, int target) {
		int k = findMin(nums);
		int n = nums.length;
		int l = 0, r = n - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			int mid = (m + k) % n;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return -1;
	}

	private int findMin(int[] nums) {
		int l = 0, r = nums.length - 1;
		int ans = nums[l] < nums[r] ? l : r;
		while (l < r - 1) {
			if (nums[l] < nums[r]) { break; }
			int mid = l + (r - l) / 2;
			if (nums[mid] > nums[l]) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		ans = nums[l] < nums[r] ? l : r;
		return ans;
	}
}
