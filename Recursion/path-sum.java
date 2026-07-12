/*
Given a binary tree in which each node contains an int,
find the max possible sum from any node to any node 
(the start node and the end node can be the same).
*/
public int maxPathSum(TreeNode root) {
	int[] globalMax = new int[1];
	globalMax[0] = Integer.MIN_VALUE;
	maxStraightPathSum(root, globalMax);
	return globalMax[0];
}

private int maxStraightPathSum(TreeNode root, int[] globalMax) {
	if (root == null) {
		return 0;
	}
	int leftMax = maxStraightPathSum(root.left, globalMax);
	int rightMax = maxStraightPathSum(root.right, globalMax);
	int childMax = Math.max(leftMax, rightMax);
	int currMax = root.key + Math.max(leftMax, 0) + Math.max(rightMax, 0);
	globalMax[0] = Math.max(globalMax[0], currMax);
	return Math.max(childMax, 0) + root.key;
}


/* Find the maximum path sum from leaf to root. */
public int maxPathSumLeafToRoot(TreeNode root) {
	if (root == null) {
		return 0;
	}
	int leftMax = maxPathSumLeafToRoot(root.left);
	int rightMax = maxPathSumLeafToRoot(root.right);
	if (root.left == null && root.right != null) {
		return rightMax + root.key;
	} else if (root.left != null && root.right == null) {
		return leftMax + root.key;
	}
	return Math.max(leftMax, rightMax) + root.key;
}


/* Determine if there exists a path from a node to itself or any of its descendants,
 * that sums to the target number.
 */
public boolean exist(TreeNode root, int target) {
	if (root == null) {
		return false;
	}
	if (helper(root, target)) {
		return true;
	} else {
		return helper(root.left, target) || helper(root.right, target);
	}
}

// returns if there exists a path sum = target that starts from root and ends at itself or one of its descendants
private boolean helper(TreeNode root, int target) {
	if (root == null) {
		return false;
	}
	if (root.key == target) {
		return true;
	}
	return helper(root.left, target - root.key) || helper(root.right, target - root.key);
}


/* Find the maximum path sum from a node to itself or any of its descendants. */
public int maxPathSum(TreeNode root) {
	if (root == null) {
		return 0;
	}
	int[] globalMax = new int[]{root.key};
	dfs(root, 0, globalMax);
	return globalMax[0];
}

private int dfs(TreeNode root, int dp, int[] globalMax) {
	if (root == null) {
		return;
	}
	if (dp <= 0) {
		dp = root.key;
	} else {
		dp += root.key;
	}
	globalMax[0] = Math.max(globalMax[0], dp);
	dfs(root.left, dp, globalMax);
	dfs(root.right, dp, globalMax);
}
