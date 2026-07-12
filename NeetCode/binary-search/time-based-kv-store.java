class TimeMap {
	class ListNode {
		int timestamp;
		String value;

		public ListNode() {
			this.timestamp = 0;
			this.value = "";
		}

		public ListNode(int ts, String s) {
			this.timestamp = ts;
			this.value = s;
		}
	}

	Map<String, List<ListNode>> map;

	public TimeMap() {
		map = new HashMap<>();
	}

	public void set(String key, String value, int timestamp) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList());
		}
		List<ListNode> list = map.get(key);
		list.add(new ListNode(timestamp, value));
	}

	public String get(String key, int timestamp) {
		if (!map.containsKey(key)) {
			return "";
		}
		List<ListNode> bucket = map.get(key);
		int l = 0, r = bucket.size() - 1;
		while (l < r - 1) {
			int m = l + (r - l) / 2;
			ListNode mid = bucket.get(m);
			if (mid.timestamp <= timestamp) {
				l = m;
			} else {
				r = m - 1;
			}
		}
		if (bucket.get(r).timestamp <= timestamp) {
			return bucket.get(r).value;
		} else if (bucket.get(l).timestamp <= timestamp) {
			return bucket.get(l).value;
		}
		return "";
	}
}
