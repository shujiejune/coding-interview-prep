/* Given an nxn board, find all configs of placing n queens,
 * and the n queens cannot attack each other.
 */
public List<List<Integer>> nqueens(int n) {
  List<List<Integer>> ans = new ArrayList<>();
  List<Integer> config = new ArrayList<>(); // the column number of the i-th queen
  dfs(n, 0, config, ans);
  return ans;
}

private void dfs(int n, int index, List<Integer> config, List<List<Integer>> ans) {
  if (index == n) {
    ans.add(new ArrayList<>(config));
    return;
  }
  for (int i = 0; i < n; i++) {
    if (valid(index, i, config)) {
      config.add(i);
      dfs(n, index + 1, config, ans);
      config.remove(index);
    }
  }
}

private boolean valid(int row, int col, List<Integer> config) {
  for (int i = 0; i < config.size(); i++) {
    if (config.get(i) == col) {
      return false;
    }
    if (Math.abs(row - i) == Math.abs(col - config.get(i))) {
      return false;
    }
  }
  return true;
}
