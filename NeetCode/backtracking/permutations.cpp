#include <vector>

using namespace std;

class Solution {
  vector<vector<int>> ans;

 public:
  vector<vector<int>> permute(vector<int>& nums) {
    vector<int> p;
    dfs(nums, 0, p);
    return ans;
  }

  void dfs(vector<int>& nums, int index, vector<int>& p) {
    if (index == nums.size()) {
      ans.push_back(p);
      return;
    }
    for (int i = index; i < nums.size(); i++) {
      p.push_back(nums[i]);
      swap(nums, i, index);
      dfs(nums, index + 1, p);
      swap(nums, i, index);
      p.pop_back();
    }
  }

 private:
  void swap(vector<int>& nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
};
