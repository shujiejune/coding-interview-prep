/* Given a binary tree and two nodes, determine whether they are cousins.
 * Two nodes are cousins if they are at the same level and have different parents.
 */
/* Clarification:
 * 1. duplicate keys? F
 * 2. guaranteed in the tree? T
 * 3. a == b? F
 */
public boolean isCousin(TreeNode root, int a, int b) {
  if (root == null) {
    return false;
  }
  Queue<TreeNode> q = new ArrayDeque<>();
  q.offer(root);
  while (!q.isEmpty()) {
    int sz = q.size();
    boolean findA = false, findB = false, sameParent = false;
    for (int i = 0; i < sz; i++) {
      TreeNode curr = q.poll();
      boolean flipFindA = false, flipFindB = false;
      if (curr.left != null) {
        q.offer(curr.left);
        if (!findA && curr.left.key == a) {
          findA = true;
          flipFindA = true;
        }
        if (!findB && curr.left.key == b) {
          findB = true;
          flipFindB = true;
        }
      }
      if (curr.right != null) {
        q.offer(curr.right);
        if (curr.right.key == a) {
          findA = true;
          flipFindA = true;
        }
        if (curr.right.key == b) {
          findB = true;
          flipFindB = true;
        }
      }
      sameParent = flipFindA && flipFindB;
    }
    if (findA && findB && !sameParent) {
      return true;
    }
  }
  return false;
}
