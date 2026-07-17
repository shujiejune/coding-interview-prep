class Solution {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    dfs(nums, 0, new ArrayList<>(), ans);
    return ans;
  }

  private void dfs(int[] nums, int index, List<Integer> p, List<List<Integer>> ans) {
    if (index == nums.length) {
      ans.add(new ArrayList<>(p));
      return;
    }
    for (int i = index; i < nums.length; i++) {
      p.add(nums[i]);
      swap(nums, index, i);
      dfs(nums, index + 1, p, ans);
      p.remove(p.size() - 1);
      swap(nums, index, i);
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}

// TC: O(n! * n)
// SC: O(n! * n)
