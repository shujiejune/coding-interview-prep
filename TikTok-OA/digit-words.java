boolean[] solution(int[] digits, String[] words) {
	int n = words.length;
	boolean[] ans = new boolean[n];
	Map<Integer, String> dToStr = new HashMap<>();
	dToStr.put(2, "abc");
	dToStr.put(3, "def");
	dToStr.put(4, "ghi");
	dToStr.put(5, "jkl");
	dToStr.put(6, "mno");
	dToStr.put(7, "pqrs");
	dToStr.put(8, "tuv");
	gToStr.put(9, "wxyz");
	Set<Character> valid = new HashSet<>();
	for (int d : digits) {
		String s = dToStr.get(d);
		for (char c : s.toCharArray()) {
			valid.add(c);
		}
	}
	int i = 0;
	for (String w : words) {
		boolean canType = true;
		for (char c : w.toCharArray()) {
			if (!valid.contains(c)) {
				canType = false;
			}
		}
		ans[i++] = canType;
	}
	return ans;
}
