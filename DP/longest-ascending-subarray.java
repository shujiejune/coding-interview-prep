/* Given an unsorted array, find the length of the longest subarray
 * in which the numbers are in ascending order.
 */
public int longest(int[] array) {
	if (array == null || array.length == 0) {
		return 0;
	}
	int n = array.length;
	// dp[i] is the len of longest ascending subarr ending at index i
	int[] dp = new int[n];
	// base case
	dp[0] = 1;
	int longest = 0;
	for (int i = 1; i < n; i++) {
		// induction rule
		if (dp[i] > dp[i - 1]) {
			dp[i] = dp[i - 1] + 1;
		} else {
			dp[i] = 1;
		}
		longest = Math.max(longest, dp[i]);
	}
	return longest;
}

// Optimize space complexity to O(1):
// use a single variable to store longest ending at current index
// instead of a dp array


/* longest ascending subsequence 
 * return the subsequence
 */
public class Solution {
	public int[] longest(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int globalMax = 1;
		// dp[i]: length of longest ascending subsequence ending at array[i]
		int[] dp = new int[n];
		int[] prev = new int[n];
		Arrays.fill(dp, 1);
		int longestEndIndex = 0;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i]) {
					if (dp[j] + 1 > dp[i]) {
						prev[i] = j;
						dp[i] = dp[j] + 1;
					}
				}
			}
			if (dp[i] > globalMax) {
				globalMax = dp[i];
				longestEndIndex = i;
			}
		}
		int[] ans = new int[globalMax];
		int index = longestEndIndex;
		for (int i = globalMax - 1; i >= 0; i--) {
			ans[i] = array[index];
			index = prev[index];
		}
		return ans;
	}
}


/* count ascending subseuqences */
public class Solution {
	public int numIncreasingSubsequences(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		int sum = 0;
		int[] dp = new int[n];
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i]) {
					dp[i] += dp[j];
				}
			}
			dp[i]++;
			sum += dp[i];
		}
		return sum;
	}
}


/* Given an array of 2D coordinates of points (all the coordinates are integers), 
 * find the largest number of points that can form a set such that any pair of points 
 * in the set can form a line with positive slope. Return the size of such a maximal set.
 *
 * Assumptions:
 * The given array is not null
 * Note: if there does not even exist 2 points can form a line with positive slope, 
 * should return 0.
 */
public class Solution {
	class Point {
		public int x;
		public int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public int largest(Point[] points) {
		if (points.length < 2) {
			return 0;
		}
		Arrays.sort(points, new Comparator<Point>(){
			@Override
			public int compare(Point p1, Point p2) {
				return p1.x.compareTo(p2.x);
			}
		});
		int globalMax = 0;
		int n = points.length;
		// dp[i]: size of largest y-ascending subsequences of points[:i]
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (points[j].y < points[i].y) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			globalMax = Math.max(globalMax, dp[i]);
		}
		return globalMax > 1 ? globalMax : 0;
	}
}
