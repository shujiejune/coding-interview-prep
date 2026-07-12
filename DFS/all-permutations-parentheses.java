/* Find all valid permutations using the n pairs of parentheses provided. */
public List<String> validParentheses(int n) {
  List<String> ans = new ArrayList<>();
  dfs(n, 0, 0, new StringBuilder(), ans);
  return ans;
}

private void dfs(int n, int index, int numOfLeft, StringBuilder s, List<String> ans) {
  if (index == 2 * n) {
    ans.add(s.toString());
    return;
  }
  if (numOfLeft < n) {
    s.append('(');
    dfs(n, index + 1, numOfLeft + 1, s, ans);
    s.deleteCharAt(s.length() - 1);
  }
  if (index < 2 * numOfLeft) {
    s.append(')');
    dfs(n, index + 1, numOfLeft, s, ans);
    s.deleteCharAt(s.length() - 1);
  }
}
