int solution(int[] audiobooks, String[] logs) {
	int n = audiobooks.length;
	int[] listenTime = new int[n];
	boolean[] skip = new boolean[n];
	int currListen = 0;
	for (String log : logs) {
		String[] words = log.split(" ");
		if (words[0].equals("LISTEN")) {
			int minutes = Integer.valueOf(words[1]);
			listenTime[currListen] += minutes;
			if (listenTime[currListen] == audiobooks[currListen]) {
				skip[currListen] = true;
			}
			currListen = (currListen + 1) % n;
			while (skip[currListen]) {
				currListen = (currListen + 1) % n;
			}
		}
		if (words[0].equals("DROP")) {
			int idx = Integer.valueOf(words[1]);
			skip[idx] = true;
			while (skip[currListen]) {
				currListen = (currListen + 1) % n;
			}
		}
	}
	int maxTime = 0, maxTimeInd = 0;
	for (int i = 0; i < n; i++) {
		if (listenTime[i] >= maxTime) {
			maxTime = listenTime[i];
			maxTimeInd = i;
		}
	}
	return maxTimeInd;
}
