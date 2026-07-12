/* Return the edge length of the largest square of 1 in a given matrix. */
public int largestSquare(int[][] matrix) {
	if (matrix == null || matrix.length == 0) {
		return 0;
	}
	int n = matrix.length;
	int[][] dp = new int[n][n];
	for (int i = 0; i < n; i++) {
		dp[i][0] = matrix[i][0] == 1 ? 1 : 0;
		for (int j = 1; j < n; j++) {
			if (matrix[i][j] == 0) {
				dp[i][j] = 0;
			} else {
				dp[i][j] = dp[i][j-1] + 1;
			}
		}
	}
	int globalMax = 0;
	for (int j = n - 1; j >= 0; j--) {
		Deque<Integer> stack = new ArrayDeque<>();
		int maxLen = 0;
		for (int i = 0; i <= n; i++) {
			int h = i == n ? 0 : dp[i][j];
			while (!stack.isEmpty() && h < dp[stack.peekFirst()][j]) {
				int height = dp[stack.pollFirst()][j];
				int width = stack.isEmpty() ? i : i - stack.peekFirst() - 1;
				int len = Math.min(height, width);
				maxLen = Math.max(maxLen, len);
			}
			stack.offerFirst(i);
		}
		globalMax = Math.max(globalMax, maxLen);
	}
	return globalMax;
}
/*
 * TC: O(n^2)
 * SC: O(n^2)
 */

/* pure dp */
public int largestSquare(int[][] matrix) {
	if (matrix == null || matrix.length == 0) {
		return 0;
	}
	int n = matrix.length;
	// dp[i][j]: the edge length of the largest square of 1s with the bottom-right corner at (i, j)
	int[][] dp = new int[n][n];
	for (int i = 0; i < n; i++) {
		dp[i][0] = matrix[i][0] == 1 ? 1 : 0;
	}
	for (int j = 1; j < n; j++) {
		dp[0][j] = matrix[0][j] == 1 ? 1 : 0;
	}
	int globalMax = 0;
	for (int i = 1; i < n; i++) {
		for (int j = 1; j < n; j++) {
			if (matrix[i][j] == 1) {
				dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
				globalMax = Math.max(globalMax, dp[i][j]);
			}
		}
	}
	return 
}
