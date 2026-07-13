class Solution {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    Map<Integer, Integer> freq = new HashMap<>();
    List<Integer> uniq = new ArrayList<>();
    for (int num : candidates) {
      if (!freq.containsKey(num)) {
        uniq.add(num);
        freq.put(num, 0);
      }
      freq.put(num, freq.get(num) + 1);
    }
    Collections.sort(uniq, (a, b) -> b - a);
    dfs(freq, uniq, 0, new ArrayList<Integer>(), ans, target);
    return ans;
  }

  private void dfs(Map<Integer, Integer> freq, List<Integer> uniq, int index, List<Integer> comb, List<List<Integer>> ans, int target) {
    if (index == uniq.size()) {
      if (target == 0) {
        ans.add(new ArrayList<>(comb));
      }
      return;
    }
    dfs(freq, uniq, index + 1, comb, ans, target);
    int num = uniq.get(index);
    int f = freq.get(num);
    int r = target / num;
    int upper = Math.min(f, r);
    for (int i = 1; i <= upper; i++) {
      comb.add(num);
      dfs(freq, uniq, index + 1, comb, ans, target - i * num);
    }
    for (int i = 1; i <= upper; i++) {
      comb.remove(comb.size() - 1);
    }
  }
}

class Solution2 {
  private List<List<Integer>> ans;

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    ans = new ArrayList<>();
    Arrays.sort(candidates);
    dfs(candidates, target, 0, new ArrayList<>(), 0);
    return ans;
  }

  private void dfs(int[] candidates, int target, int i, List<Integer> comb, int total) {
    if (total == target) {
      ans.add(new ArrayList<>(comb));
      return;
    }
    if (total > target || i == candidates.length) {
      return;
    }
    comb.add(candidates[i]);
    dfs(candidates, target, i + 1, comb, total + candidates[i]);
    comb.remove(comb.size() - 1);
    while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
      i++;
    }
    dfs(candidates, target, i + 1, comb, total);
  }
}
