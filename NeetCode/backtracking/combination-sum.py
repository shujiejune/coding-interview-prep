class Solution:
    def combinationSum(self, nums: list[int], target: int) -> list[list[int]]:
        ans = []
        nums.sort()

        def dfs(i, comb, total):
            if total == target:
                ans.append(comb.copy())
                return

            for j in range(i, len(nums)):
                if total + nums[j] > target:
                    return
                comb.append(nums[j])
                dfs(j, comb, total + nums[j])
                comb.pop()

        dfs(0, [], 0)
        return ans

# TC: O(n^(t/m))
# SC: O(t/m)
