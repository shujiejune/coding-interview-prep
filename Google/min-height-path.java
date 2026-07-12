/* Given an integer matrix with m rows and n cols, matrix[i][j] 
 * stands for the height of the bar. Find a path from the top-left 
 * to bottom-right, i.e. (0, 0) to (m-1, n-1), that minimizes the 
 * height of the highest bar on the path.
 */
public class Solution {
	public List<int[]> minHeightPath(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		// minHeight[i][j]: the min height of the highest bar from (0, 0) to (i, j)
		int[][] minHeight = new int[m][n];
		for (int[] row : minHeight) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		// pq for int[3]{row, col, minHeight[row][col]}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		minHeight[0][0] = matrix[0][0];
		pq.offer(new int[]{0, 0, matrix[0][0]});
		int[][][] parent = new int[m][n][2];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				parent[i][j][0] = -1;
				parent[i][j][1] = -1;
			}
		}
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			if (curr[0] == m - 1 && curr[1] == n - 1) {
				break;
			}
			if (curr[2] > minHeight[curr[0]][curr[1]]) { continue; }
			for (int[] d : directions) {
				int x = curr[0] + d[0];
				int y = curr[1] + d[1];
				if (x >= 0 && x < m && y >= 0 && y < n) {
					int nextMinHeight = Math.max(curr[2], matrix[x][y]);
					if (nextMinHeight < minHeight[x][y]) {
						minHeight[x][y] = nextMinHeight;
						pq.offer(new int[]{x, y, nextMinHeight});
						parent[x][y][0] = curr[0];
						parent[x][y][1] = curr[1];
					}
				}
			}
		}
		// construct path
		List<int[]> ans = new ArrayList<>();
		int currRow = m - 1, currCol = n - 1;
		while (currRow != -1 && currCol != -1) {
			ans.add(new int[]{currRow, currCol});
			int prevRow = parent[currRow][currCol][0];
			int prevCol = parent[currRow][currCol][1];
			currRow = prevRow;
			currCol = prevCol;
		}
		Collections.reverse(ans);
		return ans;
	}
}
