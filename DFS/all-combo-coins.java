/* Given a number of different denominations of coins,
 * find all the possible ways to pay a target number of cents.
 */
public List<List<Integer>> combinations(int target, int[] coins) {
  List<List<Integer>> ans = new ArrayList<>();
  List<Integer> combo = new ArrayList<>();
  dfs(coins, target, 0, combo, ans);
  return ans;
}

private void dfs(int[] coins, int target, int index, List<Integer> combo, List<List<Integer>> ans) {
  if (target == 0) {
    ans.add(new ArrayList<>(combo));
    return;
  }
  if (index == coins.length) {
    return;
  }
  int maxCount = target / coins[index];
  for (int i = 0; i <= maxCount; i++) {
    combo.add(i);
    dfs(coins, target - coins[index] * i, index + 1, combo, ans);
    combo.remove(combo.size() - 1);
  }
}
