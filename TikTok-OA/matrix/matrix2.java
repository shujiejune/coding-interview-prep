int solution(int[][] matrix) {
	if (matrix == null || matrix.length == 0) {
		return 0;
	}
	int n = matrix.length;
	int mid = n / 2;
	// nums on Y and on Bg
	int[] yCount = new int[3];
	int[] bgCount = new int[3];
	int numOfYCells = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			boolean onY = false;
			if (i <= mid) {
				if (j == i || j == n - 1 - i) {
					onY = true;
				}
			} else {
				if (j == mid) {
					onY = true;
				}
			}
			if (onY) {
				yCount[matrix[i][j]]++;
				numOfYCells++;
			} else {
				bgCount[matrix[i][j]]++;
			}
		}
	}
	int numOfBgCells = n * n - numOfYCells;
	int minChanges = n * n;
	for (int y = 0; y <= 2; y++) {
		for (int bg = 0; bg <= 2; bg++) {
			if (y == bg) {
				continue;
			}
			int changes = numOfYCells - yCount[y] + numOfBgCells - bgCount[bg];
			minChanges = Math.min(minChanges, changes);
		}
	}
	return minChanges;
}
