// dfs
class Solution {
	List<Integer> inorder = new ArrayList<>();
	public boolean isValidBST(TreeNode root) {
		inorderTraversal(root);
		for (int i = 1; i < inorder.size(); i++) {
			if (inorder.get(i) <= inorder.get(i - 1)) {
				return false;
			}
		}
		return true;
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

// space optimize
class Solution {
	public boolean isValidBST(TreeNode root) {
		return validate(root)[2] == 1;
	}

	private int[] validate(TreeNode root) {
		if (root == null) {
			return new int[]{1001, -1001, 1};
		}
		int[] left = validate(root.left);
		int[] right = validate(root.right);
		if (left[2] == 0 || right[2] == 0) {
			return new int[]{0, 0, 0};
		}
		boolean validRoot = left[1] < root.val && root.val < right[0];
		if (!validRoot) {
			return new int[]{0, 0, 0};
		}
		return new int[]{Math.min(left[0], root.val), Math.max(right[1], root.val), 1};
	}
}
