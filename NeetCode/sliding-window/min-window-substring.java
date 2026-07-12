class Solution {
	public String minWindow(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		Map<Character, Integer> freq = new HashMap<>();
		Map<Character, Integer> window = new HashMap<>();
		for (char c : t.toCharArray()) {
			freq.put(c, freq.getOrDefault(c, 0) + 1);
		}
		int i = 0, j = 0, count = 0;
		int[] bounds = new int[]{-1, -1};
		int minLen = s.length() + 1;
		while (j < s.length()) {
			char c = s.charAt(j);
			window.put(c, window.getOrDefault(c, 0) + 1);
			if (freq.containsKey(c) && window.get(c) == freq.get(c)) {
				count++;
			}
			j++;
			while (count == freq.size()) {
				int len = j - i;
				if (len < minLen) {
					minLen = len;
					bounds[0] = i;
					bounds[1] = j;
				}
				char left = s.charAt(i);
				window.put(left, window.get(left) - 1);
				if (freq.containsKey(left) && window.get(left) < freq.get(left)) {
					count--;
				}
				i++;
			}
		}
		if (bounds[0] == -1) {
			return "";
		} else {
			return s.substring(bounds[0], bounds[1]);
		}
	}
}
