import heapq

class Solution:
    def kClosest(self, points: list[list[int]], k: int) -> list[list[int]]:
        maxHeap = []
        for x, y in points:
            dist = -(x ** 2 + y ** 2)
            heapq.heappush(maxHeap, [dist, x, y])
            if len(maxHeap) > k:
                heapq.heappop(maxHeap)

        ans = []
        while maxHeap:
            dist, x, y = heapq.heappop(maxHeap)
            ans.append([x, y])

        return ans
