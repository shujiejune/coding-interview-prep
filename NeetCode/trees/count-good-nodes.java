class Solution {
	public int goodNodes(TreeNode root) {
		if (root == null) return 0;
		Queue<TreeNode> nodes = new LinkedList<>();
		Queue<Integer> upper = new LinkedList<>();
		nodes.offer(root);
		upper.offer(root.val);
		int count = 0;
		while (!nodes.isEmpty()) {
			TreeNode curr = nodes.poll();
			int top = upper.poll();
			if (curr.val >= top) {
				count++;
			}
			if (curr.left != null) {
				nodes.offer(curr.left);
				upper.offer(Math.max(top, curr.left.val));
			}
			if (curr.right != null) {
				nodes.offer(curr.right);
				upper.offer(Math.max(top, curr.right.val));
			}
		}
		return count;
	}
}
