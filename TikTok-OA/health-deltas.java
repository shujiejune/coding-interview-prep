int solution(int initialHealth, int[] deltas) {
	int h = initialHealth;
	for (int d : deltas) {
		h += d;
		h = Math.max(h, 0);
		h = Math.min(h, 100);
	}
	return h;
}
