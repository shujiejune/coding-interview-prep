/* Given an array of integers nums, return the length of the 
 * longest consecutive sequence of elements that can be formed.
 * A consecutive sequence is a sequence of elements in which 
 * each element is exactly 1 greater than the previous element. 
 * The elements do not have to be consecutive in the original array.
 * You must write an algorithm that runs in O(n) time.
 */
class Solution {
	public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> parents = new HashMap<>();
		Set<Integer> keys = new HashSet<>();
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			parents.put(nums[i], i);
			keys.add(nums[i]);
		}
		for (int key : keys) {
			if (keys.contains(key + 1)) {
				parents.put(key + 1, parents.get(key));
			}
		}
		Map<Integer, Integer> counts = new HashMap<>();
		for (int key : keys) {
			int p = findParent(key, parents, nums);
			counts.put(p, counts.getOrDefault(p, 0) + 1);
		}
		int len = 0;
		for (int p : counts.keySet()) {
			len = Math.max(len, counts.get(p));
		}
		return len;
	}

	private int findParent(int key, Map<Integer, Integer> parents, int[] nums) {
		while (nums[parents.get(key)] != key) {
			key = nums[parents.get(key)];
		}
		return key;
	}
}
