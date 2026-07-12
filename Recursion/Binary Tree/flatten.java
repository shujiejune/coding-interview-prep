/* Given a binary tree, flatten it into a linked list in-place. */
public TreeNode flatten(TreeNode root) {
	if (root == null) {
		return ans;
	}
	TreeNode left = flatten(root.left);
	TreeNode right = flatten(root.right);
	root.right = right;
	if (left == null) {
		root.left = null;
		return root;
	} else {
		TreeNode curr = left;
		while (curr.right != null) {
			curr = curr.right;
		}
		curr.right = right;
		root.right = left;
		root.left = null;
		return left;
	}
}

/* DFS solution */
public TreeNode flatten(TreeNode root) {
	TreeNode[] prev = new TreeNode[1];
	dfs(root, prev);
	return root;
}

private void dfs(TreeNode root, TreeNode[] prev) {
	if (root == null) {
		return;
	}
	TreeNode left = root.left;
	TreeNode right = root.right;
	if (prev[0] != null) {
		prev[0].right = root;
	}
	root.left = null;
	prev[0] = root;
	dfs(left, prev);
	dfs(right, prev);
}
