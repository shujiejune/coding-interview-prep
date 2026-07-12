/* Given an array of process loads, distribute them onto 
 * two servers such that the absolute difference between 
 * the total loads of the two servers is minimized.
 */
public int minDiff(int[] loads) {
	if (loads == null || loads.length == 0) {
		return 0;
	}
	int totalSum = 0;
	for (int load : loads) {
		totalSum += load;
	}
	int target = totalSum / 2;
	// dp[i]: if a sum of i is achievable
	boolean[] dp = new boolean[target + 1];
	dp[0] = true;
	for (int load : loads) {
		for (int j = target; j >= load; j--) {
			dp[j] = dp[j] || dp[j - load];
		}
	}
	int closest = 0;
	for (int i = target; i >= 0; i--) {
		if (dp[i]) {
			closest = i;
			break;
		}
	}
	return totalSum - 2 * closest;
}
