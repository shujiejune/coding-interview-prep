/* Given an array of integers nums and an integer target, 
* return the indices i and j such that nums[i] + nums[j] == target and i != j.
* You may assume that every input has exactly one pair of 
* indices i and j that satisfy the condition.
* Return the answer with the smaller index first.
*/
class Solution {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, List<Integer>> indices = new HashMap<>();
		int i = 0, j = nums.length - 1;
		for (int k = 0; k <= j; k++) {
			if (!indices.containsKey(nums[k])) {
				indices.put(nums[k], new ArrayList<>());
			}
			indices.get(nums[k]).add(k);
		}
		Arrays.sort(nums);
		while (i < j) {
			if (nums[i] + nums[j] < target) {
				i++;
			} else if (nums[i] + nums[j] > target) {
				j--;
			} else {
				int idx1 = indices.get(nums[i]).get(0);
				int idx2 = indices.get(nums[j]).get(0);
				if (idx1 == idx2) {
					idx2 = indices.get(nums[j]).get(1);
				}
				if (idx1 > idx2) {
					return new int[]{idx2, idx1};
				}
				return new int[]{idx1, idx2};
			}
		}
		return new int[]{-1, -1};
	}
}
