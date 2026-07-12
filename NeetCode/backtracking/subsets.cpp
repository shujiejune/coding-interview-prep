#include <vector>

using namespace std;

class Solution {
 public:
  vector<vector<int>> subsets(vector<int>& nums) {
    vector<vector<int>> ans;
    vector<int> subset;
    dfs(nums, 0, subset, ans);
    return ans;
  }

 private:
  void dfs(const vector<int>& nums, int i, vector<int>& subset,
           vector<vector<int>>& ans) {
    if (i >= nums.size()) {
      ans.push_back(subset);
      return;
    }
    subset.push_back(nums[i]);
    dfs(nums, i + 1, subset, ans);
    subset.pop_back();
    dfs(nums, i + 1, subset, ans);
  }
};
