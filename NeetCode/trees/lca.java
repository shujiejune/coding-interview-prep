class Solution {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root.key == p.key || root.key == q.key) {
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (left != null && right == null) {
			return left;
		} else if (left == null && right != null) {
			return right;
		} else if (left != null && right != null) {
			return root;
		} else {
			return null;
		}
	}
}
