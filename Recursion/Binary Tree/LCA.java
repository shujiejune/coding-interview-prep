/* Find the lowest common ancestor.
 * Assumption: a and b must be both in the tree.
 */
public TreeNode LCA(TreeNode root, int a, int b) {
	if (root == null) {
		return null;
	}
	if (root.key == a || root.key == b) {
		return root;
	}
	TreeNode left = LCA(root.left, a, b);
	TreeNode right = LCA(root.right, a, b);
	if (left != null && right != null) {
		return root;
	} else if (left != null) {
		return left;
	} else if (right != null) {
		return right;
	} else {
		return null;
	}
}
