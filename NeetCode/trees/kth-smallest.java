class Solution {
	List<Integer> inorder = new ArrayList<>();

	public int kthSmallest(TreeNode root, int k) {
		inorderTraversal(root);
		return inorder.get(k - 1);
	}

	private void inorderTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		inorderTraversal(root.left);
		inorder.add(root.val);
		inorderTraversal(root.right);
	}
}

class Solution {
	public int kthSmallest(TreeNode root, int k) {
		int[] ans = new int[2]; // {count, kthSmallest}
		inorder(root, ans, k);
		return ans[1];
	}

	private void inorder(TreeNode root, int[] ans, int k) {
		if (root == null) {
			return;
		}
		inorder(root.left, ans, k);
		if (ans[0] == k) { // cut the traversal
			return;
		}
		ans[0]++;
		ans[1] = root.val;
		if (ans[0] == k) {
			return;
		}
		inorder(root.right, ans, k);
	}
}
