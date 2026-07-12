String solution(String[] logs) {
	Map<String, Set<String>> files = new HashMap<>();
	String branch = "";
	for (String log : logs) {
		String[] words = log.split(" ");
		if (words[0].equals("switch")) {
			if (!files.keySet().containsKey(words[1])) {
				files.put(words[1], new HashSet<>());
			}
			branch = words[1];
		}
		if (words[0].equals("push")) {
			files.get(branch).add(words[1]);
		}
	}
	int maxFiles = 0;
	String ans = "";
	Iterator<String> iter = files.keySet().iterator();
	while (iter.hasNext()) {
		branch = iter.next();
		int num = files.get(branch).size();
		if (num > maxFiles) {
			maxFiles = num;
			ans = branch;
		}
	}
	return ans;
}
