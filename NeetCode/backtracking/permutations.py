class Solution:
    def permute(self, nums: list[int]) -> list[list[int]]:
        self.ans = []
        self.dfs(nums, 0, [], [])
        return self.ans

    def dfs(self, nums: list[int], index: int, p: list[int], ans: list[list[int]]):
        if index == len(nums):
            self.ans.append(p.copy())
            return

        def swap(nums: list[int], i: int, j: int):
            nums[i], nums[j] = nums[j], nums[i]

        for i in range(index, len(nums)):
            p.append(nums[i])
            swap(nums, i, index)
            self.dfs(nums, index + 1, p, ans)
            swap(nums, i, index)
            p.pop()
