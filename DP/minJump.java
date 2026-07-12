/* Given an array, each element represents the max jump length
 * at that position.
 * Return the min number of jumps needed to reach the end.
 */
public int minJump(int[] array) {
	int n = array.length;
	// dp[i]: the min number of jumps needed to reach i
	int[] dp = new int[n];
	for (int i = 1; i < n; i++) {
		dp[i] = -1;
		for (int j = 0; j < i; j++) {
			if (j + array[j] >= i) {
				dp[i] = dp[j] + 1;
				break;
			}
		}
		if (dp[i] == -1) {
			return -1;
		}
	}
	return dp[n - 1];
}
