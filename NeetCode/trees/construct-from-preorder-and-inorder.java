class Solution {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	private TreeNode build(int[] preorder, int preLeft, int preRight,
			       int[] inorder, int inLeft, int inRight) {
		if (preLeft > preRight) return null;
		TreeNode root = new TreeNode(preorder[preLeft]);
		int rootIdx = findIdx(inorder, inLeft, inRight, root.val);
		int leftCount = rootIdx - inLeft;
		int rightCount = inRight - rootIdx;
		TreeNode left = build(preorder, preLeft + 1, preLeft + leftCount, inorder, inLeft, rootIdx - 1);
		TreeNode right = build(preorder, preLeft + leftCount + 1, preRight, inorder, rootIdx + 1, inRight);
		root.left = left;
		root.right = right;
		return root;
	}

	// not BST, cannot use binary search
	private int findIdx(int[] arr, int left, int right, int target) {
		for (int i = left; i <= right; i++) {
			if (arr[i] == target) {
				return i;
			}
		}
		return -1;
	}
}
