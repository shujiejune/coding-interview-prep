int solution(int numRows, int numCols, int currRow, int currCol, int[][] laserCoord) {
	boolean[] laserRows = new boolean[numRows];
	boolean[] laserCols = new boolean[numCols];
	for (int[] coord : laserCoord) {
		laserRows[coord[0]] = true;
		laserCols[coord[1]] = true;
	}
	int maxRow = 0, maxCol = 0;
	for (int i = 1; i < numRows; i++) {
		if (!isValidRow(currRow - i, numRows, laserRows) && !isValidRow(currRow + i, numRows, laserRows)) {
			maxRow = Math.max(maxRow, i);
		}
	}
	for (int j = 1; j < numCols; j++) {
		if (!isValidCol(currCol - j, numCols, laserCols) && !isValidCol(currCol + j, numCols, laserCols)) {
			maxCol = Math.max(maxCol, j);
		}
	}
	return Math.max(maxRow, maxCol);
}

boolean isValidRow(int i, int m, boolean[] laserRows) {
	if (i < 0 || i >= m) {
		return false;
	}
	if (laserRows[i]) {
		return false;
	}
	return true;
}

boolean isValidCol(int i, int m, boolean[] laserCols) {
	if (i < 0 || i >= m) {
		return false;
	}
	if (laserCols[i]) {
		return false;
	}
	return true;
}
