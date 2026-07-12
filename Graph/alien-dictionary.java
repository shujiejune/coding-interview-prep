/* There is a new alien language which uses the latin alphabet. 
 * However, the order among letters are unknown to you. 
 * You receive a list of non-empty words from the dictionary, 
 * where words are sorted lexicographically by the rules of 
 * this new language. Derive the order of letters in this language.
 */
public class Solution {
	public String alienOrder(String[] words) {
		if (words == null || words.length() == 0) {
			return "";
		}
		Map<Character, Integer> indegree = new HashMap<>();
		Map<Character, Set<Character>> graph = new HashMap<>();
		for (String word : words) {
			for (char c : word.toCharArray()) {
				indegree.put(c, 0);
			}
		}
		for (int i = 0; i < words.length - 1; i++) {
			String w1 = words[i];
			String w2 = words[i + 1];
			int upperBound = Math.min(w1.length(), w2.length());
			int index = 0;
			while (index < upperBound && w1.charAt(index) == w2.charAt(index)) {
				index++;
			}
			if (index < upperBound) {
				char c1 = w1.charAt(index);
				char c2 = w2.charAt(index);
				if (!graph.containsKey(c1)) {
					graph.put(c1, new HashSet<>());
				}
				if (!graph.get(c1).contains(c2)) {
					indegree.put(c2, indegree.get(c2) + 1);
				}
				graph.get(c1).add(c2);
			}
		}
		StringBuilder order = new StringBuilder();
		Queue<Character> q = new ArrayDeque<>();
		for (char c : indegree.keySet()) {
			if (indegree.get(c) == 0) {
				q.offer(c);
			}
		}
		while (!q.isEmpty()) {
			char curr = q.poll();
			order.append(curr);
			Set<Character> children = graph.get(curr);
			for (char child : children) {
				indegree.put(child, indegree.get(child) - 1);
				if (indegree.get(child) == 0) {
					q.offer(child);
				}
			}
		}
		if (order.length() != indegree.size()) {
			return "";
		}
		return order.toString();
	}
}
