/* Get all valid permutations of l pairs of (), m pairs of [], and n pairs of {}. */
public List<String> validParentheses(int l, int m, int n) {
  List<String> ans = new ArrayList<>();
  Deque<Character> stack = new ArrayDeque<>();
  char[] candids = new char[]{'(', ')', '[', ']', '{', '}'};
  int[] count = new int[]{l, l, m, m, n, n};
  int len = 2 * (l + m + n);
  dfs(candids, count, len, stack, new StringBuilder(), ans);
  return ans;
}

private void dfs(char[] candids, int[] count, int len, Deque<Character> stack, StringBuilder s, List<String> ans) {
  if (s.length() == len) {
    ans.add(s.toString());
    return;
  }
  for (int i = 0; i < 6; i++) {
    if (count[i] > 0) {
      if (i % 2 == 0) {
        count[i]--;
        stack.offerFirst(candids[i]);
        s.append(candids[i]);
        dfs(candids, count, len, stack, s, ans);
        s.deleteCharAt(s.length() - 1);
        stack.pollFirst();
        count[i]++;
      } else {
        if (!stack.isEmpty() && stack.peekFirst() == candids[i - 1]) {
          count[i]--;
          stack.pollFirst();
          s.append(candids[i]);
          dfs(candids, count, len, stack, s, ans);
          s.deleteCharAt(s.length() - 1);
          stack.offerFirst(candids[i - 1]);
          count[i]++;
        }
      }
    }
  }
}
