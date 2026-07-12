int[] solution(int[][] matrix) {
	int m = matrix.length, n = matrix[0].length;
	int[] ans = new int[2];
	long[] averages = new long[4];
	int minDiff = Integer.MAX_VALUE;
	for (int row = 0; row < m - 1; row++) {
		for (int col = 0; col < n - 1; col++) {
			averages[0] = average(matrix, 0, row + 1, 0, col + 1);
			averages[1] = average(matrix, 0, row + 1, col + 1, n);
			averages[2] = average(matrix, row + 1, m, 0, col + 1);
			averages[3] = average(matrix, row + 1, m, col + 1, n);
			boolean isValid = true;
			for (int i = 0; i < 4; i++) {
				if (averages[i] < 0) {
					isValid = false;
				}
			}
			if (!isValid) { continue; }
			int diff = getDiff(averages);
			if (diff < minDiff) {
				ans[0] = row;
				ans[1] = col;
				minDiff = diff;
			}
		}
	}
	return ans;
}

int average(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd) {
	long sum = 0;
	int count = 0;
	for (int i = rowStart; i < rowEnd; i++) {
		for (int j = colStart; j < colEnd; j++) {
			if (matrix[i][j] >= 0) {
				sum += matrix[i][j];
				count++;
			}
		}
	}
	if (count == 0) {
		return -1;
	}
	return (int) (sum / count);
}

int getDiff(int[] arr) {
	int minVal = arr[0];
	int maxVal = arr[0];
	for (int ele : arr) {
		minVal = Math.min(minVal, ele);
		maxVal = Math.max(maxVal, ele);
	}
	return maxVal - minVal;
}
