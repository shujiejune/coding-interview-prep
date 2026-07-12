/* Given a sequence of student heights, each student 
 * must join an existing team where all students currently 
 * in that team are taller than him/her. If no such team exists, 
 * the student starts a new team. Find the minimum number of teams required.
 */
public int minQueues(int[] heights) {
	if (heights == null || heights.length == 0) {
		return 0;
	}
	List<Integer> tails = new ArrayList<>();
	for (int h : heights) {
		int left = 0, right = tails.size() - 1;
		// find smallest tail that is larger than h
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (tails.get(mid) > h) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		if (left == tails.size()) {
			tails.add(h);
		} else {
			tails.set(left, h);
		}
	}
	return tails.size();
}
