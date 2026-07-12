class Solution {
  public List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    // Optimization: sort nums in descending order
    int[] sortedNums = Arrays.stream(nums)
                             .boxed()
                             .sorted(Comparator.reverseOrder())
                             .mapToInt(Integer::intValue)
                             .toArray();
    dfs(sortedNums, target, 0, new ArrayList<Integer>(), ans);
    return ans;
  }

  private void dfs(int[] nums, int target, int index, List<Integer> comb, List<List<Integer>> ans) {
    if (index == nums.length) {
      if (target == 0) {
        ans.add(new ArrayList<>(comb));
      }
      return;
    }
    int curr = nums[index];
    int r = target / curr;
    dfs(nums, target, index + 1, comb, ans);
    for (int i = 1; i <= r; i++) {
      comb.add(curr);
      dfs(nums, target - curr * i, index + 1, comb, ans);
    }
    for (int i = 1; i <= r; i++) {
      comb.remove(comb.size() - 1);
    }
  }
}

// TC: O((t/m)^n)
// SC: O(t/m)
// Where t is target, m is the minimum element in nums, n is nums.length
