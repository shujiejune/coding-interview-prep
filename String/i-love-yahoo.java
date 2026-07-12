/* reverse every single word in the given sentence. */
public String reverse(String input) {
	if (input == null || input.length() == 0) {
		return input;
	}
	char[] arr = input.toCharArray();
	int i = 0, j = 0, n = input.length();
	while (j < n) {
		while (i < n && arr[i] == ' ') {
			i++;
		}
		j = i;
		while (j < n && arr[j] != ' ') {
			j++;
		}
		reverseSubstring(arr, i, j);
		i = j;
	}
	reverseSubstring(arr, 0, n);
	return new String(arr);
}

// reverse s.substring(i, j)
private void reverseSubstring(char[] arr, int i, int j) {
	while (i < j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		i++;
		j--;
	}
}
