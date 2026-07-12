/* Replace a pattern s1 in the given string s by another pattern s2.
 * Example: den -> XX
 * student -> stuXXt
 */
public String replace(String s, String s1, String s2) {
	if (s == null || s.length() == 0) {
		return s;
	}
	StringBuilder sb = new StringBuilder();
	List<Integer> starts = findOccurrences(s, s1);
	int i = 0, k = 0;
	while (i < s.length()) {
		if (k < starts.size()) {
			int start = starts.get(k++);
			while (i < start) {
				sb.append(s.charAt(i++));
			}
			sb.append(s2);
			i += s1.length();
		} else {
			while (i < s.length()) {
				sb.append(s.charAt(i++));
			}
		}
	}
	return sb.toString();
}

private List<Integer> findOccurrences(String s, String t) {
	List<Integer> ans = new ArrayList<>();
	int n = s.length(), m = t.length();
	int i = 0, j = 0;
	while (i + m <= n) {
		if (s.charAt(i) != t.charAt(j)) {
			i = i - j + 1;
			j = 0;
			continue;
		}
		while (i < n && j < m && s.charAt(i) == t.charAt(j)) {
			i++;
			j++;
		}
		if (j == m) {
			ans.add(i - j);
			j = 0;
		}
	}
	return ans;
}
