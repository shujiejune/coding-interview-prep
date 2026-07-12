class Solution {
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode leftInvert = invertTree(root.left);
		TreeNode rightInvert = invertTree(root.right);
		root.left = rightInvert;
		root.right = leftInvert;
		return root;
	}
}
