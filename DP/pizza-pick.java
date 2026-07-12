/* There is an array of positive integers, in which each integer 
 * represents a piece of Pizza’s size, you and your friend take turns 
 * to pick pizza from either end of the array.  Your friend follows 
 * a simple strategy: He will always pick the larger one he could get 
 * during his turn. The winner is the one who gets the larger total sum 
 * of all pizza. Return the max amount of pizza you can get.
 *
 * Assumption: If during your friend's turn, the leftmost pizza has 
 * the same size as the rightmost pizza, he will pick the rightmost one.
 */
public class Solution {
	public int canWin(int[] nums) {
		if (nums == null) {
			return 0;
		}
		int n = nums.length;
		// dp[i][j]: the largest total sum of pizza you can pick 
		// between [i, j] (0-index) pizza, assuming you start first
		int[][] dp = new int[n][n];
		for (int j = 0; j < n; j++) {
			for (int i = j; i >= 0; i--) {
				if (i == j) {
					dp[i][j] = nums[i];
				} else if (i + 1 == j) {
					dp[i][j] = Math.max(nums[i], nums[j]);
				} else {
					// if you pick the left end: nums[i], your friend picks from nums[i+1], nums[j]
					// if you pick the right end: nums[j], your friend picks from nums[i], nums[j-1]
					int left = nums[i] + nums[i+1] > nums[j] ? dp[i+2][j] : dp[i+1][j-1];
					int right = nums[j] + nums[i] > nums[j-1] ? dp[i+1][j-1] : dp[i][j-2];
					dp[i][j] = Math.max(left, right);
				}
			}
		}
		return dp[0][n - 1];
	}
}
