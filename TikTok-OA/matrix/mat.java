String[][] solution(String[][] mat) {
	int n = mat[0].length / 4;
	List<int[]> missList = new ArrayList<>();
	for (int i = 0; i < n; i++) {
		int miss = getMiss(String[][] mat, i);
		missList.add(new int[]{miss, i});
	}
	Collections.sort(missList, new Comparator<int[]>(){
		@Override
		public int compare(int[] a, int[] b) {
			if (a[0] != b[0]) {
				return a[0] - b[0];
			} else {
				return a[1] - b[1];
			}
		}
	});
	String[][] ans = new String[4][4 * n];
	for (int i = 0; i < n; i++) {
		int miss = missList.get(i)[0];
		int ind = missList.get(i)[1];
		copySquare(ans, mat, i, ind, miss);
	}
	return ans;
}

int getMiss(String[][] mat, int ind) {
	int[] num = new int[17];
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			String cell = mat[i][4 * ind + j];
			if (!cell.equals("?")) {
				num[Integer.parseInt(cell)]++;
			}
		}
	}
	for (int i = 1; i < 17; i++) {
		if (num[i] == 0) {
			return i;
		}
	}
	return 0;
}

void copySquare(String[][] ans, String[][] mat, int ansInd, int matInd, int miss) {
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (mat[i][4 * matInd + j].equals("?")) {
				ans[i][4 * ansInd + j] = String.valueOf(miss);
			} else {
				ans[i][4 * ansInd + j] = mat[i][4 * matInd + j];
			}
		}
	}
}
