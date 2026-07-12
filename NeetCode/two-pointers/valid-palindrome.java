class Solution {
	public boolean isPalindrome(String s) {
		s = s.trim();
		s = s.toLowerCase();
		int i = 0, j = s.length() - 1;
		while (i < j) {
			while (i < j && !isValid(s.charAt(i))) {
				i++;
			}
			while (i < j && !isValid(s.charAt(j))) {
				j--;
			}
			if (s.charAt(i) == s.charAt(j)) {
				i++;
				j--;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean isValid(char c) {
		return Character.isAlphabetic(c) || Character.isDigit(c);
	}
}
