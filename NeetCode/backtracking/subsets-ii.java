class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    dfs(nums, 0, new ArrayList<>(), ans);
    return ans;
  }

  private void dfs(int[] nums, int index, List<Integer> subset, List<List<Integer>> ans) {
    if (index == nums.length) {
      ans.add(new ArrayList<>(subset));
      return;
    }
    int right = index;
    while (right < nums.length && nums[right] == nums[index]) {
      right++;
    }
    dfs(nums, right, subset, ans);
    for (int i = index; i < right; i++) {
      subset.add(nums[i]);
      dfs(nums, right, subset, ans);
    }
    while (subset.size() > 0 && subset.get(subset.size() - 1) == nums[index]) {
      subset.remove(subset.size() - 1);
    }
  }
}
//TC: O(n * 2^n)
//SC: O(n)

// Alter 1
class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    dfs(nums, 0, new ArrayList<>(), ans);
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
    while (index + 1 < nums.length && nums[index] == nums[index + 1]) {
      index++;
    }
    dfs(nums, index + 1, subset, ans);
  }
}
