/* You are given an empty 2D binary grid grid of size m x n. 
 * The grid represents a map where 0's represent water and 1's 
 * represent land. Initially, all the cells of grid are water cells 
 * (i.e., all the cells are 0's).
 * We may perform an add land operation which turns the water 
 * at position into a land. You are given an array positions 
 * where positions[i] = [ri, ci] is the position (ri, ci) at which 
 * we should operate the ith operation.
 *
 * Return an array of integers answer where answer[i] is the 
 * number of islands after turning the cell (ri, ci) into a land.
 *
 * An island is surrounded by water and is formed by connecting 
 * adjacent lands horizontally or vertically. You may assume all 
 * four edges of the grid are all surrounded by water.
 */
class Solution {
	private final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> ans = new ArrayList<>();
		if (m <= 0 || n <= 0) {
			return ans;
		}
		int[] roots = new int[m * n];
		int count = 0;
		Arrays.fill(roots, -1);
		for (int[] p : positions) {
			int root = n * p[0] + p[1];
			roots[root] = root;
			count++;
			for (int[] d : directions) {
				int x = p[0] + d[0];
				int y = p[1] + d[1];
				int nb = x * n + y;
				if(isValid(x, y, m, n) && roots[nb] != -1) {
					int rootOfNb = findRoot(roots, nb);
					if (root != rootOfNb) {
						roots[root] = rootOfNb;
						root = rootOfNb;
						count--;
					}
				}
			}
			ans.add(count);
		}
		return ans;
	}

	private int findRoot(int[] roots, int nb) {
		while (roots[nb] != nb) {
			roots[nb] = roots[roots[nb]];
			nb = roots[nb];
		}
		return nb;
	}
	
	private boolean isValid(int x, int y, int m, int n) {
		return x >= 0 && x < m && y >= 0 && y < n;
	}
}
