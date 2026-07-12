/* Given a word, can it be composed by concatenating words from a given dictionary? */
public boolean canBreak(String input, Set<String> dict) {
	if (input == null || input.length() == 0) {
		return false;
	}
	int n = input.length();
	boolean[] dp = new boolean[n + 1];
	dp[0] = true;
	for (int i = 1; i <= n; i++) {
		for (int j = 0; j < i; j++) {
			if (dp[j] && dict.contains(input.substring(j, i))) {
				dp[i] = true;
				break;
			}
		}
	}
	return dp[n];
}
