/* print the binary tree level by level in a zigzag way.
 * level is 0-indexed
 * odd: left to right
 * even: right to left
 */
public List<Integer> printZigZag(TreeNode root) {
  List<Integer> ans = new ArrayList<>();
  if (root == null) {
    return ans;
  }
  Queue<TreeNode> q = new ArrayDeque<>();
  q.offer(root);
  int levelIndex = 0;
  while (!q.isEmpty()) {
    int sz = q.size();
    List<Integer> level = new ArrayList<>();
    for (int i = 0; i < sz; i++) {
      TreeNode curr = q.poll();
      if (curr.left != null) {
        q.offer(curr.left);
        level.add(curr.left.key);
      }
      if (curr.right != null) {
        q.offer(curr.right);
        level.add(curr.right.key);
      }
    }
    if (levelIndex % 2 == 0) {
      for (int i = level.size() - 1; i >= 0; i--) {
        ans.add(level.get(i));
      }
    } else {
      for (int i = 0; i < level.size(); i++) {
        ans.add(level.get(i));
      }
    }
    levelIndex++;
  }
  return ans;
}
