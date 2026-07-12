class Solution {
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length, n = matrix[0].length;
		int l = 0, r = m * n - 1;
		while (l < r - 1) {
			int mid = l + (r - l) / 2;
			int row = mid / n, col = mid % n;
			if (matrix[row][col] == target) {
				return true;
			} else if (matrix[row][col] < target) {
				l = mid;
			} else {
				r = mid;
			}
		}
		if (matrix[l / n][l % n] == target || matrix[r / n][r % n] == target) {
			return true;
		}
		return false;
	}
}
