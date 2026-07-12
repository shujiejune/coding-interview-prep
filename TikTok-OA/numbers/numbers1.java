int solution(int[] numbers) {
	if (numbers == null || numerbs.length == 0) {
		return -1;
	}
	int n = numbers.length;
	for (int i = 1; i < n; i++) {
		if (numbers[i] % 2 == numbers[i - 1] % 2) {
			return i;
		}
	}
	return -1;
}
