int solution(int n, int workLimit, int recoveryTime, String[] events) {
	Map<Integer, Map<Boolean, Integer>> servers = new HashMap<>();
	for (int i = 0; i < n; i++) {
		Map<Boolean, Integer> info = new HashMap<>();
		info.put(true, 0);
		servers.put(i, info);
	}
	int currServer = 0, currTime = 0;
	int[] count = new int[n];
	for (String e : events) {
		if (e.equals("REQUEST")) {
			Map<Boolean, Integer> currInfo = servers.get(currServer);
			if (currInfo.containsKey(true)) {
				int currProcessing = currInfo.get(true);
				if (currProcessing < workLimit - 1) {
					currInfo.put(true, currProcessing + 1);
				} else if (currProcessing == workLimit - 1) {
					currInfo.remove(true);
					currInfo.put(false, currTime + recoveryTime + 1);
				}
				count[currServer]++;
				currTime++;
			} else {
				while (!currInfo.containsKey(true)) {
					currServer = (currServer + 1) % n;
					currInfo = servers.get(currServer);
					if (currInfo.containsKey(false)) {
						int upTime = currInfo.get(false);
						if (upTime <= currTime) {
							currInfo.remove(false);
							currInfo.put(true, 0);
						}
					}
				}
				int currProcessing = currInfo.get(true);
				if (currProcessing < workLimit - 1) {
					currInfo.put(true, currProcessing + 1);
				} else if (currProcessing == workLimit - 1) {
					currInfo.remove(true);
					currInfo.put(false, currTime + recoveryTime + 1);
				}
				count[currServer]++;
				currTime++;
			}
		} else {
			String[] temp = e.split(" ");
			int upServer = Integer.parseInt(temp[1]);
			Map<Boolean, Integer> upInfo = servers.get(upServer);
			if (upInfo.containsKey(false)) {
				upInfo.remove(false);
				upInfo.put(true, 0);
			}
		}
	}
	int maxCount = 0, maxInd = 0;
	for (int i = 0; i < n; i++) {
		if (count[i] >= maxCount) {
			maxCount = count[i];
			maxInd = i;
		}
	}
	return maxInd;
}
