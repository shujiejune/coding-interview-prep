class Solution {
	public int lastStoneWeight(int[] stones) {
		if (stones.length == 1) {
			return stones[0];
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int stone : stones) {
			pq.offer(stone);
		}
		while (pq.size() > 1) {
			int x = pq.poll();
			int y = pq.poll();
			if (x == y) continue;
			else pq.offer(Math.abs(x - y));
		}
		if (pq.isEmpty()) return 0;
		else return pq.poll();
	}
}
