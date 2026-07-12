class Solution {
	public int characterReplacement(String s, int k) {
		int n = s.length();
		int i = 0, j = 0, count = 0;
		int[] freq = new int[26];
		int main = s.charAt(0) - 'A';
		int ans = 0;
		while (j < n) {
			char c = s.charAt(j);
			freq[c - 'A']++;
			if (freq[c - 'A'] > freq[main]) {
				main = c - 'A';
			}
			count = j - i + 1 - freq[main];
			if (count > k) {
				while (i <= j && count > k) {
					char left = s.charAt(i++);
					freq[left - 'A']--;
					main = update(freq);
					count = j - i + 1 - freq[main];
				}
			} else {
				ans = Math.max(ans, j - i + 1);
			}
			j++;
		}
		return ans;
	}

	private int update(int[] freq) {
		int main = 0;
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] > freq[main]) {
				main = i;
			}
		}
		return main;
	}
}
