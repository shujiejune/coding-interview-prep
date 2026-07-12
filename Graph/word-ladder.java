/* Transform a given word into another given word in the fewest steps. 
 * In each step, you are allowed to change only one letter and 
 * the resultant word must be in the dictionary.
 *
 * Assumptions:
 * - all words have the same length
 * - all words contain only lowercase letters
 * - there is no duplicate in the word list
 * - the beginWord and the endWord are not empty and not the same
 */
public class solution {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>();
		for (String word : wordList) {
			dict.add(word);
		}
		Queue<String> q = new ArrayDeque<>();
		Map<String, Integer> wordSteps = new HashMap<>();
		q.offerFirst(beginWord);
		wordSteps.put(beginWord, 1);
		while (!q.isEmpty()) {
			String curr = q.pollFirst();
			int currStep = wordSteps.get(curr);
			List<String> candidates = getNextWords(curr, dict);
			for (String next : candidates) {
				if (!wordSteps.containsKey()) {
					q.offerFirst(next);
					wordSteps.put(next, currStep + 1);
				}
				if (next.equals(endWord)) {
					return wordSteps.get(next);
				}
			}
		}
		return 0;
	}

	private List<String> getNextWords(String input, Set<String> dict) {
		List<String> ans = new ArrayList<>();
		StringBuilder s = new StringBuilder(input);
		int n = input.length();
		for (int i = 0; i < n; i++) {
			char c = input.charAt(i);
			for (int j = 0; j < 26; j++) {
				s.setCharAt(i, 'a' + j);
				if (dict.contains(s.toString())) {
					ans.add(s.toString());
				}
			}
			s.setCharAt(i, c);
		}
		return ans;
	}
}

/* TC: O()
