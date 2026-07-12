/* print BST keys in the given range */
public List<Integer> printBST(TreeNode root, int left, int right) {
	List<Integer> ans = new ArrayList<>();
	if (root == null) {
		return ans;
	}
	helper(root, left, right, ans);
	return ans;
}

private void helper(TreeNode root, int left, int right, List<Integer> ans) {
	if (root == null) {
		return;
	}
	if (root.key < left || root.key > right) {
		return;
	}
	helper(root.left, left, right, ans);
	if (root.key >= left && root.key <= right) {
		ans.add(root.key);
	}
	helper(root.right, left, right, ans);
}
