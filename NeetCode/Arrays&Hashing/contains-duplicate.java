/* Given an integer array nums, return true if any value appears 
 * more than once in the array, otherwise return false. */
class Solution {
	public boolean hasDuplicate(int[] nums) {
		Set<Integer> exists = new HashSet<>();
		for (int num : nums) {
			if (exists.contains(num)) {
				return true;
			} else {
				exists.add(num);
			}
		}
		return false;
	}
}
