/* Given two strings s and t, return true if the two strings 
 * are anagrams of each other, otherwise return false.
 * An anagram is a string that contains the exact same characters 
 * as another string, but the order of the characters can be different.
 */
class Solution {
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int n = s.length();
		int[] freq = new int[26];
		for (int i = 0; i < n; i++) {
			freq[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < n; i++) {
			char c = t.charAt(i);
			if (freq[c - 'a'] == 0) {
				return false;
			} else {
				freq[c - 'a']--;
			}
		}
		return true;
	}
}
