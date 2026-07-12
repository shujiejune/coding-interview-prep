/* Given an m * n matrix of non-negative integers representing 
 * the height of each unit cell in a continent, the "Pacific ocean" 
 * touches the left and top edges of the matrix and the "Atlantic ocean" 
 * touches the right and bottom edges.
 * Water can only flow in four directions (up, down, left, or right) 
 * from a cell to another one with height equal or lower.
 * Find the list of grid coordinates where water can flow to both the
 * Pacific and Atlantic ocean. The order of returned grid coordinates 
 * does not matter.
 */
public class Solution {
	private static final int Pacific = 1;
	private static final int Atlantic = 2;
	private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		List<List<Integer>> ans = new ArrayList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return ans;
		}
		int m = matrix.length, n = matrix[0].length;
		int[][] p = new int[m][n];
		int[][] a = new int[m][n];
		Queue<int[]> pq = new ArrayDeque<>();
		Queue<int[]> aq = new ArrayDeque<>();
		for (int i = 0; i < m; i++) {
			pq.offerFirst(new int[]{i, 0});
			aq.offerFirst(new int[]{i, n - 1});
			p[i][0] = Pacific;
			a[i][n - 1] = Atlantic;
		}
		for (int j = 0; j < n; j++) {
			pq.offerFirst(new int[]{0, j});
			aq.offerFirst(new int[]{m - 1, j});
			p[0][j] = Pacific;
			a[m - 1][j] = Atlantic;
		}
		bfs(pq, p, matrix, Pacific);
		bfs(aq, a, matrix, Atlantic);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (p[i][j] == Pacific && a[i][j] == Atlantic) {
					ans.add(Arrays.asList(i, j));
				}
			}
		}
		return ans;
	}

	private void bfs(Queue<int[]> q, int[][] ocean, int[][] matrix, int val) {
		int m = matrix.length, n = matrix[0].length;
		while (!q.isEmpty()) {
			int[] curr = q.pollFirst();
			for (int[] d : directions) {
				int x = curr[0] + d[0];
				int y = curr[1] + d[1];
				if (isValid(x, y, m, n) && ocean[x][y] == 0 && matrix[x][y] >= matrix[curr[0]][curr[1]]) {
					ocean[x][y] = val;
					q.offerFirst(new int[]{x, y});
				}
			}
		}
	}

	private boolean isValid(int x, int y, int m, int n) {
		return x >= 0 && x < m && y >= 0 && y < n;
	}
}

/* TC: O(m * n)
 * SC: O(m * n)
 */
