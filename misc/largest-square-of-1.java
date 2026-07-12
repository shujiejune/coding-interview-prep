/* Determine the largest rectangle of 1s in a binary matrix 
 * (a binary matrix only contains 0 and 1), return the area.
 * Assumptions:
 * The given matrix is not null and has size of M * N, M >= 0 and N >= 0
 */
public class Solution {
	public int largest(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int[][] prefix = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0) {
					prefix[i][j] = matrix[i][j];
				} else {
					if (matrix[i][j] == 1) {
						prefix[i][j] = prefix[i - 1][j] + 1;
					}
				}
			}
		}
		int globalMax = 0;
		for (int i = 0; i < m; i++) {
			int maxAreaInCurrRow = largestInHistogram(prefix[i]);
			globalMax = Math.max(globalMax, maxAreaInCurrRow);
		}
		return globalMax;
	}

	private int largestInHistogram(int[] array) {
		int n = array.length;
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return array[0];
		}
		Deque<Integer> monostack = new ArrayDeque<>();
		monostack.offerLast(0);
		int maxArea = 0;
		for (int j = 1; j <= n; j++) {
			int rightBoundHeight = j == n ? 0 : array[j];
			while (!monostack.isEmpty() && array[monostack.peekLast()] >= rightBoundHeight) {
				int height = array[monostack.pollLast()];
				int width = monostack.isEmpty() ? j : j - monostack.peekLast() - 1;
				maxArea = Math.max(maxArea, height * width);
			}
			monostack.offerLast(j);
		}
		return maxArea;
	}
}
