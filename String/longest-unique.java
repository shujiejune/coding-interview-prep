/* Given a string, find the longest substring without any repeating characters, 
 * return the length of it.
 */
public int longest(String input) {
	if (input == null || input.length() == 0) {
		return 0;
	}
	int n = input.length();
	int longest = 0;
	int left = 0, right = 0;
	Set<Character> exist = new HashSet<>();
	while (right < n) {
		char c = input.charAt(right);
		if (!exist.contains(c)) {
			exist.add(c);
		} else {
			while (input.charAt(left) != c) {
				exist.remove(input.charAt(left++));
			}
			left++;
		}
		longest = Math.max(longest, right - left + 1);
		right++;
	}
	return longest;
}
/* TC: O(n)
 * SC: O(n) in worst case
 */

/* Another solution that saves memory
 * Assumption: all the characters in input are lowercase alphabetic
 */
public int longest(String input) {
	if (input == null || input.length() == 0) {
		return 0;
	}
	int n = input.length();
	int[] freq = new int[26];
	int left = 0, right = 0;
	int longest = 0;
	while (right < n) {
		char c = input.charAt(right);
		if (freq[c - 'a'] > 0) {
			while (input.charAt(left) != c) {
				freq[input.charAt(left++) - 'a']--;
			}
			left++;
		} else {
			freq[c - 'a']++;
		}
		longest = Math.max(longest, right - left + 1);
		right++;
	}
	return longest;
}
/* TC: O(n)
 * SC: O(1)
 */
