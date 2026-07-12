class Solution {
	public int[][] kClosest(int[][] points, int k) {
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>(){
			@Override
			public int compare(int[] a, int[] b) {
				return dist(b) - dist(a);
			}
		});
		for (int[] p : points) {
			if (maxHeap.size() < k) {
				maxHeap.offer(p);
			} else {
				int[] top = maxHeap.peek();
				if (dist(p) < dist(top)) {
					maxHeap.poll();
					maxHeap.offer(p);
				}
			}
		}
		int[][] ans = new int[k][2];
		int i = 0;
		while (!maxHeap.isEmpty()) {
			int[] curr = maxHeap.poll();
			ans[i][0] = curr[0];
			ans[i][1] = curr[1];
			i++;
		}
		return ans;
	}

	private int dist(int[] p) {
		return p[0] * p[0] + p[1] * p[1];
	}
}
