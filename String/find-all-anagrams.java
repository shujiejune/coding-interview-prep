/* Find all anagrams of a substring s2 in a long string s1. */
public List<String> findAllAnagrams(String s1, String s2) {
	List<String> ans = new ArrayList<>();
	if (s1 == null || s1.length() == 0) {
		return ans;
	}
	Map<Character, Integer> freq = new HashMap<>();
	for (int i = 0; i < s2.length(); i++) {
		char c = s2.charAt(i);
		freq.put(c, freq.getOrDefault(c, 0) + 1);
	}
	Map<Character, Integer> freqCopy = new HashMap<>(freq);
	int matchCount = 0;
	int i = 0, j = 0, n = s1.length();
	while (j < n) {
		char c = s1.charAt(j++);
		if (!freqCopy.containsKey(c)) {
			matchCount = 0;
			freqCopy = new HashMap<>(freq);
			i = j;
		} else {
			if (freqCopy.get(c) > 0) {
				freqCopy.put(c, freqCopy.get(c) - 1);
				matchCount++;
				if (matchCount == s2.length()) {
					ans.add(s1.substring(i, j));
					char left = s1.charAt(i++);
					freqCopy.put(left, freqCopy.get(left) + 1);
					matchCount--;
				}
			} else {
				while (s1.charAt(i) != c) {
					freqCopy.put(s1.charAt(i), freqCopy.get(s1.charAt(i)) + 1);
					matchCount--;
					i++;
				}
				i++;
			}
		}
	}
	return ans;
}

/* Optimal Solution */
public List<String> findAllAnagrams2(String s1, String s2) {
	List<String> ans = new ArrayList<>();
	if (s1 == null || s1.length() == 0 || s1.length() < s2.length()) {
		return ans;
	}
	Map<Character, Integer> freq = new HashMap<>();
	for (int i = 0; i < s2.length(); i++) {
		char c = s2.charAt(i);
		freq.put(c, freq.getOrDefault(c, 0) + 1);
	}
	int i = 0, j = 0, matchCount = 0;
	int n = s1.length();
	while (j < n) {
		char c = s1.charAt(j);
		if (freq.containsKey(c)) {
			freq.put(c, freq.get(c) - 1);
			if (freq.get(c) == 0) {
				matchCount++;
			}
		}
		j++;
		if (matchCount = freq.size()) {
			ans.add(s1.substring(i, j));
		}
		if (j - i == s2.length()) {
			char left = s1.charAt(i);
			if (freq.containsKey(left)) {
				if (freq.get(left) == 0) {
					matchCount--;
				}
				freq.put(left, freq.get(left) + 1);
			}
		}
		i++;
	}
	return ans;
}
