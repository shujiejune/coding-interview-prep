/* Given an array of strings strs, group all anagrams together 
 * into sublists. You may return the output in any order.
 * An anagram is a string that contains the exact same characters 
 * as another string, but the order of the characters can be different.
 */
class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> ans = new ArrayList<>();
		Map<String, List<String>> groups = new HashMap<>();
		for (String s : strs) {
			String head = normalize(s);
			if (!groups.containsKey(head)) {
				groups.put(head, new ArrayList<>());
			}
			groups.get(head).add(s);
		}
		for (String head : groups.keySet()) {
			ans.add(groups.get(head));
		}
		return  ans;
	}

	private String normalize(String s) {
		char[] arr = s.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}
}
