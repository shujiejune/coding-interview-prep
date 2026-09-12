class Solution {
  private Map<Integer, String> pad;

  public List<String> letterCombinations(String digits) {
    List<String> ans = new ArrayList<>();
    if (digits == null || digits.length() == 0) {
      return ans;
    }
    pad = new HashMap<>();
    pad.put(2, "abc");
    pad.put(3, "def");
    pad.put(4, "ghi");
    pad.put(5, "jkl");
    pad.put(6, "mno");
    pad.put(7, "pqrs");
    pad.put(8, "tuv");
    pad.put(9, "wxyz");
    int n = digits.length();
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = digits.charAt(i) - '0';
    }
    dfs(nums, 0, new StringBuilder(), ans);
    return ans;
  }

  private void dfs(int[] nums, int i, StringBuilder s, List<String> ans) {
    if (i == nums.length) {
      ans.add(s.toString());
      return;
    }
    String options = pad.get(nums[i]);
    for (int j = 0; j < options.length(); j++) {
      s.append(options.charAt(j));
      dfs(nums, i + 1, s, ans);
      s.deleteCharAt(s.length() - 1);
    }
  }
}
