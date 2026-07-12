class Solution {
	public int lengthOfLongestSubstring(String s) {
		int ans = 0;
		if (s == null || s.length() == 0) {
			return ans;
		}
		int i = 0, j = 0;
		Set<Character> distinct = new HashSet<>();
		while (j < s.length()) {
			char c = s.charAt(j);
			if (!distinct.contains(c)) {
				distinct.add(c);
				j++;
				ans = Math.max(ans, j - i);
			} else {
				if (s.charAt(i) == c) {
					i++;
				} else {
					while (i <= j && s.charAt(i) != c) {
						distinct.remove(s.charAt(i));
						i++;
					}
				}
				j++;
			}
		}
		return ans;
	}
}

class Solution {
	public int lengthOfLongestSubstring(String s) {
		int ans = 0;
		if (s == null || s.length() == 0) {
			return ans;
		}
		Queue<Character> q = new LinkedList<>();
		Set<Character> dedup = new HashSet<>();
		int i = 0;
		while (i < s.length()) {
			char c = s.charAt(i++);
			if (!dedup.contains(c)) {
				dedup.add(c);
				q.offer(c);
				ans = Math.max(ans, dedup.size());
			} else {
				while (!q.isEmpty() && q.peek() != c) {
					char curr = q.poll();
					dedup.remove(curr);
				}
				if (!q.isEmpty()) {
					q.poll();
					q.offer(c);
				}
			}
		}
		return ans;
	}
}
