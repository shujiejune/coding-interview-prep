int solution(int[][] centers) {
	int collision = 0;
	int n = centers.length;
	for (int i = 0; i < n - 1; i++) {
		for (int j = i + 1; j < n; j++) {
			int x1 = centers[i][0], y1 = centers[i][1];
			int x2 = centers[j][0], y2 = centers[j][1];
			if (Math.abs(x1 - x2) <= 2 && Math.abs(y1 - y2) <= 2) {
				collision++;
			}
		}
	}
	return collision;
}
