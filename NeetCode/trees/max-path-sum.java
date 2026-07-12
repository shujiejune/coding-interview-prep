class Solution {
	public int maxPathSum(TreeNode root) {
		int[] globalMax = new int[]{Integer.MIN_VALUE};
		maxSinglePathSum(root, globalMax);
		return globalMax[0];
	}

	private int maxSinglePathSum(TreeNode root, int[] globalMax) {
		if (root == null) {
			return 0;
		}
		int left = maxSinglePathSum(root.left, globalMax);
		int right = maxSinglePathSum(root.right, globalMax);
		int currSingleMax = Math.max(Math.max(left, right), 0) + root.val;
		int currMax = Math.max(left, 0) + Math.max(right, 0) + root.val;
		globalMax[0] = Math.max(globalMax[0], currMax);
		return currSingleMax;
	}
}
