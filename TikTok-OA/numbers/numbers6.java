int solution(int[] numbers) {
	int n = numbers.length;
	String[] buffers = new String[];
	for (int i = 0; i < n; i++) {
		buffers[i] = String.valueOf(numbers[i]);
	}
	int count = 0;
	for (int i = 0; i < n - 1; i++) {
		for (int j = i + 1; j < n; j++) {
			if (buffers[i].length() != buffers[j].length()) {
				continue;
			}
			int diff = 0;
			for (int k = 0; k < buffers[i].length(); k++) {
				if (buffers[i].charAt(k) != buffers[j].charAt(k)) {
					diff++;
				}
			}
			if (diff == 1) {
				count++;
			}
		}
	}
	return count;
}
