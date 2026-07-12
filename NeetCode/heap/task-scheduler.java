class Solution {
	public int leastInterval(char[] tasks, int n) {
		int[] freq = new int[26];
		for (char task : tasks) {
			freq[task - 'A']++;
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for (int f : freq) {
			if (f > 0) {
				maxHeap.offer(f);
			}
		}
		int time = 0;
		Queue<int[]> q = new LinkedList<>();
		while (!maxHeap.isEmpty() || !q.isEmpty()) {
			time++;
			if (maxHeap.isEmpty()) {
				time = q.peek()[1];
			} else {
				int f = maxHeap.poll() - 1;
				if (f > 0) {
					q.offer(new int[]{f, time + n});
				}
			}
			if (!q.isEmpty() && q.peek()[1] == time) {
				maxHeap.offer(q.poll()[0]);
			}
		}
		return time;
	}
}

// greedy
class Solution {
	public int leastInterval(char[] tasks, int n) {
		int[] freq = new int[26];
		for (char task : tasks) {
			freq[task - 'A']++;
		}
		Arrays.sort(freq);
		int maxf = freq[25];
		int idle = (maxf - 1) * n;
		for (int i = 24; i >= 0; i--) {
			idle -= Math.min(maxf - 1, freq[i]);
		}
		return Math.max(0, idle) + tasks.length;
	}
}
