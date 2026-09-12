class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList<>();
    dfs(n, 0, 0, new StringBuilder(), ans);
    return ans;
  }

  private void dfs(int n, int left, int right, StringBuilder s, List<String> ans) {
    if (left == n && right == n) {
      ans.add(s.toString());
      return;
    }
    if (left > right) {
      s.append(')');
      dfs(n, left, right + 1, s, ans);
      s.deleteCharAt(s.length() - 1);
    }
    if (left < n) {
      s.append('(');
      dfs(n, left + 1, right, s, ans);
      s.deleteCharAt(s.length() - 1);
    }
  }
}
