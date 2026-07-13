class Solution:
    def combinationSum2(self, candidates: list[int], target: int) -> list[list[int]]:
        ans = []
        candidates.sort()

        def dfs(i, comb, total):
            if total == target:
                ans.append(comb.copy())
                return
            if total > target or i == len(candidates):
                return

            comb.append(candidates[i])
            dfs(i + 1, comb, total + candidates[i])
            comb.pop()

            while i + 1 < len(candidates) and candidates[i] == candidates[i + 1]:
                i += 1
            dfs(i + 1, comb, total)

        dfs(0, [], 0)
        return ans
