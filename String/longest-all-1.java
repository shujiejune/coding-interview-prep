/* Given a 0-1 array, you can flip at most k '0's to '1's.
 * Find the length of the longest subarray consisting of all '1's.
 */
public int longest(String input, int k) {
	if (input == null) {
		return 0;
	}
	int n = input.length();
	if (n <= k) {
		return n;
	}
	int i = 0, j = 0, count = 0;
	int longest = 0;
	while (j < n) {
		if (input.charAt(j) == '0') {
			if (count == k) {
				longest = Math.max(longest, j - i);
				while (input.charAt(i) != '0') {
					i++;
				}
				i++;
			} else {
				count++;
			}
		}
		j++;
	}
	longest = Math.max(longest, j - i);
	return longest;
}
