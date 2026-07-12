class KthLargest {
	int capacity;
	PriorityQueue<Integer> pq;

	public KthLargest(int k, int[] nums) {
		this.capacity = k;
		this.pq = new PriorityQueue<>(k);
		for (int num : nums) {
			if (pq.size() < k) {
				pq.offer(num);
			} else {
				if (pq.peek() < num) {
					pq.poll();
					pq.offer(num);
				}
			}
		}
	}

	public int add(int val) {
		if (pq.size() < capacity) {
			pq.offer(val);
		} else if (pq.peek() < val) {
			pq.poll();
			pq.offer(val);
		}
		return pq.peek();
	}
}
