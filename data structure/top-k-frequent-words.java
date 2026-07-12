/* Given a composition with different kinds of words,
 * return a list of the topk frequent words.
 */
static class Word {
	String word;
	int count;

	public Word(String word, int count) {
		this.word = word;
		this.count = count;
	}
}

public String[] topKFrequent(String[] combo, int k) {
	Map<String, Integer> freq = new HashMap<>();
	for (String s : combo) {
		freq.put(s, freq.getOrDefault(s, 0) + 1);
	}
	PriorityQueue<Word> maxHeap = new PriorityQueue<>(k, new Comparator<>(){
		@Override
		public int compare(Word w1, Word w2) {
			if (w1.count != w2.count) {
				return w2.count - w1.count;
			}
			return w1.compareTo(w2);
		}
	});
	for (String key : freq.keySet()) {
		Word w = new Word(key, freq.get(key));
		maxHeap.offer(w);
	}
	int sz = maxHeap.size();
	String[] ans = new String[sz];
	for (int i = 0; i < sz; i++) {
		ans[i] = maxHeap.poll();
	}
	return ans;
}
