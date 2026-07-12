/* Given a matrix of points. Each point has one of the following values:
 * 0 - there is no match to its right or bottom
 * 1 - there is a match to its right
 * 2 - there is a match to its bottom
 * 3 - there is a match to its right, and a match to its bottom
 * Return the length of the largest square surrounded by matches.
 */
public int largest(int[][] matrix) {
	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
		return 0;
	}
	int m = matrix.length, n = matrix[0].length;
	// rightDP[i][j]: the largest consecutive number of matches to the righthand of (i, j)
	int[][] rightDP = new int[m][n];
	// downDP[i][j]: the largest consecutive number of matches to the downside of (i, j)
	int[][] downDP = new int[m][n];
	for (int i = m - 1; i >= 0; i--) {
		for (int j = n - 1; j >= 0; j--) {
			if (matrix[i][j] == 1 || matrix[i][j] == 3) {
				if (j < n - 1) {
					rightDP[i][j] = rightDP[i][j + 1] + 1;
				}
			}
			if (matrix[i][j] == 2 || matrix[i][j] == 3) {
				if (i < m - 1) {
					downDP[i][j] = downDP[i + 1][j] + 1;
				}
			}
		}
	}
	int globalMax = 0;
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			int maxLen = Math.min(rightDP[i][j], downDP[i][j]);
			for (int k = maxLen; k > globalMax; k--) {
				boolean rightEdgeValid = downDP[i][j + k] >= k;
				boolean downEdgeValid = rightDP[i + k][j] >= k;
				if (rightEdgeValid && downEdgeValid) {
					globalMax = k;
					break;
				}
			}
		}
	}
	return globalMax;
}
