String[] solution(String[][] laps) {
	int n = laps[0].length;
	Map<String, Integer> bestTime = new HashMap<>();
	Set<String> eliminated = new HashSet<>();
	String[] ans = new String[n];
	int ind = 0;
	for (int i = 0; i < laps.length; i++) {
		List<String> loseLap = new ArrayList<>();
		int highest = 0;
		for (int j = 0; j < n; j++) {
			String[] words = laps[i][j].split(" ");
			String name = words[0];
			int time = Integer.valueOf(words[1]);
			if (eliminated.contains(name)) { continue; }
			if (!bestTime.containsKey(name)) {
				bestTime.put(name, time);
			} else {
				bestTime.put(name, Math.min(time, bestTime.get(name)));
			}
			highest = Math.max(highest, bestTime.get(name));
		}
		for (String name : bestTime.keySet()) {
			if (eliminated.contains(name)) { continue; }
			if (bestTime.get(name) == highest) {
				loseLap.add(name);
				eliminated.add(name);
				bestTime.remove(name);
			}
		}
		Collections.sort(loseLap);
		for (int k = 0; k < loseLap.size(); k++) {
			ans[ind++] = loseLap.get(k);
		}
	}
	if (!bestTime.isEmpty()) {
		List<String> remain = new ArrayList<>();
		for (String name : bestTime.keySet()) {
			remain.add(name);
		}
		Collections.sort(remain);
		for (int i = 0; i < remain.size(); i++) {
			ans[ind++] = remain.get(i);
		}
	}
	return ans;
}
