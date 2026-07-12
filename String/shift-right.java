/* Shift the whole string to the righthand side by k  positions. */
public String shiftRight(String input, int k) {
	if (input == null || input.length() == 0) {
		return input;
	}
	int n = input.length();
	k = k % n;
	char[] arr = input.toCharArray();
	reverseSubstring(arr, 0, n - k);
	reverseSubstring(arr, n - k, n);
	reverseSubstring(arr, 0, n);
	return new String(arr);
}
