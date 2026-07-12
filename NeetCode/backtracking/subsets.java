class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    dfs(nums, 0, new ArrayList<Integer>(), ans);
    return ans;
  }

  private void dfs(int[] nums, int index, List<Integer> subset, List<List<Integer>> ans) {
    if (index == nums.length) {
      ans.add(new ArrayList<>(subset));
      return;
    }
    subset.add(nums[index]);
    dfs(nums, index + 1, subset, ans);
    subset.remove(subset.size() - 1);
    dfs(nums, index + 1, subset, ans);
  }
}
