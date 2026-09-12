class Solution {
  private List<String> part = new ArrayList<>();
  private List<List<String>> ans = new ArrayList<>();

  public List<List<String>> partition(String s) {
    dfs(s, 0, 0);
    return ans;
  }

  private void dfs(String s, int left, int right) {
    if (right == s.length()) {
      if (left == right) {
        ans.add(new ArrayList<>(part));
      }
      return;
    }
    if (isPali(s, left, right)) {
      part.add(s.substring(left, right + 1));
      dfs(s, right + 1, right + 1);
      part.remove(part.size() - 1);
    }
    dfs(s, left, right + 1);
  }

  private boolean isPali(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }
}

// Optimization: use dp to preprocess the palindromes
class Solution {
  private boolean[][] dp;

  public List<List<String>> partition(String s) {
    int n = s.length();
    dp = new boolean[n][n];
    for (int l = 1; l <= n; l++) {
      for (int i = 0; i + l <= n; i++) {
        if (l == 1) {
          dp[i][i + l - 1] = true;
        } else if (l == 2) {
          dp[i][i + l - 1] = s.charAt(i) == s.charAt(i + l - 1);
        } else {
          dp[i][i + l - 1] = s.charAt(i) == s.charAt(i + l - 1)
            && dp[i + 1][i + l - 2];
        }
      }
    }
    List<List<String>> ans = new ArrayList<>();
    List<String> part = new ArrayList<>();
    dfs(0, s, part, ans);
    return ans;
  }

  private void dfs(int i, String s, List<String> part, List<List<String>> ans) {
    if (i >= s.length()) {
      ans.add(new ArrayList<>(part));
      return;
    }
    for (int j = i; j < s.length(); j++) {
      if (dp[i][j]) {
        part.add(s.substring(i, j + 1));
        dfs(j + 1, s, part, ans);
        part.remove(part.size() - 1);
      }
    }
  }
}
