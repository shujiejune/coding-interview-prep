class Solution {
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			List<Integer> level = new ArrayList<>();
			int n = q.size();
			for (int i = 0; i < n; i++) {
				TreeNode curr = q.poll();
				level.add(curr.val);
				if (curr.left != null) q.offer(curr.left);
				if (curr.right != null) q.offer(curr.right);
			}
			ans.add(level.get(level.size() - 1));
		}
		return ans;	
	}
}

// DFS solution
class Solution {
	List<Integer> ans = new ArrayList<>();

	public List<Integer> rightSideView(TreeNode root) {
		dfs(root, 0);
		return ans;
	}

	private void dfs(TreeNode root, int depth) {
		if (root == null) return;
		if (ans.size() == depth) {
			return root.val;
		}
		dfs(root.right, depth + 1);
		dfs(root.left, depth + 1);
	}
}
