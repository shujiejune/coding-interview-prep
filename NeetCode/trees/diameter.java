class Solution {
	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		int rootZigZag = leftDepth + rightDepth;
		int leftDiameter = diameterOfBinaryTree(root.left);
		int rightDiameter = diameterOfBinaryTree(root.right);
		int childDiameter = Math.max(leftDiameter, rightDiameter);
		return Math.max(rootZigZag, childDiameter);
	}

	private int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		return Math.max(leftDepth, rightDepth) + 1;
	}
}
