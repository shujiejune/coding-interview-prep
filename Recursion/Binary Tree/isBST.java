/* How to determine if a tree is BST*/
public boolean isBST(TreeNode root) {
	if (root == null) {
		return false;
	}
	int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
	return helper(root, min, max);
}

private boolean helper(TreeNode root, int min, int max) {
	if (root == null) {
		return true;
	}
	if (root.key <= min || root.key >= max) {
		return false;
	}
	return helper(root.left, min, root.key) && helper(root.right, root.key, max);
}
