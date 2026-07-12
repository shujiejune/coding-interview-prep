/* Given an array of size n (no duplicate), print all possible
 * combinations of k elements in the array.
 */
public List<List<Integer>> allCombos(int[] array, int k) {
  List<List<Integer>> ans = new ArrayList<>();
  if (array == null || array.length == 0) {
    return ans;
  }
  dfs(array, 0, k, new ArrayList<>(), ans);
  return ans;
}

private void dfs(int[] array, int index, int k, List<Integer> combo, List<List<Integer>> ans) {
  if (combo.size() == k) {
    ans.add(new ArrayList<>(combo));
    return;
  }
  if (index == array.length) {
    return;
  }
  combo.add(array[index]);
  dfs(array, index + 1, k, combo, ans);
  combo.remove(combo.size() - 1);
  dfs(array, index + 1, k, combo, ans);
}
