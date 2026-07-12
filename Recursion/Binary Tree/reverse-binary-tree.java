/* Given a binary tree where all the right nodes are leaves,
 * flip it upside down and turn it into a tree with left leaves.
 */
public TreeNode reverse(TreeNode root) {
	if (root == null || root.left == null) {
		return root;
	}
	TreeNode newRoot = reverse(root.left);
	root.left.left = root.right;
	root.left.right = root;
	root.left = null;
	root.right = null;
	return newRoot;
}
