/* Determine whether a string is the substring of another string. 
 * Return the index of first occurrence of the specified substring,
 * or -1 if no such occurrence.
 */
public int subStr(String s1, String s2) {
	if (s1 == null || s2 == null || s1.length() < s2.length()) {
		return -1;
	}
	if (s2.length() == 0) {
		return 0;
	}
	int m = s1.length(), n = s2.length();
	int i = 0, j = 0;
	while (i + n <= m) {
		if (s1.charAt(i) != s2.charAt(j)) {
			i = i - j + 1;
			j = 0;
			while (i + n <= m && s1.charAt(i) != s2.charAt(j)) {
				i++;
			}
		} else {
			while (j < n && s1.charAt(i) == s2.charAt(j)) {
				i++;
				j++;
			}
			if (j == n) {
				return i - n;
			}
		}
	}
	return -1;
}

/* Rabin-Karp
 * 1. Hash the string into a unique integer, via a hash function without collision
 * 2. Compare the hash value of each substring of s1 with the hash value of s2
 *
 * How to Hash:
 * 'a' = 0, ..., 'z' = 25
 * "abc" = 0 * 26^2 + 1 * 26^1 + 2 * 26^2
 * hash("bcd") = (hash("abc") - ('a' - 'a') * 26^2) * 26 + ('d' - 'a') * 26^0
 */

