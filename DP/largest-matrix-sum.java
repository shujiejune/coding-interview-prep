/* Given a matrix of integers (containing positive, negative, and zero),
 * find the submatrix with the largest sum.
 */
public int largest(int[][] matrix) {
	if (matrix == null || matrix.length == 0 || matrix[0].length) {
		return 0;
	}
	int m = matrix.length, n = matrix[0].length;
	int[][] prefixSum = new int[m + 1][n];
	for (int j = 0; j < n; j++) {
		for (int i = 1; i <= m; i++) {
			prefixSum[i][j] = prefixSum[i-1][j] + matrix[i-1][j];
		}
	}
	int globalMax = matrix[0][0];
	// compute submatrix sum of rows [top, bottom]
	for (int top = 1; top <= m; top++) {
		for (int bottom = top; bottom <= m; bottom++) {
			int[] verticalSum = new int[n];
			for (int j = 0; j < n; j++) {
				verticalSum[j] = prefixSum[bottom][j] - prefixSum[top-1][j];
			}
			int[] dp = new int[n];
			// dp[i]: the max sum of verticalSum's subarray ending at index i
			dp[0] = verticalSum[0];
			for (int i = 1; i < n; i++) {
				dp[i] = Math.max(dp[i-1], 0) + verticalSum[i];
				globalMax = Math.max(globalMax, dp[i]);
			}
		}
	}
	return globalMax;
}
