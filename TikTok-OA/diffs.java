int[] solution(int[] diffs) {
	int minDiff = 0;
	int maxDiff = 0;
	int currDiff = 0;
	for (int d : diffs) {
		currDiff += d;
		minDiff = Math.min(minDiff, currDiff);
		maxDiff = Math.max(maxDiff, currDiff);
	}
	return new int[2]{1500 + maxDiff, 1500 + minDiff};
}
