int solution(int[][] matrix, int radius) {
	int m = matrix.length, n = matrix[0].length;
	int maxSum = Integer.MIN_VALUE;
	for (int i = radius - 1; i <= m - radius; i++) {
		for (int j = radius - 1; j <= n - radius; j++) {
			int sum = calcSum(matrix, radius, i, j);
			maxSum = Math.max(maxSum, sum);
		}
	}
	return maxSum;
}

int calcSum(int[][] matrix, int radius, int x, int y) {
	int sum = matrix[x][y];
	for (int i = 1; i < radius; i++) {
		int leftX = x - i, leftY = y;
		int downX = x, downY = y + i;
		for (int j = 0; j <= i; j++) {
			sum += matrix[leftX + j][leftY - j];
			sum += matrix[downX + j][downY - j];
		}
		int upX = x, upY = y - i;
		int rightX = x + i, rightY = y;
		for (int j = 0; j <= i; j++) {
			sum += matrix[upX + j][upY + j];
			sum += matrix[rightX - j][rightY - j];
		}
		sum = sum - matrix[leftX][leftY] - matrix[downX][downY]
		      - matrix[upX][upY] - matrix[rightX][rightY];
	}
	return sum;
}
