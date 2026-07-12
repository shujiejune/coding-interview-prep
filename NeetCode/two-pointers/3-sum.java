class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		int n = nums.length;
		for (int i = 0; i < n - 2; i++) {
			if (i > 0 && nums[i] == nums[i-1]) {
				continue;
			}
			int j = i + 1, k = n - 1;
			int target = 0 - nums[i];
			while (j < k) {
				if (nums[j] + nums[k] == target) {
					ans.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[k]}));
					j++;
					k--;
					while (j < k && nums[j] == nums[j-1]) {
						j++;
					}
					while (j < k && nums[k] == nums[k+1]) {
						k--;
					}
				} else if (nums[j] + nums[k] < target) {
					j++;
				} else {
					k--;
				}
			}
		}
		return ans;
	}
}
