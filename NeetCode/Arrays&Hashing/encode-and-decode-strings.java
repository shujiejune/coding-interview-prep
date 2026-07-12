/* Design an algorithm to encode a list of strings to a string. 
 * The encoded string is then sent over the network and is decoded 
 * back to the original list of strings.
 */
class Solution {
	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append(s.length());
			sb.append('#');
			sb.append(s);
		}
		return sb.toString();
	}

	public List<String> decode(String str) {
		List<String> ans = new ArrayList<>();
		int n = str.length();
		int left = 0, right = 0;
		while (right < n) {
			while (str.charAt(right) != '#') {
				right++;
			}
			int len = Integer.valueOf(str.substring(left, right));
			left = right + 1;
			right = left + len;
			ans.add(str.substring(left, right));
			left = right;
		}
		return ans;
	}
}
