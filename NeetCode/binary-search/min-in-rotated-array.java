class Solution {
	public int findMin(int[] nums) {
		int l = 0, r = nums.length - 1;
		int ans = Math.min(nums[l], nums[r]);
		while (l < r - 1) {
			if (nums[l] < nums[r]) {
				ans = Math.min(ans, nums[l]);
				break;
			}
			int mid = l + (r - l) / 2;
			if (nums[mid] > nums[l]) {
				l = mid + 1;
			} else {
				r = mid;
			}
			ans = Math.min(nums[l], nums[r]);
		}
		return ans;
	}
}
