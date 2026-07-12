/* There is a wooden stick with length L >= 1, we need to cut it into pieces, 
* where the cutting positions are defined in an int array A. The positions are 
* guaranteed to be in ascending order in the range of [1, L - 1]. The cost of 
* each cut is the length of the stick segment being cut. Determine the minimum 
* total cost to cut the stick into the defined pieces.
*/
public class Solution {
	public int minCost(int[] cuts, int length) {
		int n = cuts.length;
		// dp[i][j]: the min cost to cut the stick between [i, j]-th position
		// 0 is left end, n+1 is right end
		// loop through k in (i, j)
		// if i + 1 = j: dp[i][j] = 0
		// if i + 1 < j: dp[i][j] = min(dp[i][k] + dp[k][j])
		int[] helper = new int[n+2];
		for (int i = 1; i <= n; i++) {
			helper[i] = cuts[i-1];
		}
		helper[n+1] = length;
		int[][] dp = new int[n+2][n+2];
		for (int j = 1; j <= n + 1; j++) {
			for (int i = j - 1; i >= 0; i--) {
				if (i+1 == j) { continue; }
				int minCost = Integer.MAX_VALUE;
				for (int k = i+1; k < j; k++) {
					minCost = Math.min(minCost, dp[i][k] + dp[k][j] + helper[j] - helper[i]);
				}
				dp[i][j] = minCost;
			}
		}
		return dp[0][n+1];
	}
}

public class Solution {
	public int minCost(int[] cuts, int length) {
		int n = cuts.length;
		int[] helper = new int[n + 2];
		for (int i = 1; i <= n; i++) {
			helper[i] = cuts[i - 1];
		}
		helper[n + 1] = length;
		int[][] dp = new int[n + 2][n + 2];
		for (int j = 1; j <= n + 1; j++) {
			for (int i = j + 1; i >= 0; i--) {}
		}
	}
}
