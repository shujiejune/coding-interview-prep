/* How to reconstruct a tree without duplicative values with preorder and inorder traversal sequence. */
public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
	int n = inOrder.length;
	return helper(inOrder, 0, n - 1, preOrder, 0, n - 1);
}

private TreeNode helper(int[] inOrder, int inLeft, int inRight, int[] preOrder, int preLeft, int preRight) {
	if (inLeft > inRight) {
		return null;
	}
	TreeNode root = new TreeNode(preOrder[preLeft]);
	int rootIndex = findIndex(inOrder, root.key);
	int leftSize = rootIndex - inLeft;
	TreeNode left = helper(inOrder, inLeft, rootIndex - 1, preOrder, preLeft + 1, preLeft + leftSize);
	TreeNode right = helper(inOrder, rootIndex + 1, inRight, preOrder, preLeft + leftSize + 1, preRight);
	root.left = left;
	root.right = right;
	return root;
}

// cannot use binary search because array is not sorted
private int findIndex(int[] array, int target) {
	for (int i = 0; i < array.length; i++) {
		if (array[i] == target) {
			return i;
		}
	}
	return -1;
}


/* postorder and inorder*/
public TreeNode reconstruct(int[] inOrder, int[] postOrder) {
	int n = inOrder.length;
	return helper(inOrder, 0, n - 1, postOrder, 0, n - 1);
}

private TreeNode helper(int[] inOrder, int inLeft, int inRight, int[] postOrder, int postLeft, int postRight) {
	if (inLeft > inRight) {
		return null;
	}
	TreeNode root = new TreeNode(postOrder[postRight]);
	int rootIndex = findIndex(inOrder, root.key);
	int leftSize = rootIndex - inLeft;
	TreeNode left = helper(inOrder, inLeft, rootIndex - 1, postOrder, postLeft, postLeft + leftSize - 1);
	TreeNode right = helper(inOrder, rootIndex + 1, inRight, postOrder, postLeft + leftSize, postRight - 1);
	root.left = left;
	root.right = right;
	return root;
}


/* levelorder and inorder */
public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
	Map<Integer, Integer> inMap = new HashMap<>();
	for (int i = 0; i < inOrder.length; i++) {
		inMap.put(inOrder[i], i);
	}
	List<Integer> levels = new ArrayList<>();
	for (int num : levelOrder) {
		levels.add(num);
	}
	return helper(inMap, levels);
}

private TreeNode helper(Map<Integer, Integer> inMap, List<Integer> levels) {
	if (levels.size() == 0) {
		return 0;
	}
	TreeNode root = new TreeNode(levels.get(0));
	if (levels.size() == 1) {
		return root;
	}
	int rootIndex = inMap.get(root.key);
	List<Integer> leftLevels = new ArrayList<>();
	List<Integer> rightLevels = new ArrayList<>();
	for (int i = 1; i < levels.size(); i++) {
		int curr = levels.get(i);
		if (!inMap.containsKey(curr)) { continue; }
		if (inMap.get(curr) < rootIndex) {
			leftLevels.add(curr);
		} else {
			rightLevels.add(curr);
		}
	}
	root.left = helper(inMap, leftLevels);
	root.right = helper(inMap, rightLevels);
	return root;
}
