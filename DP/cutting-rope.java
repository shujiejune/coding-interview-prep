/* Given a rope with integer-length n, how to cut the rope
 * into m integer-length segments with length p[0], ..., p[m - 1],
 * in order to get the maximal product of p[0]*...*p[m - 1]?
 * m is determined by you and must be > 0.
 */
pbulic int getMaxProduct(int n) {
	if (n < 1) {
		return 0;
	}
	// dp[i] is the max prod of cutting rope of length i
	int[] dp = new int[n + 1];
	dp[1] = 1;
	for (int i = 2; i <= n; i++) {
		int maxProd = 0;
		for (int j = 1; j < i; j++) {
			maxProd = Math.max(maxProd, dp[j] * dp[i - j]);
		}
		maxProd = Math.max(maxProd, i);
		dp[i] = maxProd;
	}
	return dp[n];
}
