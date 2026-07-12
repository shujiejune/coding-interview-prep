/* Given an integer array nums and an integer k, 
* return the k most frequent elements within the array.
* The test cases are generated such that the answer is always unique.
* You may return the output in any order.
*/
class Solution {
	public int[] topKFrequent(int[] nums, int k) {
		int[] ans = new int[k];
		PriorityQueue<Pair<Integer, Integer>> minHeap = 
			new PriorityQueue<>(k, new Comparator<>(){
			@Override
			public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
				if (p1.getValue() != p2.getValue()) {
					return p1.getValue() - p2.getValue();
				}
				return p2.getKey() - p1.getKey();
			}
		});
		Map<Integer, Integer> freq = new HashMap<>();
		for (int num : nums) {
			freq.put(num, freq.getOrDefault(num, 0) + 1);
		}
		for (int key : freq.keySet()) {
			if (minHeap.size() < k) {
				minHeap.offer(new Pair(key, freq.get(key)));
			} else if (minHeap.peek().getValue() < freq.get(key)) {
				minHeap.poll();
				minHeap.offer(new Pair(key, freq.get(key)));
			}
		}
		int i = 0;
		while (!minHeap.isEmpty()) {
			ans[i++] = minHeap.poll().getKey();
		}
		return ans;
	}
}
