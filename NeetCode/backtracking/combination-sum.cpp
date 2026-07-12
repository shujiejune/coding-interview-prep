#include <algorithm>
#include <vector>

using namespace std;

class Solution {
 public:
  vector<vector<int>> ans;
  vector<vector<int>> combinationSum(vector<int>& nums, int target) {
    sort(nums.begin(), nums.end());
    dfs(0, {}, 0, nums, target);
    return ans;
  }

  void dfs(int i, vector<int> comb, int total, vector<int>& nums, int target) {
    if (total == target) {
      ans.push_back(comb);
      return;
    }
    for (int j = i; j < nums.size(); j++) {
      if (total + nums[j] > target) {
        return;
      }
      comb.push_back(nums[j]);
      dfs(j, comb, total + nums[j], nums, target);
      comb.pop_back();
    }
  }
};
