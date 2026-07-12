class Solution {
	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		return isBalanced(root.left) && isBalanced(root.right) && Math.abs(leftHeight - rightHeight) <= 1;
	}
	
	private int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}
}

// Optimized
class Solution {
	public boolean isBalanced(TreeNode root) {
		return dfs(root)[0] == 1;
	}

	private int[] dfs(TreeNode root) {
		if (root == null) {
			return new int[]{1, 0};
		}
		int[] left = dfs(root.left);
		int[] right = dfs(root.right);
		boolean balanced = left[0] == 1 && right[0] == 1 && Math.abs(left[1] - right[1]) <= 1;
		int height = Math.max(left[1], right[1]) + 1;
		return new int[]{balanced ? 1 : 0, height};
	}
}
