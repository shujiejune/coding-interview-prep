/* Given 2 strings of alphanumeric characters, determine the
 * minimum number of replace, insert, delete operations needed
 * to transform one string into the other. 
 * Assumption: both strings are not null
 */
public int editDistance(String one, String two) {
	int m = one.length(), n = two.length();
	// dp[i][j]: the min operations to transform one.substring(0, i) to two.substring(0, j)
	int[][] dp = new int[m + 1][n + 1];
	for (int i = 0; i <= m; i++) {
		for (int j = 0; j <= n; j++) {
			if (i == 0) {
				dp[i][j] = j;
				continue;
			}
			if (j == 0) {
				dp[i][j] = i;
				continue;
			}
			if (one.charAt(i - 1) != two.charAt(j - 1)) {
				dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
			} else {
				dp[i][j] = dp[i-1][j-1];
			}
		}
	}
	return dp[m][n];
}
