#include <algorithm>
#include <vector>

using namespace std;

class Solutio {
 public:
  vector<vector<int>> ans;
  vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
    ans.clear();
    sort(candidates.begin(), candidates.end());
    vector<int> comb;
    dfs(candidates, target, 0, comb, 0);
    return ans;
  }

 private:
  void dfs(vector<int>& candidates, int target, int i, vector<int>& comb,
           int total) {
    if (total == target) {
      ans.push_back(comb);
      return;
    }
    if (total > target || i == candidates.size()) {
      return;
    }

    comb.push_back(candidates[i]);
    dfs(candidates, target, i + 1, comb, total + candidates[i]);
    comb.pop_back();

    while (i + 1 < candidates.size() && candidates[i] == candidates[i + 1]) {
      i++;
    }

    dfs(candidates, target, i + 1, comb, total);
  }
};
