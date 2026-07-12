/* Given a non-negative integer array representing the heights 
 * of a list of adjacent bars. Suppose each bar has a width of 1. 
 * Find the largest amount of water that can be trapped in the histogram.
 * Assumptions: The given array is not null.
 */
public class Solution {
	public int maxTrapped(int[] array) {
		// how much water we can store at index i:
		// min(leftHighest,rightHighest) - array[i]
		// highest is included (count array[i])
		int n = array.length;
		int i = 0, j = n - 1;
		int leftMax = array[i], rightMax = array[j];
		int ans = 0;
		while (i <= j) {
			leftMax = Math.max(leftMax, array[i]);
			rightMax = Math.max(rightMax, array[j]);
			if (leftMax < rightMax) {
				ans += leftMax - array[i++];
			} else {
				ans += rightMax - array[j--];
			}
		}
		return ans;
	}
}
