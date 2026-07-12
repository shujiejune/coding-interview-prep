/* Return the right view of the given binary tree, from top to bottom. */
public List<Integer> rightView(TreeNode root) {
	List<Integer> ans = new ArrayList<>();
	if (root == null) {
		return null;
	}
	Queue<TreeNode> q = new ArrayDeque<>();
	q.offer(root);
	while (!q.isEmpty()) {
		int sz = q.size();
		for (int i = 0; i < sz; i++) {
			TreeNode curr = q.poll();
			if (i == sz - 1) {
				ans.add(curr.key);
			}
			if (curr.left != null) {
				q.offer(curr.left);
			}
			if (curr.right != null) {
				q.offer(curr.right);
			}
		}
	}
	return ans;
}
