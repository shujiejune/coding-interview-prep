/* Given an integer array nums, return an array output where 
* output[i] is the product of all the elements of nums except nums[i].
* Each product is guaranteed to fit in a 32-bit integer.
* Follow-up: Could you solve it in O(n) time without using the division operation?
*/
class Solution {
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] prodFromLeft = new int[n];
		int[] prodFromRight = new int[n];
		prodFromLeft[0] = 1;
		prodFromRight[n - 1] = 1;
		for (int i = 1; i < n; i++) {
			prodFromLeft[i] = prodFromLeft[i - 1] * nums[i - 1];
		}
		for (int i = n - 2; i >= 0; i--) {
			prodFromRight[i] = prodFromRight[i + 1] * nums[i + 1];
		}
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = prodFromLeft[i] * prodFromRight[i];
		}
		return ans;
	}
}
