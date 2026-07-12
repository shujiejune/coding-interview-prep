/* Given an array of non-negative integers, you are initially positioned at the first index.
 * Each element in the array represents the maximum jump length at that position.
 * Determine if you are able to reach the last index.
 */
public boolean jumpGame(int[] array) {
	if (array == null || array.length == 0) {
		return false;
	}
	int n = array.length;
	// dp[i]: the farthest index you can reach from [0, i]
	int[] dp = new int[n];
	// base case
	dp[0] = array[0];
	for (int i = 1; i < n; i++) {
		if (dp[i - 1] < i) {
			return false;
		}
		dp[i] = Math.max(dp[i - 1], i + array[i]);
		if (dp[i] >= n - 1) {
			return true;
		}
	}
	return false;
}
