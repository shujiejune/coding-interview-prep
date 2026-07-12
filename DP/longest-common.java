/* Find the longest common substring of two given strings.
 * Assumption: The two strings are not null.
 */
public class Solution {
	public String longestCommon(String source, String target) {
		int m = source.length(), n = target.length();
		if (m == 0 || n == 0) {
			return 0;
		}
		int globalMax = 0;
		// dp[i][j]: length of the longest common substring between source[:i] and target[:j]
		// exclude source[i] and target[j]
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) { continue; }
				if (source.charAt(i - 1) == target.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					globalMax = Math.max(globalMax, dp[i][j]);
				}
			}
		}
		return globalMax;
	}
}


/* longest common subsequence */
public class Solution {
	public int longest(String source, String target) {
		int m = source.length();
		int n = target.length();
		if (m == 0 || n == 0) {
			return 0;
		}
		// dp[i][j]: length of longest common subsequence between source[:i] and target[:j]
		// exclude source[i] and target[j]
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) { continue; }
				if (source.charAt(i - 1) == target.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		return dp[m - 1][n - 1];
	}
}
