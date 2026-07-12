/**
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums toT. Each number in C may only be used once in the combination.

All numbers (including target) will be positive integers.

Elements in a combination (a1, a2, … , ak) must be in non-descending order.

The solution set must not contain duplicate combinations.

Example

  given candidate set 10,1,2,7,6,1,5 and target 8,

  A solution set is:

  [1, 7]
  [1, 2, 5]
  [2, 6]
  [1, 1, 6]
*/
public class Solution {
  public List<List<Integer>> combinationSum2(int[] num, int target) {
    // Write your solution here
    Arrays.sort(num);
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> comb = new ArrayList<>();
    helper(num, 0, target, comb, ans);
    return ans;
  }

  private void helper(int[] num, int start, int target, List<Integer> comb, List<List<Integer>> ans) {
    if (start == num.length) {
      if (target == 0) {
        ans.add(new ArrayList<>(comb));
      }
      return;
    }
    comb.add(num[start]);
    helper(num, start + 1, target - num[start], comb, ans);
    comb.remove(comb.size() - 1);
    int j = start + 1;
    while (j < num.length && num[j] == num[start]) {
      j++;
    }
    helper(num, j, target, comb, ans);
  }
}
