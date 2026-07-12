String solution(String[] paths) {
	int n = paths.length;
	List<Deque<String>> folderPaths = new ArrayList<>();
	int num = Integer.MAX_VALUE;
	for (String path : paths) {
		String[] temp = path.split("/");
		Deque<String> folders = new ArrayDeque<>();
		for (String f : temp) {
			if (f.equals("..")) {
				folders.pollFirst();
			} else {
				folders.offerFirst(f);
			}
		}
		folderPaths.add(folders);
		num = Math.min(num, folders.size());
	}
	Deque<String> commonFolders = new ArrayDeque<>();
	for (int i = 0; i < num; i++) {
		String tail = folderPaths.get(0).pollFirst();
		boolean common = true;
		for (int j = 1; j < n; j++) {
			Deque<String> curr = folderPaths.get(j);
			if (!curr.pollFirst().equals(tail)) {
				common = false;
				break;
			}
		}
		if (common) {
			commonFolders.offerFirst(tail);
		} else {
			break;
		}
	}
	StringBuilder ans = new StringBuilder();
	while (!commonFolders.isEmpty()) {
		ans.append("/");
		ans.append(commonFolders.pollFirst());
	}
	return ans.toString();
}
