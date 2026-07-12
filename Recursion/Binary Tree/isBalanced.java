/* Determine whether a binary tree is a balanced binary tree. */
public boolean isBalanced(TreeNode root) {
	if (root == null) {
		return true;
	}
	int leftHeight = getHeight(root.left);
	int rightHeight = hetHeight(root.right);
	if (Math.abs(leftHeight - rightHeight) > 1) {
		return false;
	}
	return isBalanced(root.left) && isBalanced(root.right);
}

private int getHeight(TreeNode root) {
	if (root == null) {
		return 0;
	}
	return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
}

/* This is not optimal because we call a recursive function in each recursive step. */
static class ReturnValue {
	boolean isBalanced;
	int height;

	public ReturnValue(isBalanced, height) {
		this.isBalanced = isBalanced;
		this.height = height;
	}
}

public boolean isBalanced(TreeNode root) {
	return helper(root);
}

private ReturnValue helper(TreeNode root) {
	if (root == null) {
		return new ReturnValue(true, 0);
	}
	ReturnValue leftVal = helper(root.left);
	ReturnValue rightVal = helper(root.right);
	boolean currBalanced = leftVal.isBalanced && rightVal.isBalanced && Math.abs(leftVal.height - rightVal.height) <= 1;
	int currHeight = Math.max(leftVal.height - rightVal.height) + 1;
	return new ReturnValue(currBalanced, currHeight);
}

/* Another solution: getHeightOrNotBalanced */
private int getHeightOrBalanced(TreeNode root) {
	if (root == null) {
		return 0;
	}
	int left = getHeightOtNotBalanced(root.left);
	int right = getHeightOtNotBalanced(root.right);
	if (left == -1 || right  == -1) {
		return -1;
	}
	if (Math.abs(left - right) > 1) {
		return -1;
	}
	return Math.max(left, right) + 1;
}
