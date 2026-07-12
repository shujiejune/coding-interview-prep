List<int[]> solution(int[][] matrix) {
	List<int[]> ans = new ArrayList<>();
	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
		return ans;
	}
	int m = matrix.length, n = matrix[0].length;
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (matrix[i][j] <= 0) {
				continue;
			}
			int val = matrix[i][j];
			boolean isLocalMax = true;
			for (int row = i - val; row <= i + val; row++) {
				for (int col = j - val; col <= j + val; col++) {
					if (row == i && col == j) {
						continue;
					}
					if (Math.abs(row - i) == val && Math.abs(col - j) == val) {
						continue;
					}
					if (isValid(row, col, m, n)) {
						if (matrix[row][col] >= val) {
							isLocalMax = false;
						}
					}
				}
			}
			if (isLocalMax) {
				ans.add(new int[]{i, j});
			}
		}
	}
	return ans;
}
