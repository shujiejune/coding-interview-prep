class Solution {
  public List<List<String>> solveNQueens(int n) {
    List<List<Integer>> ans = new ArrayList<>();
    dfs(n, 0, new ArrayList<>(), ans);
    List<List<String>> res = new ArrayList<>();
    for (List<Integer> cols : ans) {
      List<String> solution = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        int col = cols.get(i);
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n; j++) {
          if (j == col) {
            s.append('Q');
          } else {
            s.append('.');
          }
        }
        solution.add(s.toString());
      }
      res.add(solution);
    }
    return res;
  }

  private void dfs(int n, int i, List<Integer> cols, List<List<Integer>> ans) {
    if (i == n) {
      ans.add(new ArrayList<>(cols));
      return;
    }
    for (int j = 0; j < n; j++) {
      boolean isValid = true;
      // check (x, cols.get(x)) and (i, j)
      for (int x = 0; x < cols.size(); x++) {
        if (j == cols.get(x)) {
          isValid = false;
          break;
        }
        if (i - x == Math.abs(j - cols.get(x))) {
          isValid = false;
          break;
        }
      }
      if (isValid) {
        cols.add(j);
        dfs(n, i + 1, cols, ans);
        cols.remove(cols.size() - 1);
      }
    }
  }
}
