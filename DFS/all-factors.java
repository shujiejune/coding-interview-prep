/**
 * Get all possible combinations of factors that can multiply to a target number.

 * Assumptions:

 * The given number is guaranteed to be >= 2.

 * Examples:

 * 12 -->  [ [2, 2, 3], [2, 6], [3, 4], [12] ]
 * 5 --> [ [5] ]
*/
public class Solution {
  public List<List<Integer>> factors(int n) {
    // Write your solution here
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> fs = new ArrayList<>();
    dfs(n, 2, fs, ans);
    return ans;
  }

  private void dfs(int n, int curr, List<Integer> fs, List<List<Integer>> ans) {
    if (n == 1) {
      ans.add(new ArrayList<>(fs));
      return;
    }
    for (int i = curr; i <= n; i++) {
      if (n % i == 0) {
        fs.add(i);
        dfs(n / i, i, fs, ans);
        fs.remove(fs.size() - 1);
      }
    }
  }
}
