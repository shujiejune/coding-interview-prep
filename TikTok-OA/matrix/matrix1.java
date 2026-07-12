char[][] solution(char[][] matrix) {
	int m = matrix.length, n = matrix[0].length;
	int minFall = m;
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (matrix[i][j] == 'F') {
				for (int k = i + 1; k < m; k++) {
					if (matrix[k][j] == '#') {
						int fall = k - 1 - i;
						minFall = Math.min(minFall, fall);
						break;
					}
					if (k == m - 1) {
						minFall = Math.min(minFall, m - 1 - i);
					}
				}
			}
		}
	}
	if (minFall == 0) {
		return matrix;
	}
	char[][] ans = new char[m][n];
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			ans[i][j] = '-';
		}
	}
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (matrix[i][j] == '#') {
				ans[i][j] = '#';
			}
			if (matrix[i][j] == 'F') {
				ans[i + minFall][j] = 'F';
			}
		}
	}
	return ans;
}
