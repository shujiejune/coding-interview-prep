class Solution {
	public boolean checkInclusion(String s1, String s2) {
		int[] freq = new int[26];
		for (char c : s1.toCharArray()) {
			freq[c - 'a']++;
		}
		int count = 0;
		int i = 0, j = 0;
		while (j < s2.length()) {
			char c = s2.charAt(j);
			if (freq[c - 'a'] <= 0) {
				freq[s2.charAt(i) - 'a']++;
				i++;
				count--;
			} else {
				freq[c - 'a']--;
				count++;
				j++;
				if (count == s1.length()) {
					return true;
				}
			}
		}
		return false;
	}
}
