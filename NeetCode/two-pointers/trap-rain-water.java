class Solution {
	public int trap(int[] height) {
		int l = 0, r = height.length - 1;
		int leftMax = height[l], rightMax = height[r];
		int sum = 0;
		while (l < r) {
			leftMax = Math.max(leftMax, height[l]);
			rightMax = Math.max(rightMax, height[r]);
			int h = Math.min(leftMax, rightMax);
			if (leftMax < rightMax) {
				if (height[l] < h) {
					sum += h - height[l];
				}
				l++;
			} else {
				if (height[r] < h) {
					sum += h - height[r];
				}
				r--;
			}
		}
		return sum;
	}
}
