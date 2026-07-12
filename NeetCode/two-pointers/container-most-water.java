class Solution {
	public int maxArea(int[] heights) {
		int l = 0, r = heights.length - 1;
		int leftMax = heights[l], rightMax = heights[r];
		int ans = 0;
		while (l < r) {
			leftMax = Math.max(leftMax, heights[l]);
			rightMax = Math.max(rightMax, heights[r]);
			int h = Math.min(leftMax, rightMax);
			int area = h * (r - l);
			ans = Math.max(ans, area);
			if (leftMax < rightMax) {
				l++;
			} else {
				r--;
			}
		}
		return ans;
	}
}
